package org.androidtown.emoment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    ImageView imageView;
    ListView listView;
    ListItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setDivider(null);

        adapter = new ListItemAdapter(this);

        Bitmap emoticon1 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon1);
        Bitmap emoticon2 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon2);
        Bitmap emoticon3 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon3);
        Bitmap emoticon4 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon4);
        Bitmap emoticon6 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon1);
        Bitmap emoticon7 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon1);
        Bitmap emoticon8 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon2);
        Bitmap emoticon9 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon3);
        Bitmap emoticon10 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon4);
        Bitmap emoticon12 = BitmapFactory.decodeResource(getResources(),R.drawable.emoticon1);

        adapter.image.add(emoticon1);
        adapter.image.add(emoticon2);
        adapter.image.add(emoticon3);
        adapter.image.add(emoticon4);
        adapter.image.add(emoticon6);
        adapter.image.add(emoticon7);
        adapter.image.add(emoticon8);
        adapter.image.add(emoticon9);
        adapter.image.add(emoticon10);
        adapter.image.add(emoticon12);

        listView.setAdapter(adapter);
        listView.setFocusable(false);

        //리스트뷰 아이템 클릭하면 위에 화면에 뜸
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageBitmap(adapter.image.get(position));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
