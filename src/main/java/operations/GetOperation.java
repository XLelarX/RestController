package operations;

import data.Data;
import data.permissions.Permissions;
import org.springframework.stereotype.Service;
import repositories.DataRepository;
import repositories.PermissionsRepository;

import java.util.List;

@Service
public class GetOperation implements Operation
{
	private DataRepository dataRepository = new PermissionsRepository();

	@Override
	public List<Permissions> doOperation(Data data)
	{
		return dataRepository.doGet(((Permissions) data).getName());
	}
}
