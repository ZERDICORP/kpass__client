package constants;

import java.util.HashMap;

public class ResultMessage
{
	private static HashMap<ResultCode, String> map = new HashMap<>();

	static
	{
		map.put(ResultCode.DOES_NOT_EXIST, "does not exist..");
		map.put(ResultCode.ALREADY_EXIST, "already exist..");
		map.put(ResultCode.ACCESS_DENIED, "access denied..");
		map.put(ResultCode.BAD_INDEX, "bad index..");
	}

	public static String byCode(ResultCode code) {return "[-] " + map.get(code) + "\n";}

	public static String notLoggedIn()
	{
		return "[!] You are not logged in, run below command:\n" +
			"\tkpass auth <email> <password>\n\n" +
			"[!] Or if you don't have an account use the following command:\n" + 
			"\tkpass auth --new <email> <password>\n";
	}

	public static String needToLogout()
	{
		return "[!] For this action you need to log out:\n" +
			"\tkpass logout\n";
	}
}