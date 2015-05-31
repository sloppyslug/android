package com.example.han.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HAN on 2015. 3. 7..
 */
public class ItemView extends LinearLayout {

    Button button;
    TextView nameTextView;
    TextView numberTextView;

    public ItemView(Context context) {
        super(context);

        init(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item, this, true);

        nameTextView =  (TextView) findViewById(R.id.nameTextView);
        numberTextView =  (TextView) findViewById(R.id.numberTextView);
        nameTextView.setTextSize(20);
        numberTextView.setTextSize(15);

        button = (Button) findViewById(R.id.delete);
        // 아이템에 버튼을 넣으면 아이템 전체가 클릭 안되고 버튼만 클릭되서 메인엑티비티의 리스트뷰와 아이템안에있는 버튼에 setFocusable(false)를 해줘야함
        // setFocusale(false)은 그 객체를 눌렀을때 객체에게 포커스가 넘어가는것을 막음
        button.setFocusable(false);


    }

    public void setItem(String name, String number){
        nameTextView.setText(name);
        numberTextView.setText(number);
    }



}
