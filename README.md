# Prueba-tecnica

La prueba esta hecha solamente con los servicio rest, se saltea el pedido al servicio SOAP, pero los guarda de todas formas en la BD mysql.
Para probar el servicio rest se pueden usar los siguientes endpoints:

## Endpoints

#### Crear Empleado

```http
  GET /empleados
```

##### Empleado de ejemplo

```json
{
    "numeroDocumento":"ABCDE123",
    "nombre":"Juan",
    "apellido":"Pérez",
    "tipoDocumento":"identificación",
    "fechaNacimiento":"1990-01-01",
    "fechaVinculacion":"2021-01-01",
    "cargo":"analista de sistemas",
    "salario":"50000.00"
}
```


#### Buscar empleado por id

```http
  GET /empleados/${id}
```

| Parametro | Tipo     | Descripcion                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id del empleado a buscar |