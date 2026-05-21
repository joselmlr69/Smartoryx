package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Auditoria;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.AuditoriaRepository;

@Service
public class AuditoriaService {
	
	@Autowired
	 private AuditoriaRepository repo;

	    public void registrar(String tabla, String accion, String descripcion, Integer idUsuario) {

	        Auditoria audit = new Auditoria();
	        audit.setTabla(tabla);
	        audit.setAccion(accion);
	        audit.setDescripcion(descripcion);
	        audit.setFecha(new Date());
	        audit.setId_usuario(idUsuario);

	        repo.save(audit);
	    }

}
