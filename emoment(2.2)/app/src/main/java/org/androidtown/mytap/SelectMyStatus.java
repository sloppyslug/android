package org.androidtown.mytap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class SelectMyStatus extends Dialog {
    String TAG = "emoment2";
    Context mContext;

    PickerView pickerView;
    Button sendButton;
    Button backButton;
    Bitmap myImage;

    private ArrayList<Bitmap> items= new ArrayList<>();

    public SelectMyStatus(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);
        pickerView = (PickerView)findViewById(R.id.pickerView);
        sendButton=(Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "sendButton clicked");

                dismiss();
            }
        });
        backButton=(Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "backButton clicked");

                hide();
            }
        });
        Bitmap emoticon1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon1);
        Bitmap emoticon2 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon2);
        Bitmap emoticon3 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon3);
        Bitmap emoticon4 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.emoticon4);
        Bitmap bi = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.option);


        for(int i=0; i<5; i++)
        {
            items.add(emoticon1);
            items.add(emoticon2);
            items.add(emoticon3);
            items.add(emoticon4);
        }
        items.add(bi);
        items.add(0,bi);

        pickerView.setList(items);

        pickerView.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(Bitmap bitmap) {
                setImage(bitmap);
            }
        });

    }

    public void setImage(Bitmap bitmap)
    {
        myImage=bitmap;
    }
    public Bitmap getImage() { return  myImage;}


}
