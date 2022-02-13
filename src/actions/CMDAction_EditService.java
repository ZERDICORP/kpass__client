package actions;

import zer.cipher.CaesarEncryptionAlgorithm;
import zer.hash.PaED62;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONObject;
import org.json.JSONArray;
import constants.ResultCode;
import constants.ResultMessage;
import requests.Request_ChangePassword;
import requests.Request_UpdateService;
import requests.Request_GetServices;
import config.Config;
import tools.Tools;

@CMDPattern("e [0-9]{1,} [a-zA-Z0-9._-]{1,255} [^ ]{1,255} ([^ ]{6,255}|gen|\\_)")
public class CMDAction_EditService extends CMDAction
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

		CaesarEncryptionAlgorithm caesar = new CaesarEncryptionAlgorithm(Config.password);
		caesar.setRange(32, 126);

		JSONObject resBody = Request_UpdateService.make(Config.id, PaED62.hash(Config.password), Integer.parseInt(args[1]), args[2],
			(args[3].equals("_") ? args[3] : caesar.encrypt(args[3])),
			(args[4].equals("_") ? args[4] : caesar.encrypt(args[4].equals("gen") ? Tools.genString(16) : args[4])));
		
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		System.out.println("[+] Service #" + args[1] + " edited successfully\n");
	}
}