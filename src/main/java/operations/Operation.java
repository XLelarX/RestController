package operations;

import data.Data;
import exceptions.DataBaseException;

import java.util.List;

public interface Operation
{
	List doOperation(Data data) throws DataBaseException;
}
