package com.sena.crud_basic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.repository.IReservationRepository;
import com.sena.crud_basic.model.reservationDTO;

/*
 * Agregamos la anotación bean @Service
 * Para indicar que el archivo es un servicio
 */
@Service
public class ReservationService{

    //se realiza la conexión con el repositorio
    @Autowired
    private IReservationRepository IReservationRepository;

    /*
     * crear
     * eliminar
     * actualizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     * 
     */





    public List<reservationDTO> getAllReservation(){
        return IReservationRepository.findAll();
    }

    public reservationDTO getReservationById(int id){
        return IReservationRepository.findById(id).get();
    }

    public boolean save(reservationDTO reservation){
        /*
         * if(customer.getId==0)register or create
         * else update
         */
        IReservationRepository.save(reservation);
        return true;
    }
    
}
