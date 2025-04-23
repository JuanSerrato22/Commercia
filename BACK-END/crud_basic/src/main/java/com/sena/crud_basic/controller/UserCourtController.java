package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.clientcourtDTO;
import com.sena.crud_basic.service.ClientCourtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class    UserCourtController {

    @Autowired
    private ClientCourtService clientCourtService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerCourt(
        @RequestBody clientcourtDTO clientCourt
        ){
            clientCourtService.save(clientCourt);
        return "register ok";
    }






    @GetMapping("/")
    public ResponseEntity<Object> getClientCourtAll() {
        var prueba=clientCourtService.getAllClientCourt();
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientCourtId(@PathVariable int id) {
        var prueba=clientCourtService.getClientCourtById(id);
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

}
