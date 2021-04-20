package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.UserRoleRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserRoleServiceI;

public class UserRoleService implements UserRoleServiceI {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public List<Role> findAll() {
		return userRoleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return userRoleRepository.getOne(id);
	}

	@Override
	public Role save(Role userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public void delete(Long id) {
		userRoleRepository.deleteById(id);
	}

}
