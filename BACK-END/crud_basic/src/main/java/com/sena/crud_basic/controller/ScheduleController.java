package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.scheduleDTO;
import com.sena.crud_basic.service.ScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class    ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerSchedule(
        @RequestBody scheduleDTO schedule
        ){
            scheduleService.save(schedule);
        return "register ok";
    }






    @GetMapping("/")
    public ResponseEntity<Object> getScheduleAll() {
        var prueba=scheduleService.getAllSchedule();
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getScheduleId(@PathVariable int id) {
        var prueba=scheduleService.getScheduleById(id);
        
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

}
