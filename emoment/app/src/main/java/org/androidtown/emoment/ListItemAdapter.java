package org.androidtown.emoment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by sangsoo on 2015-05-25.
 */
public class ListItemAdapter extends BaseAdapter{
    EmoticonView emoticonView;
    private Context mContext;
    ArrayList<Bitmap> image = new ArrayList<Bitmap>();

    public ListItemAdapter(Context context) {
        mContext=context;
    }

    @Override
    public int getCount() {
        return image.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            emoticonView = new EmoticonView(mContext);
        }
        else
        {
            emoticonView = (EmoticonView)convertView;
        }
        emoticonView.setImage(image.get(position));
        notifyDataSetChanged();

        return emoticonView;
    }
}
