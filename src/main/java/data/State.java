package data;

public enum State
{
	SUCCESS("Удачно", "001"),
	FAIL("Ошибка", "002"),
	FIND_FAIL("Ошибка при удалении", "003"),
	REFLECTIVE_FAIL("Ошибка при удалении", "004");

	private String description;
	private String code;

	State(String description, String code)
	{
		this.description = description;
		this.code = code;
	}
}
