package app.dto;

import java.util.ArrayList;

import app.models.CourseModel;

public class StudentResponseDTO {
	private int id;
	private String name;
	private String photo;
	private ArrayList<CourseModel> course;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public ArrayList<CourseModel> getCourse() {
		return course;
	}
	public void setCourse(ArrayList<CourseModel> course) {
		this.course = course;
	}
	
	
}
