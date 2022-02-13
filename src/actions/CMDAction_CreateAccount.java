package actions;

import zer.hash.PaED62;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONObject;
import constants.ResultCode;
import constants.ResultMessage;
import requests.Request_CreateAccount;
import config.Config;

@CMDPattern("auth --new [a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z0-9]{1,} [^ ]{6,255}")
public class CMDAction_CreateAccount extends CMDAction
{	
	@Override
	public void exec(String[] args)
	{
		if (Config.session)
		{
			System.out.println(ResultMessage.needToLogout());
			return;
		}
		
		JSONObject resBody = Request_CreateAccount.make(args[2], PaED62.hash(args[3]));
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		System.out.println("[+] check your email to confirm account\n");
	}
}