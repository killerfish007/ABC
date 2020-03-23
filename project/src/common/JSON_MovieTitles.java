package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSON_MovieTitles {
	public static void main(String[] args) {
		String[] ar = getMovieTitles("spiderman");
		System.out.println(ar);
	}
	static String[] getMovieTitles(String substr) {
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;
        String response;
        List<String> titles = new ArrayList<>();
        while(startPage<=totalPages){
            try{
                URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title="+substr+"&page="+startPage);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while((response=in.readLine())!=null){
                    JsonObject obj = new Gson().fromJson(response, JsonObject.class);
                    totalPages = obj.get("total_pages").getAsInt();
                    JsonArray data = obj.getAsJsonArray("data");
                    for(int i=0;i<data.size();i++){
                        String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                        titles.add(title);
                    }
                }
                in.close();
                startPage++;

            }catch(Exception e){

            }
        }
        Collections.sort(titles);
        System.out.println(titles);
        return titles.toArray(new String[0]);
    }
}
