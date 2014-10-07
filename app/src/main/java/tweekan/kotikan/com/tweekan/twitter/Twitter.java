package tweekan.kotikan.com.tweekan.twitter;

import java.util.List;

/**
 * Created by roberthewitt on 07/10/2014.
 */
public class Twitter {

    static final String consumerKey    = "generateAndFillIn";
    static final String consumerSecret = "generateAndFillIn";

    public static Request request() {
        return new BasicTwitterClient();
    }

    public static interface Request {
        void tweetsForQuery(String query, Callback callback);
    }

    public static interface Callback {
        void onRequestComplete(Result result);
    }

    public static interface Result {
        ServerResponse response();
        List<String> tweets();
        String originalQuery();
    }

    public static enum ServerResponse {
        SUCCESS, FAILURE
    }

}
