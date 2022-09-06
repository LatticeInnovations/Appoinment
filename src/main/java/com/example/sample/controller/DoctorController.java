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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.entity.Doctor;
import com.example.sample.entity.Patient;
import com.example.sample.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/add")
	public ResponseEntity<String> addDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {

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
		Doctor d = doctorService.add_Doctor(doctor);
		if (d == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Try Again Later");
		return ResponseEntity.ok("Doctor Created Successfully");

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("id") int id) {

		if (doctorService.delete_doctor(id))
			return ResponseEntity.ok("Doctor Deleted Successfully");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error 1 : Please Check The Id Or Try Again Later");

	}

	@GetMapping("/")
	public ResponseEntity<List<Doctor>> getDoctors() {

		List<Doctor> doctors = doctorService.get_doctors();
		if (doctors.size() > 0)
			return ResponseEntity.ok(doctors);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctor(@PathVariable("id") int id) {

		Doctor doctor = doctorService.get_doctor(id);
		if (doctor == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(doctor);

	}
	
	@PutMapping("/{id}")
	public Doctor updateDoctor(@RequestBody Doctor doctor, @PathVariable("id") int id) {
		doctorService.updateDoctor(doctor, id);
		Doctor doctor2 = doctorService.add_Doctor(doctor);
		return doctor2;
	}

}
