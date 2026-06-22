import type { ApiError } from "./types";

const API_BASE =
    import.meta.env.VITE_API_URL || "http://localhost:8080";

const DEFAULT_TIMEOUT = 15000;

export function getImageUrl(path: string | null | undefined): string {
    if (!path) return "/placeholder.svg";
    if (path.startsWith("http://") || path.startsWith("https://")) return path;
    if (path.startsWith("/images/")) return path;
    if (path.startsWith("/")) return `/images${path}`;
    return `/images/${path}`;
}

type RequestOptions = {
    method?: "GET" | "POST" | "PUT" | "DELETE";
    body?: unknown;
    headers?: Record<string, string>;
    signal?: AbortSignal;
    forwardCookies?: string;
    redirectOn401?: boolean;
};

async function request<T>(path: string, options: RequestOptions = {}): Promise<T> {
    const {
        method = "GET",
        body,
        headers = {},
        signal,
        forwardCookies,
        redirectOn401 = true,
    } = options;

    const url = path.startsWith("http") ? path : `${API_BASE}${path}`;

    const fetchHeaders: Record<string, string> = {
        Accept: "application/json",
        ...headers,
    };

    if (body !== undefined && !(body instanceof FormData)) {
        fetchHeaders["Content-Type"] = "application/json";
    }

    if (forwardCookies) {
        fetchHeaders["Cookie"] = forwardCookies;
    }

    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), DEFAULT_TIMEOUT);
    const finalSignal = signal ?? controller.signal;

    let response: Response;
    try {
        response = await fetch(url, {
            method,
            headers: fetchHeaders,
            body: body === undefined ? undefined : body instanceof FormData ? body : JSON.stringify(body),
            credentials: "include",
            signal: finalSignal,
        });
    } catch (err) {
        clearTimeout(timeoutId);
        const message =
            err instanceof Error && err.name === "AbortError"
                ? "La solicitud tardó demasiado. Intenta de nuevo."
                : "No se pudo conectar con el servidor. Verifica tu conexión.";
        throw { status: 0, message } satisfies ApiError;
    }

    clearTimeout(timeoutId);

    if (response.status === 401 && redirectOn401 && typeof window !== "undefined") {
        const currentPath = window.location.pathname;
        if (!currentPath.startsWith("/login") && !currentPath.startsWith("/register")) {
            window.location.href = `/login?redirect=${encodeURIComponent(currentPath)}`;
        }
    }

    if (response.status === 204) {
        return undefined as T;
    }

    const contentType = response.headers.get("content-type") || "";
    const isJson = contentType.includes("application/json");

    if (!response.ok) {
        let errorMessage = `Error ${response.status}`;
        let fieldErrors: Record<string, string> | undefined;

        if (isJson) {
            try {
                const data = await response.json();
                errorMessage = data.message || data.error || errorMessage;
                if (data.errors && typeof data.errors === "object") {
                    fieldErrors = data.errors;
                }
            } catch {
                // ignore
            }
        }
        throw {
            status: response.status,
            message: errorMessage,
            errors: fieldErrors,
        } satisfies ApiError;
    }

    if (!isJson) {
        return undefined as T;
    }

    return (await response.json()) as T;
}

export const api = {
    get: <T>(path: string, options?: Omit<RequestOptions, "method" | "body">) =>
        request<T>(path, { ...options, method: "GET" }),

    post: <T>(path: string, body?: unknown, options?: Omit<RequestOptions, "method" | "body">) =>
        request<T>(path, { ...options, method: "POST", body }),

    put: <T>(path: string, body?: unknown, options?: Omit<RequestOptions, "method" | "body">) =>
        request<T>(path, { ...options, method: "PUT", body }),

    del: <T>(path: string, options?: Omit<RequestOptions, "method" | "body">) =>
        request<T>(path, { ...options, method: "DELETE" }),
};

export function getApiErrorMessage(err: unknown, fallback = "Ocurrió un error"): string {
    if (err && typeof err === "object" && "message" in err) {
        return String((err as ApiError).message);
    }
    return fallback;
}
