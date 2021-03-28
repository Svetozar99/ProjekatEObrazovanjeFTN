package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.LoginDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.UserDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.security.TokenUtils;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired UserServiceI userService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
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
	
	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO dto, UriComponentsBuilder ucBuilder){
		User existUser = this.userService.findByUsername(dto.getUserName());
		if (existUser != null) {
			r="E-mail already exists!";
			result.put("result", "error");
			result.put("r", r);
			return ResponseEntity.badRequest().body(result);
		}
		if (!isValidEmailAddress(dto.getEmail())) {
			r="E-mail address is not valid!";
			result.put("result", "error");
			result.put("r", r);
			return ResponseEntity.badRequest().body(result);
		}else {
			r="You have successfully registered!";
			User user = this.userServiceImpl.save(dto);
			UserRequest u=new UserRequest(user);
			createCertificate(u);
			user.setCertificate("./data/"+u.getEmail()+".cer");
			User user2=  this.userRepository.save(user);
			System.out.println(user2.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
			result.put("r", r);
			result.put("result", "success");
		}
		
		return ResponseEntity.accepted().body(result);
	}
}
