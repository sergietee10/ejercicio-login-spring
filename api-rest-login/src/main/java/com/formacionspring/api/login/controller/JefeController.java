package com.formacionspring.api.login.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import com.formacionspring.api.login.entity.Jefe;
import com.formacionspring.api.login.services.JefeService;


@RestController
@RequestMapping("/api")
public class JefeController {

	@Autowired
	private JefeService servicio;

	@GetMapping("/jefes")
	public List<Jefe> jefe() {
		return servicio.findAll();
	}


	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> jefeShow(@PathVariable Long id) {
		Jefe jefe = null;
		Map<String, Object> response = new HashMap<>();

		try {
			jefe = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (jefe == null) {
			response.put("mensaje", "El Jefe ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
        return new ResponseEntity<Jefe>(jefe, HttpStatus.OK);

	}


	@PostMapping("/jefes")
	public ResponseEntity<?> saveJefe(@RequestBody Jefe jefe) {
		Jefe jefeNew = null;
		Map<String, Object> response = new HashMap<>();

		try {

			jefeNew = servicio.save(jefe);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El jefe ha sido creado con éxito!");
		response.put("jefe", jefeNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}


	@PutMapping("/jefes/{id}")
	public ResponseEntity<?> updateJefe(@RequestBody Jefe jefe, @PathVariable Long id) {

		Jefe jefeActual = servicio.findById(id);

		Map<String, Object> response = new HashMap<>();

		try {

			if (jefeActual == null) {
				response.put("mensaje",
						"El jefe ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}

			jefeActual.setNombre(jefe.getNombre());
			jefeActual.setDni(jefe.getDni());
			jefeActual.setSalario(jefe.getSalario());
			jefeActual.setTelefono(jefe.getTelefono());
			jefeActual.setDepartamento(jefe.getDepartamento());

			servicio.save(jefeActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El jefe ha sido actualizado con éxito!");
		response.put("jefe", jefeActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}


	//Delete method
	@DeleteMapping("/jefes/{id}")
	public ResponseEntity<?> deleteJefe(@PathVariable Long id) {
		Jefe jefeBorrado = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (jefeBorrado == null) {
			response.put("mensaje", "El jefe ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		} else {

			try {

				servicio.delete(id);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al borrar un jefe la base de datos");
				response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("mensaje", "El jefe ha sido borrado con éxito!");
			response.put("jefe", jefeBorrado);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		}

	}
}
