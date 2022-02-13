package actions;

import zer.cipher.CaesarEncryptionAlgorithm;
import zer.hash.PaED62;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONObject;
import org.json.JSONArray;
import constants.ResultCode;
import constants.ResultMessage;
import requests.Request_AddService;
import config.Config;
import tools.Tools;
import java.util.Random;

@CMDPattern("a [a-zA-Z0-9._-]{1,255} [^ ]{1,255} ([^ ]{6,255}|gen)")
public class CMDAction_AddService extends CMDAction
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

		JSONObject resBody = Request_AddService.make(Config.id, PaED62.hash(Config.password), args[1],
			caesar.encrypt(args[2]), caesar.encrypt(args[3].equals("gen") ? Tools.genString(16) : args[3]));
		
		if (!resBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(resBody.getString("result"))));
			return;
		}

		System.out.println("[+] Service @" + args[1] + " added successfully\n");
	}
}