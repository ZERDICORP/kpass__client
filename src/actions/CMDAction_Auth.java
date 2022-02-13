package actions;

import zer.hash.PaED62;
import zer.file.FPlain;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONObject;
import requests.Request_Auth;
import constants.ResultCode;
import constants.ResultMessage;
import config.Config;

@CMDPattern("auth [a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z0-9]{1,} [^ ]{6,255}")
public class CMDAction_Auth extends CMDAction
{
	@Override
	public void exec(String[] args)
	{
		if (Config.session)
		{
			System.out.println(ResultMessage.needToLogout());
			return;
		}
		
		JSONObject resBody = Request_Auth.make(args[1], PaED62.hash(args[2]));
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		Config.id = resBody.getInt("id");
		Config.startSession();

		System.out.println("[+] successful login (" + args[1] + ")\n");
	}
}