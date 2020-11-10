package awto.test.log.dto;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwLogLoggerDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8990807731146883586L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("creation_date")
	private Date creationDate;	
	
	@JsonProperty("host")
	private String host;
	
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("details")
	private String details;
	
	@JsonProperty("stacktrace")
	private String stacktrace;
	
	@JsonProperty("hashtags")
	private Set<String> hashtags= new HashSet<>();
}
