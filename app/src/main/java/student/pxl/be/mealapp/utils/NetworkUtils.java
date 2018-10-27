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
    final static String BASE_URL = "http://www.recipepuppy.com/api/";

    final static String PARAM_NAME = "q";
    final static String PARAM_INGREDIENTS = "i";
    final static String PARAM_PAGE = "p";

    public static String buildUriString(String name, String page){
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_NAME, name)
                .appendQueryParameter(PARAM_PAGE, page)
                .build();
        return builtUri.toString();
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
