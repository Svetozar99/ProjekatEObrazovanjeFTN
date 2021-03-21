package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.DocumentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.DocumentServiceInterface;

@Service
public class DocumentService implements DocumentServiceInterface {

	@Autowired
	DocumentRepository documentRepository;
	
	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		// TODO Auto-generated method stub
		return documentRepository.getOne(id);
	}

	@Override
	public Document save(Document document) {
		// TODO Auto-generated method stub
		return documentRepository.save(document);
	}

	@Override
	public void delete(Long id) {
		documentRepository.deleteById(id);
	}

}
