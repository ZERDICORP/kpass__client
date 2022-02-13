package config;

import zer.file.FPlain;
import zer.file.FTool;

public class Config
{
	public static final String sessionFilePath = ".session";
	public static final Boolean session = FTool.exists(sessionFilePath);
	public static String password;
	public static int id;

	static
	{
		if (session)
			id = Integer.parseInt(FPlain.read(sessionFilePath));
	}

	public static void endSession() { FTool.delete(sessionFilePath); }
	public static void startSession() { FPlain.write(sessionFilePath, String.valueOf(id)); }
}