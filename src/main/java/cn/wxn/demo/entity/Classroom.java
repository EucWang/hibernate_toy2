package cn.wxn.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "u_classroom")
public class Classroom {

	@Id
	@GeneratedValue
	private Integer cid;

	@Column(name = "name")
	private String name;

	private Integer grade;

	@OneToMany(mappedBy = "classroom")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Student> students;

	@ManyToOne
	@JoinColumn(name = "special_id")
	private Special special;

	
	
	
	@Override
	public String toString() {
		return "Classroom [cid=" + cid + ", name=" + name + ", grade=" + grade + "]";
	}

	public Classroom() {
		super();
		students = new HashSet<Student>();
	}
	
	public void addStudent(Student student){
		this.students.add(student);
	}

	public Classroom(Integer cid) {
		super();
		this.cid = cid;
	}

	public Classroom(String name, Integer grade , Special special) {
		super();
		this.name = name;
		this.grade = grade;
		this.special = special;
	}

	public Classroom(String name, Integer grade, Set<Student> students, Special special) {
		super();
		this.name = name;
		this.grade = grade;
		this.students = students;
		this.special = special;
	}

	public Classroom(Integer cid, String name, Integer grade, Set<Student> students, Special special) {
		super();
		this.cid = cid;
		this.name = name;
		this.grade = grade;
		this.students = students;
		this.special = special;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Special getSpecial() {
		return special;
	}

	public void setSpecial(Special special) {
		this.special = special;
	}

}
