package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
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
	private AuditoriaService auditoriaService;

	public List<Producto> listar() {
		return repo.findAllActivos();
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

	public Producto guardar(Producto producto) {
		Categoria cat = categoriaRepo.findById(producto.getCategoria().getId_categoria())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría no existe"));

		Marca marca = marcaRepo.findById(producto.getMarca().getId_marca())
				.orElseThrow(() -> new ResourceNotFoundException("Marca no existe"));

		Proveedor prov = proveedorRepo.findById(producto.getProveedor().getId_proveedor())
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor no existe"));

		producto.setCategoria(cat);
		producto.setMarca(marca);
		producto.setProveedor(prov);
		producto.setEstado(1);

		Producto nuevo = repo.save(producto);
		auditoriaService.registrar("tb_productos", "INSERT", "Producto creado ID: " + nuevo.getId_producto(), 1);

		return nuevo;
	}

	public Producto actualizar(Integer id_producto, Producto producto) {
		Producto prod = repo.findById(id_producto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

		prod.setNombre(producto.getNombre());
		prod.setDescripcion(producto.getDescripcion());
		prod.setPrecio(producto.getPrecio());
		prod.setStock(producto.getStock());
		prod.setEstado(producto.getEstado());
		prod.setImagen_url(producto.getImagen_url());

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
