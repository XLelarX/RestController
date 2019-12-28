package operations;

import data.Data;
import exceptions.FindException;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateOperation<T extends Data>
{
	private DataRepository<T> dataRepository;
	private Class dataClass;

	public UpdateOperation(Class dataClass)
	{
		this.dataClass = dataClass;
		dataRepository = new DataRepository(dataClass);
	}

	public T doOperation(Long id, T data) throws FindException, ReflectiveOperationException
	{
		T permissions = dataRepository.doGet(id);

		Method[] dataDeclaredMethods = dataClass.getDeclaredMethods();
		List<Method> necessaryMethods = new ArrayList<>();

		for (Method method : dataDeclaredMethods)
		{
			String necessaryMethodName = method.getName();

			if (necessaryMethodName.startsWith("set") || necessaryMethodName.startsWith("get"))
				necessaryMethods.add(method);
		}

		for (Method setMethod : necessaryMethods)
		{
			String setMethodName = setMethod.getName();

			if (setMethodName.startsWith("set"))
			{
				String fieldName = setMethodName.substring(3);

				if (!fieldName.equals("Id"))
				{
					for (Method getMethod : necessaryMethods)
					{
						if (getMethod.getName().equals("get" + fieldName))
						{
							Object fieldValue = getMethod.invoke(data);

							if (fieldValue != null)
								setMethod.invoke(permissions, fieldValue);
						}
					}
				}
			}
		}

		dataRepository.doUpdate(permissions);
		return permissions;
	}
}
