package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;

public interface CourseSpecificationRepository extends JpaRepository<CourseSpecification, Long> {

	CourseSpecification findOneByCode(String code);
}
