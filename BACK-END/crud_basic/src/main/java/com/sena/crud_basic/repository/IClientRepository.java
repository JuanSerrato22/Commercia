package com.sena.crud_basic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.ClientDTO;
//extedemos de JPA para generar la conexión
//JpaRepository<modelo,tipoPK>
public interface IClientRepository extends JpaRepository<ClientDTO,Integer>{

    /*
     * C
     * R
     * U
     * D
     */
    
} 
