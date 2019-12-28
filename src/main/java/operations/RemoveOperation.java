package operations;

import data.Data;
import data.permissions.Permissions;
import exceptions.FindException;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.util.List;

@Service
public class RemoveOperation<T extends Data>
{
	private DataRepository<T> dataRepository;

	public RemoveOperation(Class dataClass)
	{
		dataRepository = new DataRepository(dataClass);
	}

	public List<T> doOperation(Long id) throws FindException
	{
		T permissions = dataRepository.doGet(id);

		dataRepository.doDelete(permissions);
		return dataRepository.doGetAll();
	}
}
