package com.example.han.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HAN on 2015. 3. 7..
 */
public class ItemAdapter extends BaseAdapter {

    ItemView itemView;
    private Context mContext;

    ArrayList<String> song = new ArrayList<String>();
    ArrayList<String> singer = new ArrayList<String>();

    public ItemAdapter(Context Context){
        mContext = Context;
    }

    @Override
    public int getCount() {
        return song.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        itemView = new ItemView(mContext);

        itemView.setItem(song.get(position), singer.get(position));

        //아이템 삭제버튼
        itemView.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext.getApplicationContext(),"삭제된 아이템 : "+song.get(position)+" "+ singer.get(position),Toast.LENGTH_SHORT).show();
                song.remove(position);
                singer.remove(position);
                //getView를 다시 그려줌
                notifyDataSetChanged();
            }
        });

        return itemView;


    }


}
