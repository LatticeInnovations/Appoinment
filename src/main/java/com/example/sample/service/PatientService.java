package com.example.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.dao.AppointmentRepository;
import com.example.sample.dao.DoctorRepository;
import com.example.sample.dao.PatientRepository;
import com.example.sample.entity.Appointment;
import com.example.sample.entity.Doctor;
import com.example.sample.entity.Patient;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	public Patient add_Patient(Patient patient) {

		Patient pat = null;
		try {
			patient.setCity(patient.getCity().toUpperCase());
			patient.setSymptom(patient.getSymptom().toUpperCase());
			pat = patientRepository.save(patient);
		} catch (Exception e) {
		}
		return pat;

	}

	public boolean delete_patient(int id) {

		try {
			appointmentRepository.deleteByPatId(id);
		} catch (Exception e) {
		}

		try {
			patientRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Patient> get_patients() {

		List<Patient> patients = new ArrayList<>();
		try {
			patients = (List<Patient>) patientRepository.findAll();
			for (Patient p : patients) {
				List<Appointment> appointments = appointmentRepository.findByPatId(p.getId());
				for (Appointment a : appointments) {
					Optional<Doctor> opt = doctorRepository.findById(a.getDocId());
					Doctor d = opt.get();
					HashMap<String, Object> details = new HashMap<>();
					details.put("Doctor_Name", d.getName());
					details.put("Doctor_Email", d.getEmail());
					details.put("Doctor_Phone", d.getPhone());
					details.put("Doctor_Speciality", d.getSpeciality());
					a.setDetails(details);
				}
				p.setAppointment(appointments);
			}
		} catch (Exception e) {
		}
		return patients;

	}

	public Patient get_patient(int id) {

		try {
			Optional<Patient> opt = patientRepository.findById(id);
			Patient patient = opt.get();
			List<Appointment> appointments = appointmentRepository.findByPatId(patient.getId());
			for (Appointment a : appointments) {
				Optional<Doctor> opt1 = doctorRepository.findById(a.getDocId());
				Doctor d = opt1.get();
				HashMap<String, Object> details = new HashMap<>();
				details.put("Doctor_Name", d.getName());
				details.put("Doctor_Email", d.getEmail());
				details.put("Doctor_Phone", d.getPhone());
				details.put("Doctor_Speciality", d.getSpeciality());
				a.setDetails(details);
			}

			patient.setAppointment(appointments);
			return patient;
		} catch (Exception e) {
			return null;
		}
	}

}
