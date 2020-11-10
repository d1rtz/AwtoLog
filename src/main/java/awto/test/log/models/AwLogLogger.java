package awto.test.log.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.JoinColumn;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "awlog_logger")
public class AwLogLogger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1505833874489816754L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="host")
	private String host;
	
	@Column(name="origin")
	private String origin;
	
	@Column(name="details")
	private String details;

	@Column(name="stacktrace")
	private String stacktrace;
	
	@ManyToMany
	@JoinTable(
			  name = "awlog_logger_hashtag", 
			  joinColumns = @JoinColumn(name = "log_id"), 
			  inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	Set<AwLogHashTag> awLogHashTags = new HashSet<>();
	
	
	public void addHashTag(AwLogHashTag awLogHashTag) {
        this.awLogHashTags.add(awLogHashTag);
        awLogHashTag.getAwLogLoggers().add(this);
    }
	
	
 
}
