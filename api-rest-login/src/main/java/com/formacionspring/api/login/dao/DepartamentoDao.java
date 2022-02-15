package com.formacionspring.api.login.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.api.login.entity.Departamento;


@Repository
public interface DepartamentoDao extends CrudRepository<Departamento, Long> {

}
