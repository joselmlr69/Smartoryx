package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Envio;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.DireccionRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.EnvioRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.VentaRepository;

@Service
public class EnvioService {

	@Autowired
    private EnvioRepository envioRepo;
	@Autowired
	private VentaRepository ventaRepo;
	@Autowired
	private DireccionRepository direccionRepo;

    public List<Envio> listar() {
        return envioRepo.findAll();
    }

    public Envio obtenerPorId(Integer id) {
        return envioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no existe"));
    }

    public Envio guardar(Integer id_venta, Integer id_direccion, Envio envio) {

        Venta venta = ventaRepo.findById(id_venta)
                .orElseThrow(() -> new RuntimeException("Venta no existe"));

        Direccion direccion = direccionRepo.findById(id_direccion)
                .orElseThrow(() -> new RuntimeException("Dirección no existe"));

        envio.setVenta(venta);
        envio.setDireccion(direccion);

        return envioRepo.save(envio);
    }

    public Envio actualizar(Integer id_envio, Envio nuevoEnvio) {

        Envio envio = envioRepo.findById(id_envio)
                .orElseThrow(() -> new RuntimeException("Envío no existe"));

        envio.setEstado(nuevoEnvio.getEstado());
        envio.setFechaEnvio(nuevoEnvio.getFechaEnvio());

        return envioRepo.save(envio);
    }

    public void eliminar(Integer id) {

        Envio envio = envioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no existe"));

        envioRepo.delete(envio);
    }
}