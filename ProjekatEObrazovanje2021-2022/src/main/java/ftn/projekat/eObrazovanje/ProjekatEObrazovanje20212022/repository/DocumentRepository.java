package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByStudent_id(Long id);
	List<Document> findByStudent_user_username(String username);
}
