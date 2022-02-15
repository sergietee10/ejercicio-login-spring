package com.formacionspring.api.login.services;

import java.util.List;
import com.formacionspring.api.login.entity.Empleado;

public interface EmpleadoService {

	public List<Empleado> findAll();

	public Empleado findById(Long id);

	public Empleado save(Empleado cliente);

	public void delete(Long id);

}
