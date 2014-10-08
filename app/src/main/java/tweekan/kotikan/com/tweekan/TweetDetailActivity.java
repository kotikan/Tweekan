package tweekan.kotikan.com.tweekan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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
