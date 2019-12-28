package exceptions;

public class FindException extends Exception
{
	private String message;

	public FindException(String message)
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
