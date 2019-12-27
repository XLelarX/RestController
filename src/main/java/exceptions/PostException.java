package exceptions;

public class PostException extends Exception
{
	private String message;

	public PostException(String message)
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
