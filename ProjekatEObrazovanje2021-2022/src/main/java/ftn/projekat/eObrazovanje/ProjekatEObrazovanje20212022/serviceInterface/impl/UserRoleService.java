package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.UserRoleRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserRoleServiceI;

public class UserRoleService implements UserRoleServiceI {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole findById(Long id) {
		return userRoleRepository.getOne(id);
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public void delete(Long id) {
		userRoleRepository.deleteById(id);
	}

}
