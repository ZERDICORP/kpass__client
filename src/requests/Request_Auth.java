package requests;

import zer.http.HTTPClient;
import zer.http.HTTPResponse;
import zer.http.HTTPRequest;
import org.json.JSONObject;

public class Request_Auth
{
	public static JSONObject make(String email, String password_hash)
	{
		JSONObject reqBody = new JSONObject()
			.put("email", email)
			.put("password_hash", password_hash);

		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/auth")
			.setBody(reqBody.toString());

		HTTPResponse res = HTTPClient.send(req);

		return new JSONObject(res.get("Body"));
	}
}