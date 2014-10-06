package tweekan.kotikan.com.tweekan;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by roberthewitt on 06/10/2014.
 */
public class TweetApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
