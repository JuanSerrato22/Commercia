package com.sena.crud_basic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.scheduleDTO;
//extedemos de JPA para generar la conexión
//JpaRepository<modelo,tipoPK>
public interface IScheduleRepository extends JpaRepository<scheduleDTO,Integer>{

    /*
     * C
     * R
     * U
     * D
     */
    
} 
