package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long>{

	Link findByTitle(String title);
	
	List<Link> findAllByTitleLikeOrderByCreationDateDesc(String title);
}
