package data.permissions;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionsResponse extends Response
{
	@JsonProperty("permissions")
	private List permissions;
}
