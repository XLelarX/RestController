package controllers;

import data.Response;
import data.State;
import data.permissions.Permissions;
import data.permissions.PermissionsResponse;
import exceptions.DataBaseException;
import operations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionsController
{
	@Autowired
	@Qualifier("insertOperation")
	private Operation insertOperation;

	@Autowired
	@Qualifier("removeOperation")
	private Operation removeOperation;

	@Autowired
	@Qualifier("updateOperation")
	private Operation updateOperation;

	@Autowired
	@Qualifier("getOperation")
	private Operation getOperation;

	private static Operation operation;

	@PostMapping("/insert")
	public Response doInsert(@RequestBody Permissions data) throws DataBaseException
	{
		List resultList = insertOperation.doOperation(data);

		return chooseResponseAfterInsert(resultList);
	}

	private Response chooseResponseAfterInsert(List resultList)
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
			Permissions permissions = new Permissions();
			permissions.setId(id);
			List resultList = removeOperation.doOperation(permissions);

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(resultList);
			response.setState(State.SUCCESS);
			return response;
		} catch (DataBaseException e)
		{
			return createFailureResponse(e);
		}
	}

	@GetMapping("/get")
	public Response doGet(@RequestParam Long id)
	{
		try
		{
			Permissions permissions = new Permissions();
			permissions.setId(id);
			List resultList = getOperation.doOperation(permissions);

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(resultList);
			response.setState(State.SUCCESS);

			return response;
		} catch (DataBaseException e)
		{
			return createFailureResponse(e);
		}
	}

	@PostMapping("/update")
	public Response doUpdate(@RequestParam Long id, @RequestBody Permissions data)
	{
		try
		{
			data.setId(id);
			List resultList = updateOperation.doOperation(data);

			PermissionsResponse response = new PermissionsResponse();
			response.setPermissions(resultList);
			response.setState(State.SUCCESS);

			return response;
		} catch (DataBaseException e)
		{
			return createFailureResponse(e);
		}
	}

	private Response createFailureResponse(Exception e)
	{
		Class exceptionClass = e.getClass();

		if (exceptionClass.equals(DataBaseException.class))
			return new Response(State.DATABASE_FAIL);
		return new Response(State.FAIL);
	}
}
