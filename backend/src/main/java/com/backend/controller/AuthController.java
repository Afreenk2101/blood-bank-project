package com.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {
	@Autowired
	private UserService service;
	
	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> loginData) {
		String email = loginData.get("email");
		String password = loginData.get("password");
		User user = service.findByEmail(email);

		if (user != null && user.getPassword() == password) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "username or password is wrong"));
	}

	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody User user) {
		User createdUser  = service.addUser(user);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(createdUser.getEmail());
		message.setText("Username:"+createdUser.getEmail()+"Password:"+createdUser.getPassword());
		mailSender.send(message);
		
		return ResponseEntity.ok(Map.of("user",createdUser));
	}
	
	@PostMapping("/admin-login")
	public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Map<String, String> adminData){
		String username = "admin";
		String password = "admin";
		
		if (!adminData.get("username").equals(username) && !adminData.get("password").equals(password)) {
			return ResponseEntity.ok(Map.of("error","username or password is wrong"));
		}
		return ResponseEntity.ok(null);
	}
	
}
