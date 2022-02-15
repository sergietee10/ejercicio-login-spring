package com.formacionspring.api.login.services;

import java.util.List;
import com.formacionspring.api.login.entity.Jefe;

public interface JefeService {

	public List<Jefe> findAll();

	public Jefe findById(Long id);

	public Jefe save(Jefe cliente);

	public void delete(Long id);

}
