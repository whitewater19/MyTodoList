package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    ListView list_memo;
    int item_id;
    private DbAdapter dbAdapter;
    ArrayList<Memo> memos = new ArrayList<>();
    Cursor cursor;
    MemoAdapter dataSimpleAdapter;

    private AlertDialog dialog = null;
    AlertDialog.Builder builder = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_memo= findViewById(R.id.memo);
        dbAdapter=new DbAdapter(this);
        displayList();
        list_memo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.move(position);
                item_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                intent = new Intent();
                intent.putExtra("item_id", item_id);
                intent.putExtra("type","EDIT");
                intent.setClass(MainActivity.this, EditMemoActivity.class);
                startActivity(intent);
            }
        });



        list_memo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.move(position);
                item_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                dialog = builder.create();
                dialog.show();

                return true;
            }
        });
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("訊息")
                .setMessage("確定刪除此便條?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    //設定確定按鈕
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        boolean is_deleted = dbAdapter.deleteMemo(item_id);
                        if(is_deleted) {
                            Toast.makeText(MainActivity.this, "已刪除!", Toast.LENGTH_SHORT).show();
                            memos = new ArrayList<>();
                            displayList();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    //設定取消按鈕
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
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

            intent.putExtra("type","add");
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
