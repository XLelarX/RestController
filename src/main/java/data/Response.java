package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response
{
	@JsonProperty("state")
	State state;

	public Response(State state)
	{
		this.state = state;
	}

	public Response()
	{
	}
}
