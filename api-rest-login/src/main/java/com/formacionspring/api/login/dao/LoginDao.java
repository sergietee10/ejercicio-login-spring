package com.formacionspring.api.login.dao;

import org.springframework.stereotype.Repository;

import com.formacionspring.api.login.entity.Login;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface LoginDao extends CrudRepository<Login, Long> {

    @Query("SELECT u FROM Login u WHERE u.dni = ?1 and u.contraseña = ?2")
    public Login findDni(String dni, String contraseña);





}