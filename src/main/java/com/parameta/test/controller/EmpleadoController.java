package com.parameta.test.controller;

import com.parameta.test.dto.EmpleadoDTO;
import com.parameta.test.service.EmpleadoService;
import com.parameta.test.service.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@CrossOrigin
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoServiceImpl empleadoService) {
        this.empleadoService = empleadoService;
    }

    //getting por letra
    @GetMapping
    public ResponseEntity<?> saveEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        try {
            EmpleadoDTO savedEmpleado = empleadoService.save(empleadoDTO);
            return new ResponseEntity<>(savedEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        try {
            EmpleadoDTO savedEmpleado = empleadoService.getById(id);
            return new ResponseEntity<>(savedEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
