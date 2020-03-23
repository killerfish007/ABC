package interviewbit;

import java.util.Arrays;

public class Rest {

	public static void main(String[] args) {
		String[] split = "hi how are you? i am fine. wow! derfd".split("[.!? ]");
		System.out.println(Arrays.toString(split));
	}

	/*
	 * public static void main(String[] args) throws Exception { String url =
	 * "https://jsonmock.hackerrank.com/api/movies/search/?Title=maze"; URL obj =
	 * new URL(url); HttpURLConnection con = (HttpURLConnection)
	 * obj.openConnection(); con.setRequestMethod("GET"); BufferedReader in = new
	 * BufferedReader(new InputStreamReader(con.getInputStream())); String
	 * inputLine; StringBuffer response = new StringBuffer();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
	 * in.close(); Gson gson = new GsonBuilder().setPrettyPrinting().create();
	 * JsonElement jelem = gson.fromJson(response.toString(), JsonElement.class);
	 * JsonObject jobj = jelem.getAsJsonObject();
	 * 
	 * System.out.println(jobj.get("total").getAsInt());
	 * 
	 * }
	 */

}
