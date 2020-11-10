package awto.test.log.dto;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwLogHashTagDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3577675214354490348L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("description")
	private String description;	
	
}
