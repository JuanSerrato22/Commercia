package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.reservationDTO;
import com.sena.crud_basic.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class    ReservationController {

    @Autowired
    private ReservationService reservationService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerReservation(
        @RequestBody reservationDTO reservation
        ){
            reservationService.save(reservation);
        return "register ok";
    }






    @GetMapping("/")
    public ResponseEntity<Object> getReservationAll() {
        var prueba=reservationService.getAllReservation();
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReservationId(@PathVariable int id) {
        var prueba=reservationService.getReservationById(id);
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

}
