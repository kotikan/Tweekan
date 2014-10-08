package tweekan.kotikan.com.tweekan.twitter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by roberthewitt on 07/10/2014.
 */
public class BasicTwitterClient implements Twitter.Request {

    private final twitter4j.Twitter instance;

    public BasicTwitterClient() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb
                .setDebugEnabled(true)
                .setOAuthConsumerKey(Twitter.consumerKey)
                .setOAuthConsumerSecret(Twitter.consumerSecret)
                .setOAuthAccessToken(Twitter.accessToken)
                .setOAuthAccessTokenSecret(Twitter.accessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        instance = twitterFactory.getInstance();
    }

    @Override
    public void tweetsForQuery(final String query, final Twitter.Callback callback) {
        new AsyncTask<String, Void, QueryResult>() {

            @Override
            protected QueryResult doInBackground(String... params) {
                QueryResult result = null;
                try {
                    result = instance.search().search(new Query(params[0]));
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(QueryResult queryResult) {
                final Twitter.ServerResponse response = queryResult != null ? Twitter.ServerResponse.SUCCESS : Twitter.ServerResponse.FAILURE;
                final ArrayList<String> results = new ArrayList<String>();
                if (queryResult != null) {
                    for (twitter4j.Status tweet : queryResult.getTweets()) {
                        String name = tweet.getUser().getName();
                        String tweetText = tweet.getText();
                        results.add(String.format(
                                  "user : %s" +
                                "\nmsg  : %s", name, tweetText));
                    }
                }

                callback.onRequestComplete(new Twitter.Result() {
                    @Override
                    public Twitter.ServerResponse response() {
                        return response;
                    }

                    @Override
                    public List<String> tweets() {
                        return results;
                    }
                });
            }
        }.execute(query);
    }

}
