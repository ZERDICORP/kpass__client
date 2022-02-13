package actions;

import zer.http.*;
import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.Request_GetServices;
import config.Config;

@CMDPattern("temp")
public class CMDAction_Temp extends CMDAction
{
	@Override
	public void exec(String[] args)
	{
		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/temp");
			
		HTTPClient.send(req);
	}
}