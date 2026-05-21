package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Proveedor;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProveedorRepository;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorRepository provRepo;

    public List<Proveedor> listar() {
        return provRepo.findAll();
    }

    public Proveedor obtenerProveedor(int id_proveedor) {
    	return provRepo.findById(id_proveedor).orElse(null);
    }
    public Proveedor guardar(Proveedor proveedor) {
        return provRepo.save(proveedor);
    }

    public Proveedor actualizar(Integer id_proveedor, Proveedor proveedor) {
        Proveedor prov = provRepo.findById(id_proveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        prov.setNombre(proveedor.getNombre());
        prov.setRuc(proveedor.getRuc());
        prov.setTelefono(proveedor.getTelefono());

        return provRepo.save(prov);
    }

    public void eliminar(Integer id_proveedor) {
        Proveedor prov = provRepo.findById(id_proveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        provRepo.delete(prov);
    }
}
