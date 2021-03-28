package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.LoginDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.UserDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.security.TokenUtils;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AdministratorServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired 
	private UserServiceI userService;
	
	@Autowired
	private StudentServiceI studentS;
	
	@Autowired
	private AdministratorServiceI adminS;
	
	@Autowired
	private TeacherServiceI teachS;
	
	@Autowired
	private AccountServiceI accountS;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO dto, UriComponentsBuilder ucBuilder){
		User existUser = this.userService.findByUsername(dto.getUserName());
		if (existUser != null) {
			return ResponseEntity.status(409).build();
		}
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setUsername(dto.getUserName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		if(dto.getRole().equals("st")) {
			Student student = new Student();
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String cardNumber = "S/"+studentS.maxId()+"-"+calendar.get(Calendar.YEAR);
			student.setCardNumber(cardNumber);
			student.setUser(user);
			studentS.save(student);
			
			Account account = new Account();
			account.setStudent(student);
			accountS.save(account);
		}else if(dto.getRole().equals("teach")) {
			Teacher teacher = new Teacher();
			teacher.setUser(user);
			teachS.save(teacher);
		}else if(dto.getRole().equals("admin")) {
			Administrator admin = new Administrator();
			admin.setUser(user);
			adminS.save(admin);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		
		return ResponseEntity.accepted().build();
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}
