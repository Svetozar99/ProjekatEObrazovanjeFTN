package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findOneByCardNumber(String cardNumber);
	Student findOneByUser_username(String username);
}
