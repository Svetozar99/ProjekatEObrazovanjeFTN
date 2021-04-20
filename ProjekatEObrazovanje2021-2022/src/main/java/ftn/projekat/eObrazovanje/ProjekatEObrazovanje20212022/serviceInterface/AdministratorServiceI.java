package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;

public interface AdministratorServiceI {

	public List<Administrator> findAll();
	
	public Administrator findById(Long id);
	
	public Administrator save(Administrator administrator);
	
	public void delete(Long id);
}
