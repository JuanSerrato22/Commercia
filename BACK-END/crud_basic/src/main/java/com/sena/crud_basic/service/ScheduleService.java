package com.sena.crud_basic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.repository.IScheduleRepository;
import com.sena.crud_basic.model.scheduleDTO;

/*
 * Agregamos la anotación bean @Service
 * Para indicar que el archivo es un servicio
 */
@Service
public class ScheduleService{

    //se realiza la conexión con el repositorio
    @Autowired
    private IScheduleRepository IScheduleRepository;

    /*
     * crear
     * eliminar
     * actualizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     * 
     */





    public List<scheduleDTO> getAllSchedule(){
        return IScheduleRepository.findAll();
    }

    public scheduleDTO getScheduleById(int id){
        return IScheduleRepository.findById(id).get();
    }

    public boolean save(scheduleDTO schedule){
        /*
         * if(customer.getId==0)register or create
         * else update
         */
        IScheduleRepository.save(schedule);
        return true;
    }
    
}
