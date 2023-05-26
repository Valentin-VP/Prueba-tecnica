package com.parameta.test.service;

import com.parameta.test.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO getById(String id);
    EmpleadoDTO save(EmpleadoDTO empleadoDTO);
}
