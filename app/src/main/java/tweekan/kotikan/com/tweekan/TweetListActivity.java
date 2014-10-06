package tweekan.kotikan.com.tweekan;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import tweekan.kotikan.com.tweekan.models.Tweet;


public class TweetListActivity extends Activity implements TweetAdderDialog.Callback {

    private TweetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        ListView listView = (ListView) findViewById(android.R.id.list);
        adapter = new TweetAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                launchDetailActivityForTweet(((Tweet)adapter.getItem(pos)).name);
            }
        });
    }

    private void launchDetailActivityForTweet(String tweet) {
        Intent intent = new Intent(this, TweetDetailActivity.class);
        intent.putExtra(TweetDetailActivity.EXTRA_TWEET_NAME, tweet);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tweet_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_action_add_tweet_interest) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TweetAdderDialog fragment = new TweetAdderDialog();
            fragment.setListener(this);
            fragmentTransaction.add(fragment, TweetAdderDialog.NAME);
            fragmentTransaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tweetChosenPhrase(String tweet) {
        adapter.addNewTweet(tweet);
    }
}
