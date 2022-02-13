package requests;

import zer.http.HTTPClient;
import zer.http.HTTPResponse;
import zer.http.HTTPRequest;
import org.json.JSONObject;

public class Request_ChangePassword
{
	public static JSONObject make(int user_id, String password_hash, String new_password_hash)
	{
		JSONObject reqBody = new JSONObject()
			.put("user_id", user_id)
			.put("password_hash", password_hash)
			.put("new_password_hash", new_password_hash);

		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/change_password")
			.setBody(reqBody.toString());
			
		HTTPResponse res = HTTPClient.send(req);

		return new JSONObject(res.get("Body"));
	}
}