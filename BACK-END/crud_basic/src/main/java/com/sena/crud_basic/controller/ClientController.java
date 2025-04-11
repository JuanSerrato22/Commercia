package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.ClientDTO;
import com.sena.crud_basic.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class    ClientController {

    @Autowired
    private ClientService clientService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerClient(
        @RequestBody ClientDTO client
        ){
            clientService.save(client);
        return "register ok";
    }






    @GetMapping("/")
    public ResponseEntity<Object> getClientAll() {
        var prueba=clientService.getAllClient();
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientId(@PathVariable int id) {
        var prueba=clientService.getClientById(id);
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

}
