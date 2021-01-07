package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Vote;
import com.example.demo.repository.VoteRepository;

@Service
public class VoteService {
	
	private final VoteRepository voteRepository;
	
	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

}
