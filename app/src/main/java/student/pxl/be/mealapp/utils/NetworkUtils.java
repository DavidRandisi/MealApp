package student.pxl.be.mealapp.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Utility class that will be used to communicate over the network with the MealAPI
 */
public class NetworkUtils {
    final static String MEALAPI_BY_NAME_URL = "https://www.themealdb.com/api/json/v1/1/search.php";
    final static String MEALAPI_BY_ID_URL = "https://www.themealdb.com/api/json/v1/1/lookup.php";
    final static String MEALAPI_LATEST_MEALS_URL = "https://www.themealdb.com/api/json/v1/1/latest.php";

    final static String PARAM_NAME = "s";
    final static String PARAM_ID = "i";

    public static URL buildSearchUri(String searchQuery, QueryType type){
        String baseURL ;
        String parameter;
        if(type == QueryType.ID){
            baseURL = MEALAPI_BY_ID_URL;
            parameter = PARAM_ID;
        } else {
            baseURL = MEALAPI_BY_NAME_URL;
            parameter = PARAM_NAME;
        }
        Uri builtUri = Uri.parse(baseURL).buildUpon()
                .appendQueryParameter(parameter, searchQuery)
                .build();
        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch(MalformedURLException exception){
            exception.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
