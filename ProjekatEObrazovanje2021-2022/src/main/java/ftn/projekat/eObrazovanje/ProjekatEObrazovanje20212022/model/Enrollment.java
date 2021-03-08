package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment extends JpaEntity {

	@Column(name = "assessment")
	private Integer assessment;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="course_instance_id", referencedColumnName="id", nullable=false)
	private CourseInstance courseInstance;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "enrollment")
	private List<Test> tests = new ArrayList<Test>();

	public Enrollment(Long id, Integer assessment, Student student, CourseInstance courseInstance, List<Test> tests) {
		super(id);
		this.assessment = assessment;
		this.student = student;
		this.courseInstance = courseInstance;
		this.tests = tests;
	}

	public Integer getAssessment() {
		return assessment;
	}

	public void setAssessment(Integer assessment) {
		this.assessment = assessment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseInstance getCourseInstance() {
		return courseInstance;
	}

	public void setCourseInstance(CourseInstance courseInstance) {
		this.courseInstance = courseInstance;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
}
