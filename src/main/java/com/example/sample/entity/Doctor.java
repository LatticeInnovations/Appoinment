package com.example.sample.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Please Enter Doctor Name")
	private String name;

	@NotBlank(message = "Please Enter Doctor City")
	@Pattern(regexp = "^(?i)Delhi(?-i)|(?i)Noida(?-i)|(?i)Faridabad(?-i)$", message = "Wrong City For Doctor, Choose('Delhi','Noida','Faridabad')")
	private String city;

	@NotBlank(message = "Please Enter Doctor Email")
	@Email
	private String email;

	@NotBlank(message = "Please Enter Doctor Phone Number")
	private String phone;

	@NotBlank(message = "Please Enter Doctor Speciality")
	@Pattern(regexp = "^(?i)Orthopedic(?-i)|(?i)Gynecology(?-i)|(?i)Dermatology(?-i)|(?i)ENT specialist(?-i)$", message = "Wrong Speciality For Doctor, Choose('Orthopedic','Gynecology','Dermatology','ENT specialist')")
	private String speciality;

	@Transient
	private List<Appointment> appointment;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int id, @NotBlank(message = "Please Enter Doctor Name") String name,
			@NotBlank(message = "Please Enter Doctor City") @Pattern(regexp = "^(?i)Delhi(?-i)|(?i)Noida(?-i)|(?i)Faridabad(?-i)$", message = "Wrong City For Doctor, Choose('Delhi','Noida','Faridabad')") String city,
			@NotBlank(message = "Please Enter Doctor Email") String email,
			@NotBlank(message = "Please Enter Doctor Phone Number") String phone,
			@NotBlank(message = "Please Enter Doctor Speciality") @Pattern(regexp = "^(?i)Orthopedic(?-i)|(?i)Gynecology(?-i)|(?i)Dermatology(?-i)|(?i)ENT specialist(?-i)$", message = "Wrong Speciality For Doctor, Choose('Orthopedic','Gynecology','Dermatology','ENT specialist')") String speciality,
			List<Appointment> appointment) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phone = phone;
		this.speciality = speciality;
		this.appointment = appointment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + ", phone=" + phone
				+ ", speciality=" + speciality + ", appointment=" + appointment + "]";
	}

}
