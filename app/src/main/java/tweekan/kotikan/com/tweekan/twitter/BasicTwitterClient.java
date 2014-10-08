/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Kotikan Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
