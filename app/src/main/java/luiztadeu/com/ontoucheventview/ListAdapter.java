package luiztadeu.com.ontoucheventview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shark on 24/12/2016.
 */

public class ListAdapter extends BaseAdapter{

    private Context mActivity;
    private List<String> list;

    public ListAdapter(Context mActivity, List<String> list) {
        this.mActivity = mActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if ( convertView == null){
            convertView = LayoutInflater.from(mActivity).inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(list.get(position));

        return convertView;
    }
}
