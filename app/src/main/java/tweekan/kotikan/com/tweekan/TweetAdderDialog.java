package tweekan.kotikan.com.tweekan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by roberthewitt on 06/10/2014.
 */
public class TweetAdderDialog  extends DialogFragment {

    public static final String NAME = UUID.randomUUID().toString();

    public static interface Callback {
        void tweetChosenPhrase(String tweet);
    }

    private Callback listener;
    private EditText inputField;

    public void setListener(Callback listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Activity activity = getActivity();

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogLayout = LayoutInflater.from(activity).inflate(R.layout.dialog_add_tweet, null);
        inputField = (EditText) dialogLayout.findViewById(R.id.dialog_add_tweet_input);

        builder
                .setTitle(R.string.dialog_add_tweet_title)
                .setView(dialogLayout)
                .setPositiveButton(R.string.dialog_add_tweet_positive, returnToActivityListener());
        return builder.create();
    }

    private DialogInterface.OnClickListener returnToActivityListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (listener != null) {
                    Editable text = inputField.getText();
                    if (text != null) {
                        listener.tweetChosenPhrase(text.toString().trim());
                    }
                }
            }
        };
    }
}