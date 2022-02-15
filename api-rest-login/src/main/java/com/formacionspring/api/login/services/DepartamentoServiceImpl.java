package com.formacionspring.api.login.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacionspring.api.login.dao.DepartamentoDao;
import com.formacionspring.api.login.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao departamentoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {

		return (List<Departamento>) departamentoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento findById(Long id) {

		return departamentoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) {

		return departamentoDao.save(departamento);
	}

	@Override
	public void delete(Long id) {

		departamentoDao.deleteById(id);

	}

}
