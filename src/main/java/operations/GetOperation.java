package operations;

import data.Data;
import exceptions.FindException;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

@Service
public class GetOperation<T extends Data>
{
	private DataRepository<T> dataRepository;

	public GetOperation(Class dataClass)
	{
		dataRepository = new DataRepository(dataClass);
	}

	public T doOperation(Long id) throws FindException
	{
		return dataRepository.doGet(id);
	}
}
