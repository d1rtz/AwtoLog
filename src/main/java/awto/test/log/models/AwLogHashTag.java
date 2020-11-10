package awto.test.log.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "awlog_hashtag")
public class AwLogHashTag implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -877937004870880172L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "description")
	private String description;	
	
	@ManyToMany(mappedBy = "awLogHashTags")
	@JsonIgnore
    private Set<AwLogLogger> awLogLoggers;
	
	
}
