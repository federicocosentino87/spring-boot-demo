package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Comment extends Auditable{

	public Comment(String body, Link link) {
		this.body = body;
		this.link = link;
	}
	
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String body;
    
    @ManyToOne
    @NonNull
    private Link link;
}