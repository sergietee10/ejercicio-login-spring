package com.formacionspring.api.login.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import com.formacionspring.api.login.entity.Empleado;
import com.formacionspring.api.login.services.EmpleadoService;


@RestController
@RequestMapping("/api")
public class EmpleadoController {

	@Autowired
	private EmpleadoService servicio;

	@GetMapping("/empleados")
	public List<Empleado> empleado() {
		return servicio.findAll();
	}


	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> empleadoShow(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();

		try {
			empleado = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (empleado == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);

	}


	@PostMapping("/empleados")
	public ResponseEntity<?> saveDepartamento(@RequestBody Empleado empleado) {
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {

			empleadoNew = servicio.save(empleado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El empleado ha sido creado con éxito!");
		response.put("empleado", empleadoNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}


	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> updateEmpleados(@RequestBody Empleado empleado, @PathVariable Long id) {

		Empleado empleadoActual = servicio.findById(id);

		Map<String, Object> response = new HashMap<>();

		try {

			if (empleadoActual == null) {
				response.put("mensaje",
						"El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}

			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			empleadoActual.setDepartamento(empleado.getDepartamento());
			servicio.save(empleadoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El departamento ha sido actualizado con éxito!");
		response.put("empleado", empleadoActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}


	//Delete method
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
		Empleado empleadoBorrado = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (empleadoBorrado == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		} else {

			try {

				servicio.delete(id);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al borrar un empleado la base de datos");
				response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("mensaje", "El empleado ha sido borrado con éxito!");
			response.put("empleado", empleadoBorrado);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		}

	}
}
