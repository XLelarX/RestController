package operations;

import data.Data;
import data.permissions.Permissions;
import org.springframework.stereotype.Service;
import repositories.DataRepository;
import repositories.PermissionsRepository;

import java.util.List;

@Service
public class InsertOperation implements Operation
{
	private DataRepository permissionsRepository = new PermissionsRepository();

	@Override
	public List<Permissions> doOperation(Data data)
	{
		permissionsRepository.doInsert(data);
		return permissionsRepository.doGetAll();
	}
}
