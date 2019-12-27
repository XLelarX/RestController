package data.permissions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import static data.Data.*;

@Data
@Entity
@Table(name = PERMISSIONS, schema = SCHEMA_NAME)
@NamedQuery(name = "getByName()", query = "select p from Permissions p where p.name = :name")
@NamedQuery(name = "getAll()", query = "select p from Permissions p")
@SequenceGenerator(name = "dataSequences", sequenceName = SEQUENCE_PREFIX + PERMISSIONS, schema = SCHEMA_NAME, allocationSize = 1)
public class Permissions implements data.Data
{
	@JsonIgnore
	@Id
	@Column(name = ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataSequences")
	private Long id;

	@JsonProperty("name")
	@Column(name = NAME)
	private String name;
}
