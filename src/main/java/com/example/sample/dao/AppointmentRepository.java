package com.example.sample.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.sample.entity.Appointment;


public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	public List<Appointment> findByDocIdAndAppointDate(int docId, String appointDate);
	
	public List<Appointment> findByDocIdAndAppointDateAndAppointTimeBetween(int docId, String appointDate, String appointTime, String appointTime1);
	
	public List<Appointment> findByDocIdAndAppointDateAndAppointTimeBetweenOrAppointTimeBetween(int docId, String appointDate, String appointTime, String appointTime1, String appointTime2, String appointTime3);
	
	public List<Appointment> findByDocId(int id);
	
	@Modifying
	@Transactional
	@Query("delete from Appointment a where a.docId=:x")
	void deleteByDocId(@Param("x") int id);
	
	public List<Appointment> findByPatId(int id);
	
	@Modifying
	@Transactional
	@Query("delete from Appointment a where a.patId=:x")
	void deleteByPatId(@Param("x") int id);
	
}