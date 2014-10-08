package tweekan.kotikan.com.tweekan;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roberthewitt on 08/10/2014.
 */
public abstract class GenericAdapter<T> extends BaseAdapter {

    final List<T> data = new ArrayList<T>();

    public void add(T t) {
        data.add(t);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public T getItemGeneric(int position) {
        return data.get(position);
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
