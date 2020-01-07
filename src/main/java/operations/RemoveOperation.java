package operations;

import data.Data;
import exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.util.List;

@Service
public class RemoveOperation implements Operation
{
	@Autowired
	private DataRepository dataRepository;

	@Override
	public List doOperation(Data data) throws DataBaseException
	{
		Data deletedData = dataRepository.doGet(data);

		dataRepository.doRemove(deletedData);
		return dataRepository.doGetAll(data);
	}
}
