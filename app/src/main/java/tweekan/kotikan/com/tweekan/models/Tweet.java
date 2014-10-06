package tweekan.kotikan.com.tweekan.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by roberthewitt on 06/10/2014.
 */
@Table(name = "Tweet")
public class Tweet extends Model {
    @Column(name = "Name")
    public String name;

    public static List<Tweet> getAll() {
        return new Select().from(Tweet.class).orderBy("Name").execute();
    }
}