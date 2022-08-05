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
import javax.validation.constraints.Size;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Please Enter Patient Name")
	@Size(min = 3, message = "Please Enter Name Of Size Atleast Of Size 3")
	private String name;

	@NotBlank(message = "Please Enter Patient City")
	@Size(min = 3, message = "Please Enter City Of Size Atleast 10")
	private String city;

	@NotBlank(message = "Please Enter Patient Email")
	@Email(message = "Please Enter Correct Email Address")
	private String email;

	@NotBlank(message = "Please Enter Patient Phone")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please Enter Correct Phone Number, Format('+xxx xxxxxxxxxx')")
	@Size(min = 10, message = "Please Enter Phone Number Of Size Atleast 10 + Country Code")
	private String phone;

	@NotBlank(message = "Please Enter Patient Symptom")
	@Pattern(regexp = "^(?i)Arthritis(?-i)|(?i)Backpain(?-i)|(?i)Tissue injuries(?-i)|(?i)Dysmenorrhea(?-i)|(?i)Skin infection(?-i)|(?i)skin burn(?-i)||(?i)Ear pain(?-i)$", message = "Wrong Symptom For Patient, Choose('Arthritis','Backpain','Tissue injuries','Dysmenorrhea','Skin infection','skin burn','Ear pain')")
	private String symptom;

	@Transient
	private List<Appointment> appointment;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int id,
			@NotBlank(message = "Please Enter Patient Name") @Size(min = 3, message = "Please Enter Name Of Size Atleast Of Size 3") String name,
			@NotBlank(message = "Please Enter Patient City") @Size(min = 10, message = "Please Enter City Of Size Atleast 10") String city,
			@NotBlank(message = "Please Enter Patient Email") @Email(message = "Please Enter Correct Email Address") String email,
			@NotBlank(message = "Please Enter Patient Phone") @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please Enter Correct Phone Number, Format('+xxx xxxxxxxxxx')") @Size(min = 10, message = "Please Enter Phone Number Of Size Atleast 10 + Country Code") String phone,
			@NotBlank(message = "Please Enter Patient Symptom") @Pattern(regexp = "^(?i)Arthritis(?-i)|(?i)Backpain(?-i)|(?i)Tissue injuries(?-i)|(?i)Dysmenorrhea(?-i)|(?i)Skin infection(?-i)|(?i)skin burn(?-i)||(?i)Ear pain(?-i)$", message = "Wrong Symptom For Patient, Choose('Arthritis','Backpain','Tissue injuries','Dysmenorrhea','Skin infection','skin burn','Ear pain')") String symptom,
			List<Appointment> appointment) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phone = phone;
		this.symptom = symptom;
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

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + ", phone=" + phone
				+ ", symptom=" + symptom + ", appointment=" + appointment + "]";
	}

}
