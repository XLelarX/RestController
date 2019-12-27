package data;

public enum State
{
	SUCCESS("Удачно", "001"),
	FAIL("Ошибка", "004");

	private String description;
	private String code;

	State(String description, String code)
	{
		this.description = description;
		this.code = code;
	}
}
