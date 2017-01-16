package cn.wxn.demo.entity;


public class StudentDTO{

	private Integer sid;
	
	private String sname;
	
	private String cname;
	
	private Integer grade;
	
	private String speame;

	public StudentDTO() {
		super();
	}

	public StudentDTO(Integer sid, String sname, String cname, Integer grade, String speame) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.cname = cname;
		this.grade = grade;
		this.speame = speame;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getSpeame() {
		return speame;
	}

	public void setSpeame(String speame) {
		this.speame = speame;
	}

	@Override
	public String toString() {
		return "StudentDTO [sid=" + sid + ", sname=" + sname + ", cname=" + cname + ", grade=" + grade + ", speame="
				+ speame + "]";
	}
 
	
}
