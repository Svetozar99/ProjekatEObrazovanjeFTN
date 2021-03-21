package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;


public interface DocumentTypeServiceInterface {

	public List<TypeDocument> findAll();
	
	public TypeDocument findById(Long id);
	
	public TypeDocument save(TypeDocument typeDocument);
	
	public void delete(Long id);
}
