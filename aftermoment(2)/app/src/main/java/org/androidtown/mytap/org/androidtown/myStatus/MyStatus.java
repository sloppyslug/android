package org.androidtown.mytap.org.androidtown.myStatus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.mytap.R;
import org.androidtown.mytap.org.androidtown.myStatus.newPicker.AbstractWheel;
import org.androidtown.mytap.org.androidtown.myStatus.newPicker.AbstractWheelTextAdapter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class MyStatus extends Fragment {

    public static final int myRequestCode=5001;

    View vi;
    AbstractWheel pickerView;
    TextView commentText;
    String comment=null;
    boolean commentNull=true;
    String TAG = "MyStatus";
    int myImage=0;
    public static final int[] emoticon =
            new int[]{R.drawable.emoticon1, R.drawable.emoticon2, R.drawable.emoticon3, R.drawable.emoticon4};


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        vi= inflater.inflate(R.layout.my_status, container, false);
        pickerView = (AbstractWheel) vi.findViewById(R.id.pickerView);
        pickerView.setVisibleItems(3);
        pickerView.setViewAdapter(new EmoticonAdapter(getActivity(), emoticon));

        commentText= (TextView) vi.findViewById(R.id.commentText);

        //저장된게 있으면 불러와.
        loadMyStatus();

        pickerView.setOnSelectListener(new AbstractWheel.OnSelectListener() {
            int cnt=0;
            @Override
            public void onSelect(int position) {
                Log.d(TAG, "받은 position is" + position);
                myImage=position;
                if(cnt!=0)
                {
                    Log.d(TAG, "리스너에서 저장 cnt is " + cnt);
                    new CommentActivity().storeStatus(position, commentText.getText().toString());
                }
                cnt++;
            }
        });

        //코멘트 창 클릭하면
        commentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra("image", myImage);
                //입력 된게 있으면
                if (!commentNull) {
                    intent.putExtra("comment", commentText.getText());
                    intent.putExtra("length", commentText.getText().length());
                }else{
                    intent.putExtra("comment", "");
                    intent.putExtra("length", 0);
                }
                commentNull=true;
                startActivityForResult(intent, myRequestCode);
            }
        });
        return vi;
    }

    //intent 넘어온 결과 처리
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==myRequestCode && resultCode==getActivity().RESULT_OK)
        {
            comment = data.getStringExtra("comment");
            if(comment.equals(""))
            {
                commentText.setText("오늘 기분은 어떠세요?");
            }else{
                commentText.setText(comment);
            }
        }else if(requestCode==myRequestCode && resultCode==getActivity().RESULT_CANCELED)
        {
            Log.d(TAG, "cancel intent");
        }

    }


    public void loadMyStatus()
    {
        //오늘 날짜 받아서 url에 반영.
        String urlStr = "http://52.69.116.105:8000/load_current_moment/";
        new NetworkThread2().execute(urlStr);
    }

    class NetworkThread2 extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params)//...은 가변
        {
            String urlStr = params[0];
            StringBuffer buffer = new StringBuffer();
            String[] arr = new String[2];
            try {
                URL url = new URL(urlStr);
                InputStream is = url.openConnection().getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.d(TAG, " 전체 메세지 " + line);
                    buffer.append(line);
                }

                JSONObject root = new JSONObject(buffer.toString());
                String emotion = root.getString("emotion");
                Log.d(TAG, "emotion : " + emotion);
                String comment = root.getString("comment");
                Log.d(TAG, "comment : " + comment);
                arr[0] = emotion;
                arr[1] = comment;
            } catch (Exception e) {
                e.printStackTrace();
                arr[0]="0";
                arr[1]=null;
            }
            return arr;
        }

        @Override
        protected void onPostExecute(String[] s) {

            int position=Integer.parseInt(s[0]);

            pickerView.setCurrentItem(position);

            if(s[1]==null) {
                commentNull = true;
                commentText.setText("오늘 기분은 어떠세요?");
            }else{
                commentText.setText(s[1]);
            }
        }
    }
    private class EmoticonAdapter extends AbstractWheelTextAdapter
    {
        int[] emoticon;
        /**
         * Constructor
         */
        protected EmoticonAdapter(Context context, int[] emoticon) {
            super(context, R.layout.picker_item, NO_RESOURCE);
            this.emoticon=emoticon;

        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);

            ImageView img = (ImageView) view.findViewById(R.id.emotion);
            img.setImageResource(emoticon[index]);

            return view;

        }
        @Override
        public int getItemsCount() {
            return emoticon.length;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return null;
        }
    }

}
