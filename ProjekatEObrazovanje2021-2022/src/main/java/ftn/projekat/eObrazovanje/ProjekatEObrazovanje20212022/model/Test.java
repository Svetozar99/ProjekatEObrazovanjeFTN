package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tests")
public class Test extends JpaEntity{

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "date_of_event", nullable = false)
	private Date dateOfEvent;
	
	@Column(name = "number_of_points", nullable = false)
	private Integer numberOfPoints;
	
	@ManyToOne
	@JoinColumn(name = "enrollment_id", referencedColumnName = "id", nullable = false)
	private Enrollment enrollment;

	public Test(Long id, String title, Date dateOfEvent, Integer numberOfPoints, Enrollment enrollment) {
		super(id);
		this.title = title;
		this.dateOfEvent = dateOfEvent;
		this.numberOfPoints = numberOfPoints;
		this.enrollment = enrollment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public Integer getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(Integer numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}
	
}
