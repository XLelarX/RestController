package data.categories;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoriesResponse extends Response
{
	@JsonProperty("categories")
	private List<Categories> categories;
}
