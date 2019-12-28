package data.permissions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import static data.Data.*;

@Data
@Entity
@Table(name = PERMISSIONS, schema = SCHEMA_NAME)
@NamedQuery(name = PERMISSIONS + GETALL_QUERY, query = "select p from Permissions p")
@SequenceGenerator(name = "permissionsSequence", sequenceName = SEQUENCE_PREFIX + PERMISSIONS, schema = SCHEMA_NAME, allocationSize = 1)
public class Permissions implements data.Data
{
	@JsonIgnore
	@Id
	@Column(name = ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSequence")
	private Long id;

	@JsonProperty("name")
	@Column(name = NAME)
	private String name;
}
