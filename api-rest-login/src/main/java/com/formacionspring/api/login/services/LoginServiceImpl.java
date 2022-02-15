package com.formacionspring.api.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.api.login.dao.LoginDao;
import com.formacionspring.api.login.entity.Login;


@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao service;

    @Override
    public Login findDni(String dni, String contraseña) {

        return service.findDni(dni, contraseña);
    }

}
