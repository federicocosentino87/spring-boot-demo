package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
public class Link extends Auditable{
	
	public Link(String title, String url) {
		this.title = title;
		this.url = url;
	}

	@Id
    @GeneratedValue
    private Long id;
	@NonNull
    private String title;
	@NonNull
    private String url;
   
	@OneToMany(mappedBy="link")
	@ToString.Exclude
	private List<Comment> comments = new ArrayList<>();
	
	public void addComment(Comment comment) {
        comments.add(comment);
    }
}
