package tweekan.kotikan.com.tweekan.twitter;

import java.util.List;

/**
 * Created by roberthewitt on 07/10/2014.
 */
public class Twitter {

    // generate using https://apps.twitter.com/
    static final String consumerKey       = "fillIn";
    static final String consumerSecret    = "fillIn";
    static final String accessToken       = "fillIn";
    static final String accessTokenSecret = "fillIn";

    private static BasicTwitterClient basicTwitterClient = new BasicTwitterClient();
    public static Request request() {
        return basicTwitterClient;
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
    }

    public static enum ServerResponse {
        SUCCESS, FAILURE
    }

}
