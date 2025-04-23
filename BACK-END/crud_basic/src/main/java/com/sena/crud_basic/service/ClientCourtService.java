package com.sena.crud_basic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.repository.IClientCourtRepository;
import com.sena.crud_basic.model.clientcourtDTO;

/*
 * Agregamos la anotación bean @Service
 * Para indicar que el archivo es un servicio
 */
@Service
public class ClientCourtService{

    //se realiza la conexión con el repositorio
    @Autowired
    private IClientCourtRepository IClientCourtRepository;

    /*
     * crear
     * eliminar
     * actualizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     * 
     */





    public List<clientcourtDTO> getAllClientCourt(){
        return IClientCourtRepository.findAll();
    }

    public clientcourtDTO getClientCourtById(int id){
        return IClientCourtRepository.findById(id).get();
    }

    public boolean save(clientcourtDTO clientCourt){
        /*
         * if(customer.getId==0)register or create
         * else update
         */
        IClientCourtRepository.save(clientCourt);
        return true;
    }
    
}
