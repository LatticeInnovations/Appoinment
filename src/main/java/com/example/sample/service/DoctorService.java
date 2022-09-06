package com.example.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.dao.AppointmentRepository;
import com.example.sample.dao.DoctorRepository;
import com.example.sample.dao.PatientRepository;
import com.example.sample.entity.Appointment;
import com.example.sample.entity.Doctor;
import com.example.sample.entity.Patient;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientService patientService;

	public Doctor add_Doctor(Doctor doctor) {

		Doctor doc = null;
		try {
			doctor.setCity(doctor.getCity().toUpperCase());
			doctor.setSpeciality(doctor.getSpeciality().toUpperCase());
			doc = doctorRepository.save(doctor);
		} catch (Exception e) {
		}
		return doc;

	}

	public boolean delete_doctor(int id) {

		try {
			appointmentRepository.deleteByDocId(id);
		} catch (Exception e) {
		}

		try {
			doctorRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}

	}

	public List<Doctor> get_doctors() {

		List<Doctor> doctors = new ArrayList<>();
		try {
			doctors = (List<Doctor>) doctorRepository.findAll();
			for (Doctor d : doctors) {
				List<Appointment> appointments = appointmentRepository.findByDocId(d.getId());
				for (Appointment a : appointments) {
					Optional<Patient> opt = patientRepository.findById(a.getPatId());
					Patient p = opt.get();
					HashMap<String, Object> details = new HashMap<>();
					details.put("Patient_Name", p.getName());
					details.put("Patient_Email", p.getEmail());
					details.put("Patient_Phone", p.getPhone());
					details.put("Patient_Symptom", p.getSymptom());
					a.setDetails(details);
				}
				d.setAppointment(appointments);
			}
		} catch (Exception e) {
		}
		return doctors;

	}

	public Doctor get_doctor(int id) {

		try {
			Optional<Doctor> opt = doctorRepository.findById(id);
			Doctor doctor = opt.get();
			List<Appointment> appointments = appointmentRepository.findByDocId(doctor.getId());
			for (Appointment a : appointments) {
				Optional<Patient> opt1 = patientRepository.findById(a.getPatId());
				Patient p = opt1.get();
				HashMap<String, Object> details = new HashMap<>();
				details.put("Patient_Name", p.getName());
				details.put("Patient_Email", p.getEmail());
				details.put("Patient_Phone", p.getPhone());
				details.put("Patient_Symptom", p.getSymptom());
				a.setDetails(details);
			}
			doctor.setAppointment(appointments);
			return doctor;
		} catch (Exception e) {
			return null;
		}

	}

	public String getSpecialization(String symptom) {

		String doctor_spec = "";
		if (symptom.equals("ARTHRITIS") || symptom.equals("BACKPAIN") || symptom.equals("TISSUE INJURIES"))
			doctor_spec = "ORTHOPEDIC";

		else if (symptom.equals("DYSMENORRHEA"))
			doctor_spec = "GYNECOLOGY";

		else if (symptom.equals("SKIN INFECTION") || symptom.equals("SKIN BURN"))
			doctor_spec = "DERMATOLOGY";

		else
			doctor_spec = "ENT SPECIALIST";
		return doctor_spec;

	}

	public List<Doctor> get_doctor_speciality_symptom(String symptom) {
		List<Doctor> doctors = new ArrayList<>();
		try {
			String doctor_spec = getSpecialization(symptom);
			doctors = doctorRepository.findBySpeciality(doctor_spec);
		} catch (Exception e) {
		}
		return doctors;
	}

	public List<Doctor> suggest_doctor(int id) {

		Patient patient = patientService.get_patient(id);
		String symptom = patient.getSymptom();
		String doctor_spec = getSpecialization(symptom);
		List<Doctor> doctors = doctorRepository.findBySpecialityAndCity(doctor_spec, patient.getCity());
		return doctors;

	}

	public List<Doctor> get_doctor_city(int id) {

		List<Doctor> doctors = new ArrayList<>();
		try {
			Patient patient = patientService.get_patient(id);
			doctors = doctorRepository.findByCity(patient.getCity());
		} catch (Exception e) {
		}
		return doctors;

	}

	public List<Doctor> get_doctor_speciality(int id) {

		List<Doctor> doctors = new ArrayList<>();
		try {
			Patient patient = patientService.get_patient(id);
			String symptom = patient.getSymptom();
			String doctor_spec = getSpecialization(symptom);
			doctors = doctorRepository.findBySpeciality(doctor_spec);
		} catch (Exception e) {
		}
		return doctors;

	}

	public void updateDoctor(Doctor doctor, int id) {
		List<Doctor> list = new ArrayList<>();
		list = list.stream().map(p -> {
			if (p.getId() == id) {
				p.setName(p.getName());
				p.setCity(p.getCity());
				p.setEmail(p.getEmail());
				p.setPhone(p.getPhone());
				p.setSpeciality(p.getSpeciality());
			}
			return p;
		}).collect(Collectors.toList());
	}
}
