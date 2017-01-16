package cn.wxn.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "u_special")
public class Special {

	@Id
	@GeneratedValue
	private Integer sid;

	@Column(name = "s_name")
	private String name;

	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "special")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Classroom> classrooms;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public Special() {
		super();
		this.classrooms = new HashSet<Classroom>();
	}
	
	public void addClassroom(Classroom classroom)
	{
		this.classrooms.add(classroom);
	}

	public Special(Integer sid, String name, String type, Set<Classroom> classrooms) {
		super();
		this.sid = sid;
		this.name = name;
		this.type = type;
		this.classrooms = classrooms;
	}

	public Special(Integer sid) {
		super();
		this.sid = sid;
	}
	
	public Special(String name, String type, Set<Classroom> classrooms) {
		super();
		this.name = name;
		this.type = type;
		this.classrooms = classrooms;
	}

	public Special(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Special [sid=" + sid + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
