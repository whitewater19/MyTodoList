package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    ListView list_memo;
    private DbAdapter dbAdapter;
    ArrayList<Memo> memos = new ArrayList<>();
    Cursor cursor;
    MemoAdapter dataSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_memo= findViewById(R.id.memo);
        dbAdapter=new DbAdapter(this);
        displayList();

    }

    private void displayList() {
        cursor = dbAdapter.listMemos();
        if(cursor.moveToFirst()){
            do{
                memos.add(new Memo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)));

            }while(cursor.moveToNext());
        }
        cursor.moveToFirst();
        dataSimpleAdapter = new MemoAdapter(this,memos);
        list_memo.setAdapter(dataSimpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add) {
            intent = new Intent(MainActivity.this,EditMemoActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
