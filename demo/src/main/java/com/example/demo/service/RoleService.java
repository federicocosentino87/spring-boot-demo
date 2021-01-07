package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
