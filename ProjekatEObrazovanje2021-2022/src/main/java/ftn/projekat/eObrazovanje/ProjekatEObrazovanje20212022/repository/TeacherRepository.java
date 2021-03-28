package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Teacher findOneByUser_username(String username);
}
