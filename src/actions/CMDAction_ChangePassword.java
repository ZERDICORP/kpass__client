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

@CMDPattern("pwd [^ ]{6,255}")
public class CMDAction_ChangePassword extends CMDAction
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

		CaesarEncryptionAlgorithm newCaesar = new CaesarEncryptionAlgorithm(args[1]);
		newCaesar.setRange(32, 126);

		JSONObject getServicesResBody = Request_GetServices.make(Config.id, PaED62.hash(Config.password), "");
		if (!getServicesResBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(getServicesResBody.getString("result"))));
			return;
		}

		JSONArray services = getServicesResBody.getJSONArray("services");
		for (int i = 0; i < services.length(); ++i)
		{
			JSONObject service = services.getJSONObject(i);

			JSONObject updateServiceResBody = Request_UpdateService.make(Config.id, PaED62.hash(Config.password), service.getInt("id"), service.getString("name"),
				newCaesar.encrypt(caesar.decrypt(service.getString("login"))), newCaesar.encrypt(caesar.decrypt(service.getString("password"))));
			
			if (!updateServiceResBody.getString("result").equals(ResultCode.OK.toString()))
			{
				System.out.println(ResultMessage.byCode(ResultCode.valueOf(updateServiceResBody.getString("result"))));
				return;
			}

			System.out.print("[%] Encrypting your data (" + (i + 1) + "/" + services.length() + ")\r");
		}

		JSONObject changePasswordResBody = Request_ChangePassword.make(Config.id, PaED62.hash(Config.password), PaED62.hash(args[1]));
		if (!changePasswordResBody.getString("result").equals(ResultCode.OK.toString()))
		{
			System.out.println(ResultMessage.byCode(ResultCode.valueOf(changePasswordResBody.getString("result"))));
			return;
		}

		System.out.println("\n[+] Password changed successfully\n");
	}
}