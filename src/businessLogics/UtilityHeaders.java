package businessLogics;

import java.util.HashMap;
import java.util.Map;

public class UtilityHeaders {

	public static Map getCommonHeaders() {
		Map<String, String> requestHeader=new HashMap<String, String>();
		requestHeader.put("X-NLocale", "EN");
		requestHeader.put("Content-Type", "application/json");
		requestHeader.put("X-NDeviceId", "0");
		requestHeader.put("X-NDevice", "00");
		requestHeader.put("X-NVersion", "1220");
		requestHeader.put("X-NPlatformId", "ANDROID");
		return requestHeader;
	}
}
