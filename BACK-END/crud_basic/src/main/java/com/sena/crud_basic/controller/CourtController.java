package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.courtDTO;
import com.sena.crud_basic.service.CourtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class    CourtController {

    @Autowired
    private CourtService courtService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerCourt(
        @RequestBody courtDTO court
        ){
            courtService.save(court);
        return "register ok";
    }






    @GetMapping("/")
    public ResponseEntity<Object> getCourtAll() {
        var prueba=courtService.getAllCourt();
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourtId(@PathVariable int id) {
        var prueba=courtService.getCourtById(id);
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

}
