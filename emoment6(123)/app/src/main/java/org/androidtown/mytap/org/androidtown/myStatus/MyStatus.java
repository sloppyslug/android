package org.androidtown.mytap.org.androidtown.myStatus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidtown.mytap.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class MyStatus extends Fragment {

    public static final int myRequestCode=5001;

    View vi;
    PickerView pickerView;
    TextView commentText;
    String comment=null;
    boolean commentNull=false;
    int commentLength;
    String TAG = "MyStatus";
    int myPosition;
    public static final ArrayList<Bitmap> items= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        vi= inflater.inflate(R.layout.my_status, container, false);
        pickerView = (PickerView) vi.findViewById(R.id.pickerView);
        commentText= (TextView) vi.findViewById(R.id.commentText);

        //이모티콘 로드
        loadEmoticon();
        //저장된게 있으면 불러와.
        loadMyStatus();


        //코멘트 창 클릭하면
        commentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra("image", myPosition);
                //입력 된게 있으면
                if (!commentNull) {
                    intent.putExtra("comment", commentText.getText());
                    intent.putExtra("length", commentText.getText().length());
                }else{

                }
                commentNull=false;
                startActivityForResult(intent, myRequestCode);
            }
        });

        //pickerView 선택 될 때 마다
        pickerView.setOnSelectListener(new PickerView.OnSelectListener() {
            int cnt=0;
            @Override
            public void onSelect(int position) {
                Log.d(TAG, "받은 position is" + position);
                setImage(position);
                Log.d(TAG, "setImage is " + position);
                if(cnt!=0)
                {
                    Log.d(TAG, "리스너에서 저장 cnt is " + cnt);
                    new CommentActivity().storeStatus(position, commentText.getText().toString());
                }
                cnt++;
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
            Log.d(TAG, "get intent");
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

    public void setImage(int position)
    {
        myPosition=position;
    }

    public void loadEmoticon()
    {

        Bitmap emoticon1 = BitmapFactory.decodeResource(vi.getResources(), R.drawable.emoticon1);
        Bitmap emoticon2 = BitmapFactory.decodeResource(vi.getResources(),R.drawable.emoticon2);
        Bitmap emoticon3 = BitmapFactory.decodeResource(vi.getResources(),R.drawable.emoticon3);
        Bitmap emoticon4 = BitmapFactory.decodeResource(vi.getResources(), R.drawable.icon);

        items.add(emoticon1);
        items.add(emoticon2);
        items.add(emoticon3);
        items.add(emoticon4);
        pickerView.setList(items);
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
                arr[0]="1";
                arr[1]=null;
            }
            return arr;
        }

        @Override
        protected void onPostExecute(String[] s) {

            int position=Integer.parseInt(s[0]);
            Log.d(TAG, "OnPostExecute " + position);

            pickerView.setImagePosition(position);

            if(s[1]==null)
            {
                commentNull = true;
                commentText.setText("오늘 기분은 어떠세요?");
            }else{
                commentText.setText(s[1]);
            }
        }
    }

}
