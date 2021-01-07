package com.example.demo.domain;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.example.demo.service.BeanUtil;

import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

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
	@NotEmpty(message = "Please enter a title.")
	private String title;
	
	@NonNull
	@NotEmpty(message = "Please enter a url.")
	@URL(message = "Please enter a valid url.")
    private String url;
   
	@OneToMany(mappedBy="link")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "link")
    private List<Vote> votes = new ArrayList<>();

    private int voteCount = 0;
    
    @ManyToOne
    private User user;
	
	public void addComment(Comment comment) {
        comments.add(comment);
	}
	
	public String getDomainName() throws URISyntaxException {
		URI uri = new URI(this.url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}
	
	public String getPrettyTime() {
		PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
		return pt.format(convertToDateViaInstant(getCreationDate()));
	}
	
	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
}
