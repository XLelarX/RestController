package controllers;

import data.Response;
import data.State;
import data.permissions.Permissions;
import data.permissions.PermissionsResponse;
import exceptions.FindException;
import operations.InsertOperation;
import operations.RemoveOperation;
import operations.GetOperation;
import operations.UpdateOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionsController
{
	@PostMapping("/insert")
	public Response doInsert(@RequestBody Permissions savingData)
	{
		InsertOperation<Permissions> operation = new InsertOperation<>(Permissions.class);
		List<Permissions> resultList = operation.doOperation(savingData);

		return chooseResponseAfterInsert(resultList);
	}

	private Response chooseResponseAfterInsert(List<Permissions> resultList)
	{
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

	@GetMapping("/remove")
	public Response doRemove(@RequestParam Long id)
	{
		try
		{
			RemoveOperation<Permissions> operation = new RemoveOperation<>(Permissions.class);
			List<Permissions> resultList = operation.doOperation(id);

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(resultList);
			response.setState(State.SUCCESS);
			return response;
		} catch (FindException e)
		{
			return createFailureResponse(e);
		}
	}

	@GetMapping("/get")
	public Response doGet(@RequestParam Long id)
	{
		try
		{
			GetOperation<Permissions> operation = new GetOperation<>(Permissions.class);
			List<Permissions> result = new ArrayList<>();
			result.add(operation.doOperation(id));

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(result);
			response.setState(State.SUCCESS);

			return response;
		} catch (FindException e)
		{
			return createFailureResponse(e);
		}
	}

	@PostMapping("/update")
	public Response doUpdate(@RequestParam Long id, @RequestBody Permissions savingData)
	{
		try
		{
			UpdateOperation<Permissions> operation = new UpdateOperation<>(Permissions.class);
			List<Permissions> result = new ArrayList<>();
			result.add(operation.doOperation(id, savingData));

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(result);
			response.setState(State.SUCCESS);

			return response;
		} catch (FindException | ReflectiveOperationException e)
		{
			return createFailureResponse(e);
		}
	}

	private Response createFailureResponse(Exception e)
	{
		Class exceptionClass = e.getClass();

		if (exceptionClass.equals(FindException.class))
			return new Response(State.FIND_FAIL);
		if (exceptionClass.equals(ReflectiveOperationException.class))
			return new Response(State.REFLECTIVE_FAIL);
		return new Response(State.FAIL);
	}
}
