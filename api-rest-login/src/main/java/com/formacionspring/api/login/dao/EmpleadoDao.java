package com.formacionspring.api.login.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.api.login.entity.Empleado;


@Repository
public interface EmpleadoDao extends CrudRepository<Empleado, Long> {

}