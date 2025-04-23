package com.sena.crud_basic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.reservationDTO;
//extedemos de JPA para generar la conexión
//JpaRepository<modelo,tipoPK>
public interface IReservationRepository extends JpaRepository<reservationDTO,Integer>{

    /*
     * C
     * R
     * U
     * D
     */
    
} 
