export interface Product {
    id: string;
    name: string;
    title: string; // compatibility with home.ts
    price: number;
    description: string;
    category: string;
    subcategory?: string;
    stock: number;
    images: string[];
    slug: string;
    badge?: string;
    discount?: number;
    specs?: { label: string; value: string }[];
}

export const products: Product[] = [
    {
        id: "1",
        name: "MacBook Pro 16 M3 Max",
        title: "MacBook Pro 16 M3 Max",
        price: 3499,
        description: "The most powerful MacBook Pro ever. With the lightning-fast M3 Max chip, advanced thermal architecture, and a stunning Liquid Retina XDR display, it's designed for those who demand the absolute best in performance and portability.",
        category: "Computers",
        subcategory: "notebooks",
        stock: 5,
        images: [
            "https://images.unsplash.com/photo-1629131726692-1accd0c53ce0?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1611186871348-b1ce696e52c9?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "macbook-pro-16-m3-max",
        badge: "Premium",
        discount: 5,
        specs: [
            { label: "Processor", value: "Apple M3 Max" },
            { label: "Memory", value: "32GB Unified" },
            { label: "Storage", value: "1TB SSD" },
            { label: "Display", value: "16.2-inch XDR" }
        ]
    },
    {
        id: "2",
        name: "Sony WH-1000XM5",
        title: "Sony WH-1000XM5",
        price: 399,
        description: "Industry-leading noise cancellation, exceptional sound quality, and crystal-clear hands-free calling. The WH-1000XM5 headphones rewrite the rules for distraction-free listening and ultra-clear call quality.",
        category: "Audio",
        stock: 12,
        images: [
            "https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1583394838336-acd977736f90?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1546435770-a3e426bf472b?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "sony-wh-1000xm5",
        badge: "Bestseller",
        discount: 10,
        specs: [
            { label: "Battery Life", value: "Up to 30 hours" },
            { label: "Noise Cancelling", value: "Industry Leading" },
            { label: "Bluetooth", value: "v5.2" },
            { label: "Weight", value: "250g" }
        ]
    },
    {
        id: "3",
        name: "iPhone 15 Pro Max",
        title: "iPhone 15 Pro Max",
        price: 1199,
        description: "Forged in titanium. Featuring the groundbreaking A17 Pro chip, a customizable Action button, and the most powerful iPhone camera system ever. Experience the next level of mobile technology.",
        category: "Phones",
        subcategory: "iphone",
        stock: 0,
        images: [
            "https://i.blogs.es/f15f0b/img_2033/650_1200.jpeg",
            "https://images.unsplash.com/photo-1678652197831-2d180705cd2c?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "iphone-15-pro-max",
        badge: "New Arrival",
        discount: 0,
        specs: [
            { label: "Chip", value: "A17 Pro" },
            { label: "Camera", value: "48MP Main" },
            { label: "Material", value: "Titanium" },
            { label: "Size", value: "6.7-inch" }
        ]
    },
    {
        id: "4",
        name: "Logitech MX Master 3S",
        title: "Logitech MX Master 3S",
        price: 99,
        description: "An icon, remastered. Meet MX Master 3S – an iconic mouse remastered for ultimate tactility, performance, and flow. Feel every moment of your workflow with even more precision, tactility, and performance.",
        category: "Accessories",
        stock: 50,
        images: [
            "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?auto=format&fit=crop&w=800&q=80",
            "https://www.mastecnologia.com.ar/images/productos/17101.png"
        ],
        slug: "logitech-mx-master-3s",
        badge: "Essential",
        discount: 0,
        specs: [
            { label: "DPI", value: "8000" },
            { label: "Buttons", value: "7 Programmable" },
            { label: "Battery", value: "Rechargeable" },
            { label: "Connectivity", value: "Logi Bolt / BT" }
        ]
    },
    {
        id: "5",
        name: "iPad Pro M2",
        title: "iPad Pro M2",
        price: 799,
        description: "Astonishing performance. Incredibly advanced displays. Superfast wireless connectivity. Next-level Apple Pencil features. Powerful new features in iPadOS. The ultimate iPad experience.",
        category: "Computers",
        subcategory: "notebooks",
        stock: 15,
        images: [
            "https://i.guim.co.uk/img/media/23b25aa93e573bc0b016c0e6e9233bc51da92c54/0_240_5261_3156/master/5261.jpg?width=1200&quality=85&auto=format&fit=max&s=5f0115e164644ebfdbc3c86ee86c163b",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQonjUvhTVx33qjjjCrRp68MDRAlt_1uqA9YA&s"
        ],
        slug: "ipad-pro-m2",
        badge: "Pro",
        discount: 0,
        specs: [
            { label: "Chip", value: "Apple M2" },
            { label: "Display", value: "Liquid Retina" },
            { label: "Camera", value: "12MP Ultra Wide" },
            { label: "Storage", value: "256GB" }
        ]
    },
    {
        id: "6",
        name: "Dell XPS 15",
        title: "Dell XPS 15",
        price: 1899,
        description: "High-performance laptop with a stunning 4K OLED display and premium build quality.",
        category: "Computers",
        subcategory: "notebooks",
        stock: 10,
        images: [
            "https://images-cdn.ubuy.qa/634ea45745ed0860dc58d75b-dell-xps-15-9510-laptop-2021.jpg",
        ],
        slug: "dell-xps-15",
        specs: [
            { label: "Processor", value: "Intel i9" },
            { label: "RAM", value: "32GB" },
            { label: "Screen", value: "15.6 OLED" }
        ]
    },
    {
        id: "7",
        name: "MacBook Air 15",
        title: "MacBook Air 15",
        price: 1299,
        description: "Impossibly thin and incredibly fast. The 15-inch MacBook Air gives you more room for what you love with a spacious Liquid Retina display.",
        category: "Computers",
        subcategory: "notebooks",
        stock: 25,
        images: [
            "https://images.unsplash.com/photo-1611186871348-b1ce696e52c9?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1541807084-5c52b6b3adef?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "macbook-air-15",
        badge: "New",
        specs: [
            { label: "Chip", value: "M2" },
            { label: "Battery", value: "18 Hours" },
            { label: "Weight", value: "3.3 lbs" }
        ]
    },
    {
        id: "8",
        name: "ASUS ROG Zephyrus G14",
        title: "ASUS ROG Zephyrus G14",
        price: 1499,
        description: "Powerful gaming in a compact chassis. Features the latest Ryzen processors and RTX graphics for gaming on the go.",
        category: "Computers",
        subcategory: "notebooks",
        stock: 8,
        images: [
            "https://www.cnet.com/a/img/resize/69c350f19a0bd245ab8f60a98e456fa9dc67ee42/hub/2024/02/05/e716f8f8-a7a4-418c-9b14-0b210d9dfc72/asus-rog-zephyrus-g14-2024-5409.jpg?auto=webp&fit=crop&height=1200&width=1200",
        ],
        slug: "asus-rog-zephyrus-g14",
        badge: "Gaming",
        specs: [
            { label: "GPU", value: "RTX 4060" },
            { label: "CPU", value: "Ryzen 9" },
            { label: "Screen", value: "120Hz" }
        ]
    },
    {
        id: "9",
        name: "Samsung Galaxy S24 Ultra",
        title: "Samsung Galaxy S24 Ultra",
        price: 1299,
        description: "Unleash your creativity, productivity, and possibilities. With a built-in S Pen and the most powerful processor yet.",
        category: "Phones",
        subcategory: "android",
        stock: 20,
        images: [
            "https://images.unsplash.com/photo-1610945415295-d9bbf067e59c?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "samsung-galaxy-s24-ultra",
        badge: "AI Ready",
        specs: [
            { label: "Camera", value: "200MP" },
            { label: "Zoom", value: "100x" },
            { label: "Pen", value: "Included" }
        ]
    },
    {
        id: "10",
        name: "Google Pixel 8 Pro",
        title: "Google Pixel 8 Pro",
        price: 999,
        description: "The all-pro phone engineered by Google. It has a polished aluminum frame and matte back glass, and it comes in elegant colors.",
        category: "Phones",
        subcategory: "android",
        stock: 15,
        images: [
            "https://lh3.googleusercontent.com/22AC6Qcb5-4qN6QJTkBzGK2N5kS5AZyuss9AcAQzAuxjHqGz3VfI5-MSXsKDzuUuePoqHAmyAFyewt9CdNyw3oQikUDY7dTSmyDsVPo=rw-e365-w842-v1",
            "https://tecnopolis.com.bo/cdn/shop/files/8-PRO.png?v=1763581431"
        ],
        slug: "google-pixel-8-pro",
        badge: "Smartest",
        specs: [
            { label: "Chip", value: "Tensor G3" },
            { label: "OS", value: "Android 14" },
            { label: "AI", value: "Gemini Nano" }
        ]
    },
    {
        id: "11",
        name: "iPhone 14",
        title: "iPhone 14",
        price: 699,
        description: "A total powerhouse. Impressive battery life, durable build, and a camera system that takes stunning photos in any light.",
        category: "Phones",
        subcategory: "iphone",
        stock: 30,
        images: [
            "https://images.unsplash.com/photo-1678652197831-2d180705cd2c?auto=format&fit=crop&w=800&q=80",
        ],
        slug: "iphone-14",
        specs: [
            { label: "Chip", value: "A15 Bionic" },
            { label: "Screen", value: "6.1 Super Retina" },
            { label: "Safety", value: "Crash Detection" }
        ]
    },
    {
        id: "12",
        name: "AirPods Max",
        title: "AirPods Max",
        price: 549,
        description: "A perfect balance of exhilarating high-fidelity audio and the effortless magic of AirPods.",
        category: "Audio",
        stock: 10,
        images: [
            "https://images.unsplash.com/photo-1613040809024-b4ef7ba99bc3?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "airpods-max",
        badge: "Premium Audio",
        specs: [
            { label: "Driver", value: "Dynamic" },
            { label: "ANC", value: "Active" },
            { label: "Spatial", value: "Head Tracking" }
        ]
    },
    {
        id: "13",
        name: "Bose QuietComfort Ultra",
        title: "Bose QuietComfort Ultra",
        price: 429,
        description: "World-class noise cancellation, quieter than ever before. Breakthrough spatial audio for more immersive listening.",
        category: "Audio",
        stock: 18,
        images: [
            "https://m.media-amazon.com/images/I/51ZR4lyxBHL.jpg",
        ],
        slug: "bose-qc-ultra",
        specs: [
            { label: "Battery", value: "24 Hours" },
            { label: "Modes", value: "Quiet/Aware" },
            { label: "Comfort", value: "Plush" }
        ]
    },
    {
        id: "14",
        name: "Sonos Era 100",
        title: "Sonos Era 100",
        price: 249,
        description: "Next-gen acoustics and new levels of connectivity transform any room with the finely tuned stereo sound and rich bass.",
        category: "Audio",
        stock: 22,
        images: [
            "https://culturageek.com.ar/wp-content/uploads/2024/12/566898-Era-100-Pro_Mount-Ceiling_Black-309023-original-1733603775_11zon.webp",
        ],
        slug: "sonos-era-100",
        specs: [
            { label: "Type", value: "Smart Speaker" },
            { label: "Voice", value: "Alexa Built-in" },
            { label: "Connect", value: "WiFi/BT" }
        ]
    },
    {
        id: "15",
        name: "Keychron Q1 Pro",
        title: "Keychron Q1 Pro",
        price: 199,
        description: "A fully customizable mechanical keyboard with QMK/VIA support and a premium aluminum body.",
        category: "Computers",
        subcategory: "keyboards",
        stock: 30,
        images: [
            "https://images.unsplash.com/photo-1595225476474-87563907a212?auto=format&fit=crop&w=800&q=80",
            "https://i.ytimg.com/vi/URjj7-QghHs/maxresdefault.jpg"
        ],
        slug: "keychron-q1-pro",
        badge: "Mechanical",
        specs: [
            { label: "Layout", value: "75%" },
            { label: "Body", value: "Aluminum" },
            { label: "Switch", value: "Hot-swappable" }
        ]
    },
    {
        id: "16",
        name: "Apple Magic Trackpad",
        title: "Apple Magic Trackpad",
        price: 129,
        description: "Wireless and rechargeable, it includes the full range of Multi-Touch gestures and Force Touch technology.",
        category: "Accessories",
        stock: 40,
        images: [
            "https://images.unsplash.com/photo-1522204538344-922f76ecc041?auto=format&fit=crop&w=800&q=80"
        ],
        slug: "magic-trackpad",
        specs: [
            { label: "Surface", value: "Glass" },
            { label: "Connect", value: "Bluetooth" },
            { label: "Color", value: "White/Black" }
        ]
    },
    {
        id: "17",
        name: "Samsung T7 Shield 2TB",
        title: "Samsung T7 Shield 2TB",
        price: 159,
        description: "Rugged durability. Fast speeds. The T7 Shield gives you superior performance on the go, even in challenging environmental conditions.",
        category: "Accessories",
        stock: 100,
        images: [
            "https://http2.mlstatic.com/D_NQ_NP_646728-CBT96610611509_102025-O.webp",
        ],
        slug: "samsung-t7-shield",
        specs: [
            { label: "Speed", value: "1000MB/s" },
            { label: "Rating", value: "IP65" },
            { label: "Port", value: "USB-C" }
        ]
    },
    {
        id: "18",
        name: "Dell UltraSharp 27",
        title: "Dell UltraSharp 27",
        price: 499,
        description: "Experience captivating details and true-to-life color on this 27-inch QHD monitor with InfinityEdge.",
        category: "Computers",
        subcategory: "monitors",
        stock: 12,
        images: [
            "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=800&q=80",
        ],
        slug: "dell-ultrasharp-27",
        specs: [
            { label: "Res", value: "2560x1440" },
            { label: "Panel", value: "IPS" },
            { label: "Ports", value: "USB-C Hub" }
        ]
    },
    {
        id: "19",
        name: "Fujifilm X100V",
        title: "Fujifilm X100V",
        price: 1399,
        description: "The fifth iteration in Fujifilm's X100 series, blending classic dial-based design with the most advanced mobile technology.",
        category: "Accessories",
        stock: 3,
        images: [
            "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=800&q=80",
        ],
        slug: "fujifilm-x100v",
        badge: "Limited",
        specs: [
            { label: "Sensor", value: "APS-C" },
            { label: "Lens", value: "23mm F2" },
            { label: "Video", value: "4K" }
        ]
    },
    {
        id: "20",
        name: "LG C3 OLED 42",
        title: "LG C3 OLED 42",
        price: 899,
        description: "The advanced LG OLED evo C-Series is better than ever. The LG OLED evo C3 is powered by the next-gen a9 AI Processor Gen6.",
        category: "Computers",
        subcategory: "monitors",
        stock: 6,
        images: [
            "https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?auto=format&fit=crop&w=800&q=80",

        ],
        slug: "lg-c3-oled-42",
        badge: "Display",
        specs: [
            { label: "Type", value: "OLED" },
            { label: "Refresh", value: "120Hz" },
            { label: "HDR", value: "Dolby Vision" }
        ]
    },
    {
        id: "21",
        name: "Cargador Samsung 45W",
        title: "Cargador Samsung 45W",
        price: 89.90,
        description: "Cargador rápido USB-C 45W original Samsung para carga ultrarrápida.",
        category: "Accesorios",
        subcategory: "cargadores",
        stock: 50,
        images: ["/Accesorios/cargador samsung 45 w.jpg"],
        slug: "cargador-samsung-45w",
        badge: "Original",
        specs: [
            { label: "Potencia", value: "45W" },
            { label: "Puerto", value: "USB-C" },
            { label: "Compatible", value: "Samsung Galaxy" }
        ]
    },
    {
        id: "22",
        name: "Cargador Apple 20W",
        title: "Cargador Apple 20W",
        price: 79.90,
        description: "Cargador rápido USB-C 20W para iPhone con carga optimizada.",
        category: "Accesorios",
        subcategory: "cargadores",
        stock: 40,
        images: ["/Accesorios/cargador apple.jpg"],
        slug: "cargador-apple-20w",
        badge: "Original",
        specs: [
            { label: "Potencia", value: "20W" },
            { label: "Puerto", value: "USB-C" },
            { label: "Compatible", value: "iPhone/iPad" }
        ]
    },
    {
        id: "23",
        name: "Cargador Xiaomi 120W",
        title: "Cargador Xiaomi 120W",
        price: 99.90,
        description: "Cargador HyperCharge 120W de carga ultrarrápida para Xiaomi.",
        category: "Accesorios",
        subcategory: "cargadores",
        stock: 35,
        images: ["/Accesorios/cargador xiomi 120w.jpg"],
        slug: "cargador-xiaomi-120w",
        badge: "HyperCharge",
        specs: [
            { label: "Potencia", value: "120W" },
            { label: "Puerto", value: "USB-C" },
            { label: "Compatible", value: "Xiaomi/Redmi" }
        ]
    },
    {
        id: "24",
        name: "Cargador Motorola 33W",
        title: "Cargador Motorola 33W",
        price: 69.90,
        description: "Cargador turbo power 33W original Motorola para carga rápida.",
        category: "Accesorios",
        subcategory: "cargadores",
        stock: 45,
        images: ["/Accesorios/cargador motorola.jpg"],
        slug: "cargador-motorola-33w",
        badge: "Turbo Power",
        specs: [
            { label: "Potencia", value: "33W" },
            { label: "Puerto", value: "USB-C" },
            { label: "Compatible", value: "Motorola" }
        ]
    },
    {
        id: "25",
        name: "Samsung Galaxy Buds4 Pro",
        title: "Samsung Galaxy Buds4 Pro",
        price: 349.90,
        description: "Audífonos inalámbricos premium con cancelación activa de ruido.",
        category: "Accesorios",
        subcategory: "audifonos",
        stock: 25,
        images: ["/Accesorios/samsung bud 4 pro.jpg"],
        slug: "samsung-galaxy-buds4-pro",
        badge: "Premium",
        specs: [
            { label: "ANC", value: "Activa" },
            { label: "Batería", value: "8h + 20h estuche" },
            { label: "Bluetooth", value: "5.3" }
        ]
    },
    {
        id: "26",
        name: "Apple AirPods Pro",
        title: "Apple AirPods Pro",
        price: 549.90,
        description: "Audífonos inalámbricos Apple con cancelación de ruido y audio espacial.",
        category: "Accesorios",
        subcategory: "audifonos",
        stock: 20,
        images: ["/Accesorios/airpods pro.jpg"],
        slug: "apple-airpods-pro",
        badge: "Bestseller",
        specs: [
            { label: "ANC", value: "Activa" },
            { label: "Batería", value: "6h + 24h estuche" },
            { label: "Chip", value: "H2" }
        ]
    },
    {
        id: "27",
        name: "Redmi Buds 6",
        title: "Redmi Buds 6",
        price: 79.90,
        description: "Audífonos inalámbricos económicos con buena calidad de sonido.",
        category: "Accesorios",
        subcategory: "audifonos",
        stock: 60,
        images: ["/Accesorios/redmi buds 6.jpg"],
        slug: "redmi-buds-6",
        badge: "Económico",
        specs: [
            { label: "ANC", value: "No" },
            { label: "Batería", value: "7h + 21h estuche" },
            { label: "Bluetooth", value: "5.3" }
        ]
    },
    {
        id: "28",
        name: "Motorola Moto Buds C30",
        title: "Motorola Moto Buds C30",
        price: 89.90,
        description: "Audífonos inalámbricos compactos con diseño ergonómico.",
        category: "Accesorios",
        subcategory: "audifonos",
        stock: 40,
        images: ["/Accesorios/motorola moto buds c30.jpg"],
        slug: "motorola-moto-buds-c30",
        specs: [
            { label: "ANC", value: "No" },
            { label: "Batería", value: "6h + 18h estuche" },
            { label: "Bluetooth", value: "5.1" }
        ]
    },
    {
        id: "29",
        name: "Funda Samsung Galaxy",
        title: "Funda Samsung Galaxy",
        price: 39.90,
        description: "Funda protectora transparente anti-golpes para Samsung Galaxy.",
        category: "Accesorios",
        subcategory: "fundas",
        stock: 100,
        images: ["/Accesorios/fundas samsung.jpg"],
        slug: "funda-samsung-galaxy",
        specs: [
            { label: "Material", value: "TPU transparente" },
            { label: "Protección", value: "Anti-golpes" },
            { label: "Compatible", value: "Samsung Galaxy" }
        ]
    },
    {
        id: "30",
        name: "Funda Apple iPhone",
        title: "Funda Apple iPhone",
        price: 49.90,
        description: "Funda protectora de silicona premium para iPhone.",
        category: "Accesorios",
        subcategory: "fundas",
        stock: 80,
        images: ["/Accesorios/fundas apple.jpg"],
        slug: "funda-apple-iphone",
        badge: "Premium",
        specs: [
            { label: "Material", value: "Silicona premium" },
            { label: "Protección", value: "Anti-impacto" },
            { label: "Compatible", value: "iPhone" }
        ]
    },
    {
        id: "31",
        name: "Funda Redmi/Xiaomi",
        title: "Funda Redmi/Xiaomi",
        price: 29.90,
        description: "Funda protectora resistente transparente para Redmi/Xiaomi.",
        category: "Accesorios",
        subcategory: "fundas",
        stock: 120,
        images: ["/Accesorios/fundas redmi.jpg"],
        slug: "funda-redmi-xiaomi",
        badge: "Económica",
        specs: [
            { label: "Material", value: "TPU resistente" },
            { label: "Protección", value: "Anti-rayones" },
            { label: "Compatible", value: "Redmi/Xiaomi" }
        ]
    },
    {
        id: "32",
        name: "Funda Motorola",
        title: "Funda Motorola",
        price: 34.90,
        description: "Funda protectora flexible anti-impacto para Motorola.",
        category: "Accesorios",
        subcategory: "fundas",
        stock: 90,
        images: ["/Accesorios/fundas motorola.jpg"],
        slug: "funda-motorola",
        specs: [
            { label: "Material", value: "Silicona flexible" },
            { label: "Protección", value: "Anti-impacto" },
            { label: "Compatible", value: "Motorola" }
        ]
    }
];

export const getProductBySlug = (slug: string) => products.find(p => p.slug === slug);
export const getRelatedProducts = (category: string, currentSlug: string) =>
    products.filter(p => p.category === category && p.slug !== currentSlug).slice(0, 4);
