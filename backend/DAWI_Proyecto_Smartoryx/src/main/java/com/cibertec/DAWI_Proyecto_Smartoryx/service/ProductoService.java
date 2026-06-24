package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.ProductoRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.ProductoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Proveedor;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CategoriaRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MarcaRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProductoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProveedorRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private MarcaRepository marcaRepo;
	@Autowired
	private ProveedorRepository proveedorRepo;
	@Autowired
	private ProductoMapper productoMapper;
	@Autowired
	private AuditoriaService auditoriaService;

	public List<Producto> listar() {
		return repo.findAllActivos();
	}

	public List<Producto> listarTodos() {
		return repo.listarCompleto();
	}

	public org.springframework.data.domain.Page<Producto> listarTodosPaginado(org.springframework.data.domain.Pageable pageable) {
		return repo.findAllPaged(pageable);
	}

	public List<Producto> listarCompleto() {
		return repo.listarCompleto();
	}

	public List<Producto> listarPorMarca(Integer idMarca) {
		return repo.findByMarcaAndActivo(idMarca);
	}

	public List<Producto> listarPorCategoria(Integer idCategoria) {
		return repo.findByCategoriaIdCategoriaAndEstado(idCategoria);
	}

	public Producto obtenerProducto(Integer id_producto) {
		return repo.findByIdActivo(id_producto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
	}

	public Producto guardar(ProductoRequest request) {
		Categoria cat = categoriaRepo.findById(request.getIdCategoria())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría no existe"));

		Marca marca = marcaRepo.findById(request.getIdMarca())
				.orElseThrow(() -> new ResourceNotFoundException("Marca no existe"));

		Proveedor prov = proveedorRepo.findById(request.getIdProveedor())
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor no existe"));

		Producto producto = productoMapper.toEntity(request);
		producto.setCategoria(cat);
		producto.setMarca(marca);
		producto.setProveedor(prov);
		producto.setEstado(1);

		Producto nuevo = repo.save(producto);
		auditoriaService.registrar("tb_productos", "INSERT", "Producto creado ID: " + nuevo.getId_producto(), 1);

		return nuevo;
	}

	public Producto actualizar(Integer id_producto, ProductoRequest request) {
		Producto prod = repo.findById(id_producto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

		Categoria cat = categoriaRepo.findById(request.getIdCategoria())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría no existe"));
		Marca marca = marcaRepo.findById(request.getIdMarca())
				.orElseThrow(() -> new ResourceNotFoundException("Marca no existe"));
		Proveedor prov = proveedorRepo.findById(request.getIdProveedor())
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor no existe"));

		prod.setNombre(request.getNombre());
		prod.setDescripcion(request.getDescripcion());
		prod.setPrecio(request.getPrecio());
		prod.setStock(request.getStock());
		prod.setEstado(request.getEstado() != null ? request.getEstado() : prod.getEstado());
		prod.setImagen_url(request.getImagenUrl());
		prod.setCategoria(cat);
		prod.setMarca(marca);
		prod.setProveedor(prov);

		Producto actualizado = repo.save(prod);
		auditoriaService.registrar("tb_productos", "UPDATE", "Producto actualizado ID: " + actualizado.getId_producto(), 1);
		return actualizado;
	}

	public void eliminar(Integer id_producto) {
		Producto prod = repo.findById(id_producto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		prod.setEstado(0);
		repo.save(prod);
		auditoriaService.registrar("tb_productos", "DELETE", "Producto eliminado ID: " + prod.getId_producto(), 1);
	}
}
