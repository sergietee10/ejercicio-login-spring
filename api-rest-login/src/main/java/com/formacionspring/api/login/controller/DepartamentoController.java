package com.formacionspring.api.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import com.formacionspring.api.login.entity.Departamento;
import com.formacionspring.api.login.services.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {

	@Autowired
	private DepartamentoService servicio;

	@GetMapping("/departamentos")
	public List<Departamento> departamento() {
		return servicio.findAll();
	}

	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> departamentoShow(@PathVariable Long id) {
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();

		try {
			departamento = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (departamento == null) {
			response.put("mensaje", "El departamento ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
        return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);

	}

	@PostMapping("/departamentos")
	public ResponseEntity<?> saveDepartamento(@RequestBody Departamento departamento) {
		Departamento departamentoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {

			departamentoNew = servicio.save(departamento);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El departamento ha sido creado con éxito!");
		response.put("empleado", departamentoNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/departamentos/{id}")
	public ResponseEntity<?> updateDepartamento(@RequestBody Departamento departamento, @PathVariable Long id) {

		Departamento departamentoActual = servicio.findById(id);

		Map<String, Object> response = new HashMap<>();

		try {

			if (departamentoActual == null) {
				response.put("mensaje",
						"El departamento ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}

			departamentoActual.setNombre(departamento.getNombre());
			departamentoActual.setUbicacion(departamento.getUbicacion());

			servicio.save(departamentoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El departamento ha sido actualizado con éxito!");
		response.put("departamento", departamentoActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Delete method
//	@DeleteMapping("/departamentos/{id}")
//	public ResponseEntity<?> deleteDepartamento(@PathVariable Long id) {
//		Departamento departamentoBorrado = servicio.findById(id);
//		Map<String, Object> response = new HashMap<>();
//
//		if (departamentoBorrado == null) {
//			response.put("mensaje", "El departamento ID: ".concat(id.toString().concat(" no existe en la base de datos")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//
//		} else {
//
//			try {
//
//				servicio.delete(id);
//
//			} catch (DataAccessException e) {
//				response.put("mensaje", "Error al borrar un departamento la base de datos");
//				response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
//
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//
//			}
//			response.put("mensaje", "El departamento ha sido borrado con éxito!");
//			response.put("departamento", departamentoBorrado);
//
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//
//		}
//
//	}
}
