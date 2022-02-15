package com.formacionspring.api.login.services;

import com.formacionspring.api.login.entity.Login;

public interface LoginService {

    public Login findDni(String dni, String contraseña);


}