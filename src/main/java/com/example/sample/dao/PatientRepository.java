package com.example.sample.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

}
