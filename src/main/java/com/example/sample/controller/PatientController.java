package com.example.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
import com.example.sample.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/add")
	public ResponseEntity<String> addPatient(@Valid @RequestBody Patient patient, BindingResult result) {

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
		Patient p = patientService.add_Patient(patient);
		if (p == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Try Again Later");
		return ResponseEntity.ok("Patient Created Successfully");

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") int id) {

		if (patientService.delete_patient(id))
			return ResponseEntity.ok("Patient Deleted Successfully");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error 1 : Please Check The Id Or Try Again Later");

	}

	@GetMapping("/")
	public ResponseEntity<List<Patient>> getPatients() {

		List<Patient> patients = patientService.get_patients();
		if (patients.size() > 0)
			return ResponseEntity.ok(patients);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable("id") int id) {

		Patient patient = patientService.get_patient(id);
		if (patient == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(patient);

	}

	@GetMapping("/suggestsymptom/{symptom}")
	public ResponseEntity<Object> suggestDoctorBySymptom(@PathVariable("symptom") String symptom) {

		List<Doctor> doctors2 = doctorService.get_doctor_speciality_symptom(symptom.toUpperCase());
		if (doctors2.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new String("There isn’t any doctor present for your symptom"));

		List<HashMap<String, Object>> doctors_map = new ArrayList<>();
		for (Doctor d : doctors2) {
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("id", d.getId());
			hashMap.put("name", d.getName());
			hashMap.put("city", d.getCity());
			hashMap.put("email", d.getEmail());
			hashMap.put("phone", d.getPhone());
			hashMap.put("speciality", d.getSpeciality());
			doctors_map.add(hashMap);
		}
		return ResponseEntity.ok().body(doctors_map);

	}
	
	@GetMapping("/suggest/{id}")
	public ResponseEntity<Object> suggestDoctor(@PathVariable("id") int id) {

		List<Doctor> doctors = doctorService.get_doctor_city(id);
		if (doctors.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new String("We are still waiting to expand to your location"));
		}

		List<Doctor> doctors1 = doctorService.get_doctor_speciality(id);
		if (doctors1.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new String("There isn’t any doctor present at your location for your symptom"));

		List<Doctor> doctors2 = doctorService.suggest_doctor(id);
		if (doctors2.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new String("There isn’t any doctor present at your location for your symptom"));

		List<HashMap<String, Object>> doctors_map = new ArrayList<>();
		for (Doctor d : doctors2) {
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("name", d.getName());
			hashMap.put("city", d.getCity());
			hashMap.put("email", d.getEmail());
			hashMap.put("phone", d.getPhone());
			hashMap.put("speciality", d.getSpeciality());
			doctors_map.add(hashMap);
		}
		return ResponseEntity.ok().body(doctors_map);

	}
	
	@PutMapping("/{id}")
	public Patient updatePatient(@RequestBody Patient patient, @PathVariable("id") int id) {
		patientService.updatePatient(patient, id);
		Patient patient2 = patientService.add_Patient(patient);
		return patient2;
	}

}
