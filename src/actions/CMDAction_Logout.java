package actions;

import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import constants.ResultMessage;
import config.Config;

@CMDPattern("logout")
public class CMDAction_Logout extends CMDAction
{
	@Override
	public void exec(String[] args)
	{
		if (!Config.session)
		{
			System.out.println(ResultMessage.notLoggedIn());
			return;
		}

		Config.endSession();

		System.out.println("[+] successful logout\n");
	}
}