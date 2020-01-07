package operations;

import data.Data;
import exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetOperation implements Operation
{
	@Autowired
	private DataRepository dataRepository;

	//
//	@Autowired
//	public GetOperation(Class dataClass)
//	{
//		dataRepository = new DataRepository<>(dataClass);
//	}
//
	@Override
	public List doOperation(Data data) throws DataBaseException
	{
		List<Data> resultList = new ArrayList<>();
		resultList.add(dataRepository.doGet(data));

		return resultList;
	}
}
