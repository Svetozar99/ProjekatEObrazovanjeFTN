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

@SuppressWarnings("serial")
@Entity
@Table(name = "exams")
public class Exam extends JpaEntity {
	
	@Column(name = "points", nullable = false)
	private Integer points;
	
	@Column(name = "gradle", nullable = false)
	private Integer gradle;
	
	@ManyToOne
	@JoinColumn(name="enrollment_id", referencedColumnName="id", nullable=false)
	private Enrollment enrollment;

	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "exam")
	private List<ExamPart> examParts = new ArrayList<ExamPart>();

	public Exam() {
		super();
		this.gradle = 5;
		// TODO Auto-generated constructor stub
	}

	public Exam(Long id, Integer points, Integer gradle, Enrollment enrollment, List<ExamPart> examParts) {
		super(id);
		this.points = points;
		this.gradle = gradle;
		this.enrollment = enrollment;
		this.examParts = examParts;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getGradle() {
		return gradle;
	}

	public void setGradle() {
		if(this.points >= 51 && this.points < 61) {
			this.gradle = 6;
		}else if(this.points >= 61 && this.points < 71) {
			this.gradle = 7;
		}else if(this.points >= 71 && this.points < 81) {
			this.gradle = 8;
		}else if(this.points >= 81 && this.points < 91) {
			this.gradle = 9;
		}else if(this.points >= 91 && this.points <= 100) {
			this.gradle = 10;
		}
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public List<ExamPart> getExamParts() {
		return examParts;
	}

	public void setExamParts(List<ExamPart> examParts) {
		this.examParts = examParts;
	}
	
	
}
