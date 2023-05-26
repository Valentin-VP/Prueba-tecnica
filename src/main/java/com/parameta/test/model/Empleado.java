package com.parameta.test.model;

import com.parameta.test.dto.EmpleadoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private Date fechaNacimiento;
    private Date fechaVinculacion;
    private String cargo;
    private Double salario;

    public Empleado() {
    }

    public Empleado(String numeroDocumento, String nombre, String apellido, String tipoDocumento, Date fechaNacimiento, Date fechaVinculacion, String cargo, Double salario) {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
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

    public EmpleadoDTO toEmpleadoDto() {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setNumeroDocumento(this.numeroDocumento);
        empleadoDTO.setNombre(this.nombre);
        empleadoDTO.setApellido(this.apellido);
        empleadoDTO.setTipoDocumento(this.tipoDocumento);
        empleadoDTO.setFechaNacimiento(this.fechaNacimiento.toString());
        empleadoDTO.setFechaVinculacion(this.fechaVinculacion.toString());
        empleadoDTO.setCargo(this.cargo);
        empleadoDTO.setSalario(this.salario);
        empleadoDTO.setTiempoVinculacion();
        empleadoDTO.setEdad();
        return empleadoDTO;
    }
}
