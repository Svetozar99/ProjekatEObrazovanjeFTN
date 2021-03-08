package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "teachers")
public class Teacher extends User {

	@Column(name = "teacher_role")
	private TeacherRole teacherRole;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<CourseSpecification> listCourses;

	public Teacher() {
		super();
	}
	
	public Teacher(Long id, String firstName, String lastName, String username, String password, UserRole role, TeacherRole roleTeacher) {
		super(id, firstName, lastName, username, password, role);
		this.teacherRole = roleTeacher;
	}

	public TeacherRole getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(TeacherRole teacherRole) {
		this.teacherRole = teacherRole;
	}

	public Set<CourseSpecification> getListCourses() {
		return listCourses;
	}

	public void setListCourses(Set<CourseSpecification> listCourses) {
		this.listCourses = listCourses;
	}
}
