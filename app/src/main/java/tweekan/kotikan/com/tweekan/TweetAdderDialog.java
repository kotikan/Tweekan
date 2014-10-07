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