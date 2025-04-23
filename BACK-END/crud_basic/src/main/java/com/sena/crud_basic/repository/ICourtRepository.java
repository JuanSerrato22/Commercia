package com.sena.crud_basic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.courtDTO;
//extedemos de JPA para generar la conexión
//JpaRepository<modelo,tipoPK>
public interface ICourtRepository extends JpaRepository<courtDTO,Integer>{

    /*
     * C
     * R
     * U
     * D
     */
    
} 
