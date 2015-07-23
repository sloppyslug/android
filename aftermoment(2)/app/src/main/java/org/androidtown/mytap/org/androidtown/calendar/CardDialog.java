package org.androidtown.mytap.org.androidtown.calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.mytap.R;

/**
 * Created by constant on 15. 7. 17..
 */
public class CardDialog extends Dialog {

    ImageView imageView;
    TextView textView;
    Context mContext;
    String emotion;
    String comment;

    String TAG = "MYDIALOG";
    public CardDialog(Context context, String emotion, String comment) {
        super(context);
        mContext=context;
        this.emotion = emotion;
        this.comment = comment;
        Log.d(TAG, "emotion is " + emotion + " comment is " + comment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_dialog);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        int tempInt = Integer.parseInt(emotion);
        Log.d(TAG, "tempInt is" + tempInt);

        switch (tempInt) {
            case 0:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon1));
                break;
            case 1:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon2));
                break;
            case 2:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon3));
                break;
            case 3:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon4));
                break;
        }
        Log.d(TAG, "card comment is " + comment);
        textView.setText(comment);
    }
}
