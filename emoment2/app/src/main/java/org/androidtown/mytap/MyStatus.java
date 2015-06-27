package org.androidtown.mytap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by sangsoo on 2015-06-27.
 */
public class MyStatus extends Fragment{
    PickerView picker;
    ImageView imageView;

    private ArrayList<Bitmap> items= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi=inflater.inflate(R.layout.my_status, container, false);
        picker = (PickerView)vi.findViewById(R.id.pickerView);
        imageView = (ImageView)vi.findViewById(R.id.imageView);

        Bitmap emoticon1 = BitmapFactory.decodeResource(getResources(), R.drawable.emoticon1);
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
        return vi;
    }
}
