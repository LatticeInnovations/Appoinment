package com.example.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.entity.Appointment;
import com.example.sample.service.AppointmentService;

@RestController
@RequestMapping("/appoint")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/add")
	public ResponseEntity<String> appoint(@Valid @RequestBody Appointment appointment, BindingResult result) {

		String errors = "";
		int i = 1;

		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				errors = errors + "Error " + i + " : " + error.getDefaultMessage() + "\n";
				i++;
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}

		String msg = appointmentService.appoint(appointment);
		if (msg.startsWith("Appointment")) {
			return ResponseEntity.ok().body(msg);
		} else if (msg.startsWith("Please"))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAppoint(@PathVariable("id") int id) {

		if (appointmentService.delete_appoint(id))
			return ResponseEntity.ok("Appointment Deleted Successfully");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error 1 : Please Check The Id Or Try Again Later");

	}

	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAppointment() {
		List<Appointment> appoints = appointmentService.getAppoints();
		if (appoints.size() > 0)
			return ResponseEntity.ok(appoints);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
