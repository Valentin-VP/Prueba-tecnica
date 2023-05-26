package com.parameta.test.service;

import com.parameta.test.dto.EmpleadoDTO;
import com.parameta.test.model.Empleado;
import com.parameta.test.repository.EmpleadoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoDTO getById(String id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return empleado.get().toEmpleadoDto();
        } else {
            throw new RuntimeException("No se encontro el empleado con el id: " + id);
        }
    }

    @Override
    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) throws RuntimeException {

        Field[] fields = empleadoDTO.getClass().getDeclaredFields();
        List<String> missingFields = new ArrayList<>();
        //chquea si todos los campos estan presentes
        for (Field field : fields) {
            if (field.getName().equals("tiempoVinculacion") || field.getName().equals("edad")) {
                continue;
            }
            field.setAccessible(true);
            try {
                if (field.get(empleadoDTO) == null || field.get(empleadoDTO).toString().isEmpty()) {
                    missingFields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //retorna un error si algun campo esta vacio
        if (missingFields.size() > 0) {
            String errorMessage = "Los siguientes campos son requeridos: ";
            for (String missingField : missingFields) {
                errorMessage = errorMessage.concat(missingField).concat(", ");
            }

            errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        //Chequea si la fecha esta en el formato correcto y es mayor de edad
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date fechaVinculacion = dateFormat.parse(empleadoDTO.getFechaVinculacion());
            Date fechaNacimiento = dateFormat.parse(empleadoDTO.getFechaNacimiento());
            Date fechaActual = new Date();

            Period edadPeriod = Period.between(fechaNacimiento.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), fechaActual.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            if (edadPeriod.getYears() < 18) {
                throw new RuntimeException("El empleado debe ser mayor de edad");
            }

            Empleado empleado = empleadoDTO.toEmpleado();
            empleadoRepository.save(empleado);
            return empleadoDTO;


        } catch (ParseException e) {
            throw new RuntimeException("Las fechas deben estar en formato yyyy-MM-dd");
        }
    }

}
