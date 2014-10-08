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

package tweekan.kotikan.com.tweekan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import tweekan.kotikan.com.tweekan.twitter.Twitter;

public class TweetDetailActivity extends Activity {

    public static final String EXTRA_TWEET_NAME = "EXTRA_TWEET_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tweet_detail);

        final ListView listView = (ListView) findViewById(android.R.id.list);

        Bundle extras = getIntent().getExtras();
        String tweetQuery = extras.getString(EXTRA_TWEET_NAME);
        setTitle("Showing tweets for " + tweetQuery);

        Twitter.request().tweetsForQuery(tweetQuery, new Twitter.Callback() {
            @Override
            public void onRequestComplete(Twitter.Result result) {
                if (result.response() == Twitter.ServerResponse.SUCCESS) {
                    StringAdapter adapter = new StringAdapter();
                    listView.setAdapter(adapter);

                    for (String tweet : result.tweets()) {
                        adapter.add(tweet);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
