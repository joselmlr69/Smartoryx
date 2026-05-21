package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MetodoPagoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.PagoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.VentaRepository;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepo;
	@Autowired
    private VentaRepository ventaRepo;
	@Autowired
    private MetodoPagoRepository metodoRepo;

    public Pago registrarPago(Pago pago) {

        Venta venta = ventaRepo.findById(
                pago.getVenta().getId_venta()
        ).orElseThrow(() -> new RuntimeException("Venta no existe"));

        MetodoPago metodo = metodoRepo.findById(
                pago.getMetodoPago().getId_metodo()
        ).orElseThrow(() -> new RuntimeException("Método no existe"));

        // VALIDAR MONTO
        if (!venta.getTotal().equals(pago.getMonto())) {
            throw new RuntimeException("El monto no coincide con el total de la venta");
        }

        pago.setVenta(venta);
        pago.setMetodoPago(metodo);

        return pagoRepo.save(pago);
    }
}
