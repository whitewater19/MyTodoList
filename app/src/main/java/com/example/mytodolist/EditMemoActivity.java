package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditMemoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTitle;
    EditText edtmemo;
    Spinner sp_colors;
    Button btn_back,btn_ok;
    Intent intent;
    SppinerAdapter sppinerAdapter;
    String[] colors;
    ArrayList<Colordata> color_list;
    String selected_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);

        initView();

        btn_back=findViewById(R.id.btn_back);
        btn_ok=findViewById(R.id.btn_ok);
        btn_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }
    private  void  initView(){
        txtTitle = findViewById(R.id.txtTitle);
        edtmemo=findViewById(R.id.edtMemo);
        sp_colors=findViewById(R.id.sp_colors);
        colors = getResources().getStringArray(R.array.colors);
        LinearLayout container = new LinearLayout(this);
        color_list = new ArrayList<Colordata>();

        color_list.add(new Colordata("Red","#e4222d"));
        color_list.add(new Colordata("Green","#00c7a4"));
        color_list.add(new Colordata("Blue","#4b7bd8"));
        color_list.add(new Colordata("Orange","#fc8200"));
        color_list.add(new Colordata("Cyan","#18ffff"));

        sppinerAdapter = new SppinerAdapter(this,color_list);
        sp_colors.setAdapter(sppinerAdapter);
        sp_colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_color = color_list.get(position).getCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                intent=new Intent(EditMemoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_ok:{

                break;
            }
        }

    }
}
