package com.sena.crud_basic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.repository.ICourtRepository;
import com.sena.crud_basic.model.courtDTO;

/*
 * Agregamos la anotación bean @Service
 * Para indicar que el archivo es un servicio
 */
@Service
public class CourtService{

    //se realiza la conexión con el repositorio
    @Autowired
    private ICourtRepository ICourtRepository;

    /*
     * crear
     * eliminar
     * actualizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     * 
     */





    public List<courtDTO> getAllCourt(){
        return ICourtRepository.findAll();
    }

    public courtDTO getCourtById(int id){
        return ICourtRepository.findById(id).get();
    }

    public boolean save(courtDTO court){
        /*
         * if(customer.getId==0)register or create
         * else update
         */
        ICourtRepository.save(court);
        return true;
    }
    
}
