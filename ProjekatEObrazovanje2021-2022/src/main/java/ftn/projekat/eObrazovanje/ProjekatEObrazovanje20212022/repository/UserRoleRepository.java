package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;

public interface UserRoleRepository extends JpaRepository<Role, Long> {

}
