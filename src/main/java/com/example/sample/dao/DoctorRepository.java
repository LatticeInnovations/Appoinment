package com.example.sample.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.entity.Doctor;


public interface DoctorRepository extends CrudRepository<Doctor, Integer>{

	public List<Doctor> findBySpecialityAndCity(String speciality,String city);
	
	public List<Doctor> findByCity(String city);
	
	public List<Doctor> findBySpeciality(String speciality);
	
}
