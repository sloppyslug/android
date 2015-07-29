package org.androidtown.mytap.org.androidtown.friend;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidtown.mytap.MainActivity;
import org.androidtown.mytap.org.androidtown.calendar.CardDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by constant on 15. 7. 25..
 */
public class GridViewAdapter extends BaseAdapter {

    Context mContext;
    SimpleDateFormat dateFormat;
    Date date;
    String today;
    String[] parsedToday={null,null};

    CardDialog dialog;

    String name = "오상수";
    String comment = "샘플 친구목록 창";

    public GridViewAdapter(Context context) {
        dateFormat = new SimpleDateFormat("MM.dd");
        date = new Date();
        today = dateFormat.format(date);
        parsedToday=parse(today);
        Log.d(MainActivity.TAG, "(화면 3) today is : " +today);
        //Log.d(MainActivity.TAG, "parsedToday is : " +parsedToday[0]+ parsedToday[1]);

        mContext =context;
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friend_item friend_item;
        if(convertView==null) {
            friend_item= new Friend_item(mContext);
        } else {
            friend_item=(Friend_item)convertView;
        }
        friend_item.setInfo(name, 1, comment);

        return friend_item;
    }

    public void CardDialogShow(int position)
    {
        Log.d(MainActivity.TAG, "(화면 3) card position is " + position);

        String title = parsedToday[0] + "." + parsedToday[1];
        dialog = new CardDialog(mContext, name, comment, title);
        dialog.show();
    }
    public String[] parse(String today)
    {
        String[] temp={null,null};
        if(today.contains("."))
        {
            temp=today.split(".");

        }else{
            temp=null;
        }
        return temp;
    }

}
