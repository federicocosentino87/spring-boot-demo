package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Link;
import com.example.demo.repository.LinkRepository;

@Service
public class LinkService {

	private final LinkRepository linkRepository;
	
	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository; 
	}
	
	public List<Link> findAll() {
		return linkRepository.findAll();
	}
	
	public Optional<Link> findById(Long id) {
		return linkRepository.findById(id);
	}
	
	public Link save(Link link) {
		return linkRepository.save(link);
	}
}
