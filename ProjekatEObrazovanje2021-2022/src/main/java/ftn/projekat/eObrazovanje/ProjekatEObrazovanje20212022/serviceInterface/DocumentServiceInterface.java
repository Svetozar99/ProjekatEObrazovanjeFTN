package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;


public interface DocumentServiceInterface {

	public List<Document> findAll();
	
	public Document findById(Long id);
	
	public Document save(Document document);
	
	public void delete(Long id);
	
	public List<Document> findByUsername(String username);
	
	public String saveUploadedFile(MultipartFile file) throws IOException;
}
