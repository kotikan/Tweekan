package tweekan.kotikan.com.tweekan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by roberthewitt on 08/10/2014.
 */
public class StringAdapter extends GenericAdapter<String> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        final LinearLayout layout = (convertView != null)
                ? (LinearLayout) convertView
                : (LinearLayout) LayoutInflater.from(context).inflate(R.layout.list_cell_tweet_detail, null);


        TextView textView = (TextView) layout.findViewById(R.id.list_cell_tweet_message);
        textView.setText(getItemGeneric(position));

        return layout;
    }

}
