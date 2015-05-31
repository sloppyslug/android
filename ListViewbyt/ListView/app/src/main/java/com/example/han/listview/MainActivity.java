package com.example.han.listview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ItemAdapter(this);

        for(int i=1;i<16;i++) {
            adapter.song.add("노래" + i);
            adapter.singer.add("가수" + i);
        }

        listView.setAdapter(adapter);
        listView.setFocusable(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"선택된 아이템 : " + adapter.song.get(position)+ " " + adapter.singer.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            String song = data.getExtras().getString("song");
            String singer = data.getExtras().getString("singer");

            adapter.song.add(song);
            adapter.singer.add(singer);
            
            //getView를 다시 그려줌
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"추가된 아이템 : "+ song + " "+singer,Toast.LENGTH_LONG).show();


        }

    }

    public void onAddButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(),SongInfor.class);
        startActivityForResult(intent,1001);

    }

    public void onCancleButtonClicked(View v){
        finish();
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
