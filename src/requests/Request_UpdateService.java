package requests;

import zer.http.HTTPClient;
import zer.http.HTTPResponse;
import zer.http.HTTPRequest;
import org.json.JSONObject;

public class Request_UpdateService
{
	public static JSONObject make(int user_id, String password_hash, int id, String name, String login, String password)
	{
		JSONObject reqBody = new JSONObject()
			.put("user_id", user_id)
			.put("password_hash", password_hash)
			.put("id", id)
			.put("name", name)
			.put("login", login)
			.put("password", password);

		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/update_service")
			.setBody(reqBody.toString());
			
		HTTPResponse res = HTTPClient.send(req);

		return new JSONObject(res.get("Body"));
	}
}