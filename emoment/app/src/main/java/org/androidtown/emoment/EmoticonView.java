package org.androidtown.emoment;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sangsoo on 2015-05-25.
 */
public class EmoticonView extends RelativeLayout {
    ImageView imageView;
    TextView textView;
    public EmoticonView(Context context) {
        super(context);
        init(context);
    }

    public EmoticonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context)
    {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        imageView = (ImageView)findViewById(R.id.imageView);
    }
    public void setImage(Bitmap bit)
    {
        imageView.setImageBitmap(bit);
    }

}
