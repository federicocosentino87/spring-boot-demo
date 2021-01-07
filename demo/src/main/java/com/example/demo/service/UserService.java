package com.example.demo.service;


import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService{

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService; 

    public UserService(RoleService roleService, UserRepository userRepository, MailService mailService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        encoder = new BCryptPasswordEncoder();
        this.mailService = mailService;
    }

    public User register(User user) {
    	String secret = "{bcrypt}" + encoder.encode(user.getPassword());
    	user.setEnabled(false);
    	user.setPassword(secret);
    	user.setConfirmPassword(secret);
    	user.addRole(roleService.findByName("ROLE_USER"));
    	user.setActivationCode(UUID.randomUUID().toString());
		save(user);
		sendActivationEmail(user);
		return user;
    }
    
    public User save(User user) {
    	return userRepository.save(user);
    }
    
    @Transactional
    public void saveUsers(User... users) {
        for(User user : users) {
            logger.info("Saving User: " + user.getEmail());
            userRepository.save(user);
        }
    }
    
    public void sendActivationEmail(User user) {
    	mailService.sendActivationEmail(user);
    }
    
    public void sendWelcomeEmail (User user) {
    	mailService.sendWelcomeEmail(user);
    }
    
    public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
        return userRepository.findByEmailAndActivationCode(email,activationCode);
    }
}
