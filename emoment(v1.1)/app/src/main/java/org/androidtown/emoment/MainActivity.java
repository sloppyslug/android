package org.androidtown.emoment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    ImageView imageView;
    PickerView picker;
    Handler handler = new Handler();

    private ArrayList<Bitmap> items= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picker=(PickerView)findViewById(R.id.pickerView);
        imageView = (ImageView)findViewById(R.id.imageView);

        Bitmap emoticon1 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon1);
        Bitmap emoticon2 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon2);
        Bitmap emoticon3 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon3);
        Bitmap emoticon4 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon4);
        Bitmap bi = BitmapFactory.decodeResource(getResources(),R.drawable.option);


        for(int i=0; i<5; i++)
        {
            items.add(emoticon1);
            items.add(emoticon2);
            items.add(emoticon3);
            items.add(emoticon4);
        }
        items.add(bi);
        items.add(0,bi);

        picker.setList(items);

        picker.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        });


    }
}
