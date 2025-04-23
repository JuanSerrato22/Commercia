package com.sena.crud_basic.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.clientcourtDTO;
//extedemos de JPA para generar la conexión
//JpaRepository<modelo,tipoPK>
public interface IClientCourtRepository extends JpaRepository<clientcourtDTO,Integer>{

    /*
     * C
     * R
     * U
     * D
     */
    
} 
