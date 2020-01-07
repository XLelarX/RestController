package operations;

import data.Data;
import exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DataRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateOperation implements Operation
{
	@Autowired
	private DataRepository dataRepository;

	@Override
	public List doOperation(Data data) throws DataBaseException
	{
		Data permissions = dataRepository.doGet(data);

		Method[] dataDeclaredMethods = permissions.getClass().getDeclaredMethods();
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
							try
							{
								Object fieldValue = getMethod.invoke(data);

								if (fieldValue != null)
									setMethod.invoke(permissions, fieldValue);

							} catch (ReflectiveOperationException e)
							{
								throw new DataBaseException("Проблемы с обновлением данных");
							}
						}
					}
				}
			}
		}

		dataRepository.doUpdate(permissions);
		List resultList = new ArrayList();
		resultList.add(permissions);

		return resultList;
	}
}
