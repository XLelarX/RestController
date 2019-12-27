package operations;

import data.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Operation
{
	List doOperation(Data data);
}
