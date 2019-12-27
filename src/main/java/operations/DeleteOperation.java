package operations;

import data.Data;
import data.permissions.Permissions;
import org.springframework.stereotype.Service;
import repositories.DataRepository;
import repositories.PermissionsRepository;

import java.util.List;

@Service
public class DeleteOperation implements Operation
{
	private DataRepository dataRepository = new PermissionsRepository();

	@Override
	public List<Permissions> doOperation(Data data)
	{
		dataRepository.doDelete(data);
		return dataRepository.doGetAll();
	}
}
