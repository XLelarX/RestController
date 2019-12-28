package operations;

import data.Data;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.util.List;

@Service
public class InsertOperation<T extends Data>
{
	private DataRepository dataRepository;

	public InsertOperation(Class dataClass)
	{
		dataRepository = new DataRepository(dataClass);
	}

	public List<T> doOperation(T data)
	{
		dataRepository.doInsert(data);
		return dataRepository.doGetAll();
	}
}
