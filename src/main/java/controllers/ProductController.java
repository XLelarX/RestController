package controllers;

import data.Response;
import data.State;
import data.permissions.Permissions;
import data.permissions.PermissionsResponse;
import exceptions.PostException;
import operations.DeleteOperation;
import operations.GetOperation;
import operations.Operation;
import operations.InsertOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
	@PostMapping("/data")
	public Response doOperation(@RequestParam(value = "operation") String operationName, @RequestBody Permissions savingData) throws PostException
	{
		Operation operation = createOperation(operationName);

		List<Permissions> resultList = operation.doOperation(savingData);

		if (!resultList.isEmpty())
		{
			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(resultList);
			response.setState(State.SUCCESS);

			return response;
		} else
		{
			Response response = new Response();
			response.setState(State.FAIL);

			return response;
		}
	}

	private Operation createOperation(String operationName) throws PostException
	{
		if (operationName.equals("insert"))
			return new InsertOperation();

		if (operationName.equals("get"))
			return new GetOperation();

		if (operationName.equals("remove"))
			return new DeleteOperation();

		throw new PostException("Не существует введенной операции");
	}
}
