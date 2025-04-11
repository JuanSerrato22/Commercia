package com.sena.crud_basic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.repository.IClientRepository;
import com.sena.crud_basic.model.ClientDTO;

/*
 * Agregamos la anotación bean @Service
 * Para indicar que el archivo es un servicio
 */
@Service
public class ClientService{

    //se realiza la conexión con el repositorio
    @Autowired
    private IClientRepository IClientRepository;

    /*
     * crear
     * eliminar
     * actualizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     * 
     */





    public List<ClientDTO> getAllClient(){
        return IClientRepository.findAll();
    }

    public ClientDTO getClientById(int id){
        return IClientRepository.findById(id).get();
    }

    public boolean save(ClientDTO client){
        /*
         * if(customer.getId==0)register or create
         * else update
         */
        IClientRepository.save(client);
        return true;
    }
    
}
