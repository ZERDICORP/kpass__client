package tools;

import config.Config;
import java.util.Random;

public class Tools
{
	public static String genString(int length)
	{
		String result = new String();
		for (int i = 0; i < length; ++i)
			result += (char)(new Random().nextInt(126 - 33) + 33);
		return result;
	}

	public static void readPassword()
	{
		System.out.print("[>] Enter password: ");
		Config.password = new String(System.console().readPassword());
		System.out.println();
	}
}