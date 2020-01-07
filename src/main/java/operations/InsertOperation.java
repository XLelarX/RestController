package operations;

import data.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.util.List;

@Service
//@Qualifier("insertService")
public class InsertOperation implements Operation
{
	@Autowired
	private DataRepository dataRepository;

	@Override
	public List doOperation(Data data)
	{
		dataRepository.doInsert(data);
		return dataRepository.doGetAll(data);
	}
}
