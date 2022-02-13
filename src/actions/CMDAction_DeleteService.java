package actions;

import zer.cipher.CaesarEncryptionAlgorithm;
import zer.hash.PaED62;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONObject;
import org.json.JSONArray;
import constants.ResultCode;
import constants.ResultMessage;
import requests.Request_DeleteService;
import config.Config;
import tools.Tools;
import java.util.Random;

@CMDPattern("d [0-9]{1,}")
public class CMDAction_DeleteService extends CMDAction
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

		JSONObject resBody = Request_DeleteService.make(Config.id, PaED62.hash(Config.password), Integer.parseInt(args[1]));
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		System.out.println("[+] Service #" + args[1] + " successfully deleted\n");
	}
}