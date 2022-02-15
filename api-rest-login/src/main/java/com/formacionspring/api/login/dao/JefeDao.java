package com.formacionspring.api.login.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.api.login.entity.Jefe;


@Repository
public interface JefeDao extends CrudRepository<Jefe, Long> {

}