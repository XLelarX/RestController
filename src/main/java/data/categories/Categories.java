package data.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import static data.Data.*;

@Data
@Entity
@Table(name = CATEGORIES, schema = SCHEMA_NAME)
@NamedQuery(name = CATEGORIES + GETALL_QUERY, query = "select c from Categories c")
@SequenceGenerator(name = "categoriesSequence", sequenceName = SEQUENCE_PREFIX + CATEGORIES, schema = SCHEMA_NAME, allocationSize = 1)
public class Categories implements data.Data
{
	@JsonIgnore
	@Id
	@Column(name = ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesSequence")
	private Long id;

	@JsonProperty("name")
	@Column(name = NAME)
	private String name;
}
