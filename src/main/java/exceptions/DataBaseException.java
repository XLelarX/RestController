package exceptions;

public class DataBaseException extends Exception
{
	private String message;

	public DataBaseException(String message)
	{
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
