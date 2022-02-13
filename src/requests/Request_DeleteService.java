package requests;

import zer.http.HTTPClient;
import zer.http.HTTPResponse;
import zer.http.HTTPRequest;
import org.json.JSONObject;

public class Request_DeleteService
{
	public static JSONObject make(int user_id, String password_hash, int id)
	{
		JSONObject reqBody = new JSONObject()
			.put("user_id", user_id)
			.put("password_hash", password_hash)
			.put("id", id);

		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/delete_service")
			.setBody(reqBody.toString());
			
		HTTPResponse res = HTTPClient.send(req);

		return new JSONObject(res.get("Body"));
	}
}