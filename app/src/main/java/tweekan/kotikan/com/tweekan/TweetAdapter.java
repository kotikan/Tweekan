package tweekan.kotikan.com.tweekan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roberthewitt on 06/10/2014.
 */
public class TweetAdapter extends BaseAdapter {

    final List<String> tweets = new ArrayList<String>();
    private final LayoutInflater inflater;

    public TweetAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addNewTweet(String tweet) {
        tweets.add(tweet);
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
        textView.setText(tweets.get(position));

        return layout;
    }
}
