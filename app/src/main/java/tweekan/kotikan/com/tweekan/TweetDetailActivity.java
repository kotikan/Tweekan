package tweekan.kotikan.com.tweekan;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class TweetDetailActivity extends Activity {

    public static final String EXTRA_TWEET_NAME = "EXTRA_TWEET_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString(EXTRA_TWEET_NAME);
        setTitle("Showing tweets for " + title);
    }
}
