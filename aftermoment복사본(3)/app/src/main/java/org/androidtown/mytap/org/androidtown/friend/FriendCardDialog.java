package org.androidtown.mytap.org.androidtown.friend;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.mytap.MainActivity;
import org.androidtown.mytap.R;

/**
 * Created by constant on 15. 7. 17..
 */
public class FriendCardDialog extends Dialog {

    TextView title;
    ImageView imageView;
    TextView textView;
    Context mContext;
    String emotion;
    String comment;
    String titleComment;

    public FriendCardDialog(Context context, String emotion, String comment, String title) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext=context;
        this.emotion = emotion;
        this.comment = comment;
        this.titleComment=title;
        Log.d(MainActivity.TAG, "emotion is " + emotion + " comment is " + comment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_dialog);
        title = (TextView)findViewById(R.id.title);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        int tempInt = Integer.parseInt(emotion);
        Log.d(MainActivity.TAG, "tempInt is" + tempInt);

        switch (tempInt) {
            case 0:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon1));
                break;
            case 1:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emoticon2));
                break;
            case 2:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emotion_happy2));
                break;
            case 3:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emotion_happy2));
                break;
            case 4:
                imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emotion_happy2));
                break;
        }
        Log.d(MainActivity.TAG, "card comment is " + comment);
        textView.setText(comment);
        title.setText(titleComment);
    }
}
