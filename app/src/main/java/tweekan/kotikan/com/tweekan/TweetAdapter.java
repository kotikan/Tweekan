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
