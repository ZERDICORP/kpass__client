package actions;

import zer.cipher.CaesarEncryptionAlgorithm;
import zer.hash.PaED62;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.Request_GetServices;
import constants.ResultCode;
import constants.ResultMessage;
import config.Config;
import tools.Tools;

@CMDPattern("f=([a-zA-Z0-9_.-]{1,255}|\\*)")
public class CMDAction_GetServices extends CMDAction
{
	@Override
	public void exec(String[] args)
	{
		if (!Config.session)
		{
			System.out.println(ResultMessage.notLoggedIn());
			return;
		}

		Tools.readPassword();

		String pattern = args[0].split("=", 2)[1].toLowerCase();
		if (pattern.equals("*"))
			pattern = "";

		JSONObject resBody = Request_GetServices.make(Config.id, PaED62.hash(Config.password), pattern);
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		CaesarEncryptionAlgorithm caesar = new CaesarEncryptionAlgorithm(Config.password);
		caesar.setRange(32, 126);

		JSONArray services = resBody.getJSONArray("services");
		for (int i = 0; i < services.length(); ++i)
		{
			JSONObject service = services.getJSONObject(i);
			System.out.println("[" + service.getInt("id") + "] " + service.getString("name") +
				"\n\t" + caesar.decrypt(service.getString("login")) + "\n\t" +
				caesar.decrypt(service.getString("password")) + "\n");
		}

		if (services.length() == 0)
			System.out.println("[o] Empty list..\n");
	}
}