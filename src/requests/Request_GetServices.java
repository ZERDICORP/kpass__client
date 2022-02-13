package requests;

import zer.http.HTTPClient;
import zer.http.HTTPResponse;
import zer.http.HTTPRequest;
import zer.http.HTTPConfig;
import org.json.JSONObject;
import org.json.JSONException;

public class Request_GetServices
{
	public static boolean isJSONValid(String test) {
    try {
        new JSONObject(test);
    } catch (JSONException ex) {
        // edited, to include @Arthur's comment
        // e.g. in case JSONArray is valid as well...
    	return false;
    }
    return true;
}

	public static JSONObject make(int user_id, String password_hash, String pattern)
	{
		JSONObject reqBody = new JSONObject()
			.put("user_id", user_id)
			.put("password_hash", password_hash)
			.put("pattern", pattern);

		HTTPRequest req = new HTTPRequest();
		req
			.set("Type", "POST")
			.set("Path", "/get_services")
			.setBody(reqBody.toString());
		
		HTTPResponse res = HTTPClient.send(req);

		if (!isJSONValid(res.get("Body")))
		{
			System.out.println("Not valid!\n");
			System.out.println(res.get("Body"));
		}

		return new JSONObject(res.get("Body"));
	}
}