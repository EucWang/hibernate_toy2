package cn.wxn.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "u_student")
public class Student {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String gender;
	
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", classroom=" + classroom + "]";
	}

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;

	public Student() {
		super();
	}

	public Student(Integer id, String name, String gender, Classroom classroom) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.classroom = classroom;
	}

	public Student(Integer id) {
		super();
		this.id = id;
	}

	public Student(String name, String gender, Classroom classroom) {
		super();
		this.name = name;
		this.gender = gender;
		this.classroom = classroom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}
