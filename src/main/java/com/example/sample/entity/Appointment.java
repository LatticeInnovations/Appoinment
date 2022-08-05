package com.example.sample.entity;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "Please Enter Patient Id")
	private Integer patId;

	@NotNull(message = "Please Enter Doctor Id")
	private Integer docId;

	@NotBlank(message = "Please Enter Apponitment Date")
	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", message = "Please Enter The Date In Correct Format('dd/MM/yyyy')")
	private String appointDate;

	@NotBlank(message = "Please Enter Apponitment Time")
	@Pattern(regexp = "^(0[1-9]|1[0-2]):[0-5][0-9] ([AP][M])$", message = "Please Enter The Time In Correct Format('hh:mm AM/PM')")
	private String appointTime;

	@Transient
	private HashMap<String, Object> details;

	public Appointment(int id, @NotNull(message = "Please Enter Patient Id") Integer patId,
			@NotNull(message = "Please Enter Doctor Id") Integer docId,
			@NotBlank(message = "Please Enter Apponitment Date") @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", message = "Please Enter The Date In Correct Format('dd/MM/yyyy')") String appointDate,
			@NotBlank(message = "Please Enter Apponitment Time") @Pattern(regexp = "^(0[1-9]|1[0-2]):[0-5][0-9] ([AP][M])$", message = "Please Enter The Time In Correct Format('hh:mm AM/PM')") String appointTime,
			HashMap<String, Object> details) {
		super();
		this.id = id;
		this.patId = patId;
		this.docId = docId;
		this.appointDate = appointDate;
		this.appointTime = appointTime;
		this.details = details;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPatId() {
		return patId;
	}

	public void setPatId(Integer patId) {
		this.patId = patId;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}

	public String getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}

	public HashMap<String, Object> getDetails() {
		return details;
	}

	public void setDetails(HashMap<String, Object> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patId=" + patId + ", docId=" + docId + ", appointDate=" + appointDate
				+ ", appointTime=" + appointTime + ", details=" + details + "]";
	}

}
