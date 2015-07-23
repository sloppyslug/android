/*
* Copyright 2011 Lauri Nevala.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.androidtown.mytap.org.androidtown.calendar;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.mytap.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    static final int FIRST_DAY_OF_WEEK = 0; // Sunday = 0, Monday = 1

    private Context mContext;

    private Calendar month;
    private CardDialog dialog;
    private TextView dayView;
    private int firstDay;
    private int lastDay;

    private ImageView dateImage;
    private ImageView commentImage;

    private String TAG = "CALENDAR";
    private String TAG2 = "CALENDAR3";
    //1~30(31)
    public String[] days;
    //해당 월의 모든 날짜의 정보.
    private String[][] dayInfo;




    public CalendarAdapter(Context c, Calendar monthCalendar) {
        month = monthCalendar;
        mContext = c;
        month.set(Calendar.DAY_OF_MONTH, 1);
        refreshDays();
    }

    public int getCount() {
        return days.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_item, null);
        }
        dayView = (TextView) v.findViewById(R.id.date);
        dateImage = (ImageView)v.findViewById(R.id.date_icon);
        commentImage = (ImageView)v.findViewById(R.id.commentImage);

        //모두 클릭 안되게 한후
        dayView.setClickable(false);
        dayView.setFocusable(false);

        //글자가 있는거는 클릭가능
        if (days[position].equals("")) {
            dayView.setClickable(true);
            dayView.setFocusable(true);
        }

        //글자 쓰기
        if(position%7==6)
        {
            dayView.setTextColor(Color.parseColor("#8fc31f"));
        }else if(position%7==0)
        {
            dayView.setTextColor(Color.parseColor("#8fc31f"));
        }else{
            dayView.setTextColor(Color.parseColor("#dae000"));
        }
        dayView.setText(days[position]);

        //1부터 끝까지

        if(!days[position].equals(""))
        {
            LoadCalendar(month.get(Calendar.MONTH), days[position]);
        }
        return v;
    }

    public void refreshDays() {
        lastDay = month.getActualMaximum(Calendar.DAY_OF_MONTH);
        firstDay = (int) month.get(Calendar.DAY_OF_WEEK);
        dayInfo = new String[lastDay][4];


        // figure size of the array
        if (firstDay == 1) {
            days = new String[lastDay + (FIRST_DAY_OF_WEEK * 6)];
        } else {
            days = new String[lastDay + firstDay - (FIRST_DAY_OF_WEEK + 1)];
        }
        int j = FIRST_DAY_OF_WEEK;

        // populate empty days before first real day
        if (firstDay > 1) {
            for (j = 0; j < firstDay - FIRST_DAY_OF_WEEK; j++) {
                days[j] = "";
            }
        } else {
            for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
                days[j] = "";
            }
            j = FIRST_DAY_OF_WEEK * 6 + 1; // sunday => 1, monday => 7
        }

        // populate days
        int dayNumber = 1;
        for (int i = j - 1; i < days.length; i++) {
            days[i] = "" + dayNumber;
            dayNumber++;
        }
    }

    class CalendarNetwork extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String[] params) {
            String urlStr = params[0];
            StringBuffer buffer = new StringBuffer();
            String[] arr = new String[4];
            try {
                URL url = new URL(urlStr);
                InputStream is = url.openConnection().getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.d(TAG, " 전체 메세지 " + line);
                    buffer.append(line);
                }
                if(buffer.toString()!=null)
                {
                    JSONObject root = new JSONObject(buffer.toString());
                    String emotion = root.getString("emotion");
                    Log.d(TAG, "emotion : " + emotion);
                    String comment = root.getString("comment");
                    Log.d(TAG, "comment : " + comment);
                    String month = root.getString("month");
                    Log.d(TAG, "month : " + month);
                    String day = root.getString("day");
                    Log.d(TAG, "day : " + day);
                    arr[0] = emotion;
                    arr[1] = comment;
                    arr[2] = month;
                    arr[3] = day;
                }
            } catch (Exception e) {
                arr[0]=null;
                arr[1]=null;
                arr[2]=null;
                arr[3]=null;
            }
            return arr;
        }
    }
    public void LoadCalendar(int monthInt, String today) {
        int todayInt = Integer.parseInt(today);
        int arrayToday = --todayInt;
        Log.d(TAG2, "todayInt is " + todayInt);
        String[] todayInfo;
        String month = String.valueOf(++monthInt);
        String urlStr = "http://52.69.116.105:8000/load_specific_moment/2015/" + month + "/" + today;
        Log.d(TAG2, "URL IS " + urlStr);
        try {
            todayInfo= new CalendarNetwork().execute(urlStr).get();
        } catch (Exception e) {
            todayInfo = null;
        }
        for(int i=0; i<4; i++)
        {
            dayInfo[arrayToday][i]=todayInfo[i];
            Log.d(TAG2, "dayInfo is " + dayInfo[arrayToday][i] + " todayInfo is " + todayInfo[i]);

        }
        if (dayInfo[todayInt][1] != null)
        {
            int tempInt = Integer.parseInt(dayInfo[arrayToday][0]);
            switch (tempInt)
            {
                case 0:
                    dateImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon1));
                    break;
                case 1:
                    dateImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon2));
                    break;
                case 2:
                    dateImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon3));
                    break;
                case 3:
                    dateImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon4));
                    break;
            }
            commentImage.setVisibility(View.VISIBLE);
        }
        for(int j=0; j<4; j++)
        {
            Log.d(TAG2, "dayInfo is" + dayInfo[arrayToday][j]);
        }

    }
    public void CardDialogShow(int position)
    {
        int firstday=firstDay-2;
        int today = position-firstday;
        int arrayToday=today-1;
        Log.d(TAG2, "card today is " + today);
        for(int j=0; j<4; j++)
        {
            Log.d(TAG2, "card dayInfo is" + dayInfo[arrayToday][j]);
        }
        dialog = new CardDialog(mContext, dayInfo[arrayToday][0], dayInfo[arrayToday][1]);
        Log.d(TAG2, "card emotion is " + dayInfo[arrayToday][0] + "card comment is " + dayInfo[arrayToday][1]);

        dialog.show();


    }

}

