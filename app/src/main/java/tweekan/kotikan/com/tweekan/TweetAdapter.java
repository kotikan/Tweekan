package tweekan.kotikan.com.tweekan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import tweekan.kotikan.com.tweekan.models.Tweet;

/**
 * Created by roberthewitt on 06/10/2014.
 */
public class TweetAdapter extends BaseAdapter {

    private final List<Tweet> tweets = new ArrayList<Tweet>();
    private final LayoutInflater inflater;

    private Tweet saveTweetToDisc(String toSave) {
        Tweet tweet = new Tweet();
        tweet.name = toSave;
        tweet.save();
        return tweet;
    }


    private void deleteTweetFromDisc(Tweet tweet) {
        tweet.delete();
    }

    public TweetAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        tweets.addAll(Tweet.getAll());
    }

    public void addNewTweet(String tweet) {
        Tweet tweetToDisc = saveTweetToDisc(tweet);
        tweets.add(tweetToDisc);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LinearLayout layout = (convertView != null)
                ? (LinearLayout) convertView
                : (LinearLayout) inflater.inflate(R.layout.list_cell_tweet_interest, null);


        TextView textView = (TextView) layout.findViewById(R.id.list_cell_tweet_title);
        View deleteButton = layout.findViewById(R.id.list_cell_tweet_delete);
        deleteButton.setOnClickListener(deleteTweetListener(position));

        textView.setText(tweets.get(position).name);

        return layout;
    }

    private View.OnClickListener deleteTweetListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTweetFromDisc(tweets.remove(position));

                notifyDataSetChanged();
            }
        };
    }
}
