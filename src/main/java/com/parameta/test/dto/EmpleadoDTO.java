package com.parameta.test.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;

import com.parameta.test.model.Empleado;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

public class EmpleadoDTO {
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String fechaNacimiento;
    private String fechaVinculacion;
    private String cargo;
    private Double salario;
    private FechaDTO tiempoVinculacion;
    private FechaDTO edad;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(String numeroDocumento, String nombre, String apellido, String tipoDocumento, String fechaNacimiento, String fechaVinculacion, String cargo, Double salario) {
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaVinculacion = fechaVinculacion;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(String fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public FechaDTO getTiempoVinculacion() {
        return tiempoVinculacion;
    }

    public void setTiempoVinculacion(FechaDTO tiempoVinculacion) {
        this.tiempoVinculacion = tiempoVinculacion;
    }

    public FechaDTO getEdad() {
        return edad;
    }

    public void setEdad(FechaDTO edad) {
        this.edad = edad;
    }

    public void setTiempoVinculacion() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date fechaVinculacion = dateFormat.parse(this.fechaVinculacion);
            Period periodo = Period.between(fechaVinculacion.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            FechaDTO tiempoVinculacion = new FechaDTO(periodo.getYears(), periodo.getMonths(), periodo.getDays());
            this.tiempoVinculacion = tiempoVinculacion;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEdad(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date fechaNacimiento = dateFormat.parse(this.fechaNacimiento);
            Period periodo = Period.between(fechaNacimiento.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            FechaDTO edad = new FechaDTO(periodo.getYears(), periodo.getMonths(), periodo.getDays());
            this.edad = edad;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Empleado toEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setNumeroDocumento(this.numeroDocumento);
        empleado.setNombre(this.nombre);
        empleado.setApellido(this.apellido);
        empleado.setTipoDocumento(this.tipoDocumento);

        empleado.setCargo(this.cargo);
        empleado.setSalario(this.salario);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date fechaNacimiento = dateFormat.parse(this.fechaNacimiento);
            empleado.setFechaNacimiento(fechaNacimiento);
            Date fechaVinculacion = dateFormat.parse(this.fechaVinculacion);
            empleado.setFechaVinculacion(fechaVinculacion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return empleado;
    }
}
