package com.mylooms.darkroom.lab9;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText id,name,dob,tmark;
    Button submit,sel,upd,del,vi;
    ListView lv;
    private int activity_main;
    private int activity_main1;
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         id=(EditText)findViewById(R.id.rno);
         name=(EditText)findViewById(R.id.name);
         dob=(EditText)findViewById(R.id.dob);
         tmark=(EditText)findViewById(R.id.tmark);
         submit=(Button)findViewById(R.id.submit);
         sel=(Button)findViewById(R.id.sel);
         submit.setOnClickListener(this);
        vi=(Button)findViewById(R.id.vi);
        vi.setOnClickListener(this);
         sel.setOnClickListener(this);

        upd=(Button)findViewById(R.id.upd);
        upd.setOnClickListener(this);
        submit.setOnClickListener(this);
         myDb=new Database(this);
         lv=(ListView)findViewById(R.id.lv);

    }

    @Override
    public void onClick(View v) {
        if(!id.getText().toString().equals("")) {
            int rno = Integer.parseInt(id.getText().toString());

            if (v == submit || v== upd) {
                if(!(tmark.getText().toString().equals("")||name.getText().toString().equals("")||dob.getText().toString().equals(""))) {
                    int total = Integer.parseInt(tmark.getText().toString());
                    if(v==submit){
                    long res = myDb.insertData(rno, name.getText().toString(), dob.getText().toString(), total);
                    if (res > 0) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                    } else Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
                else if (v==upd){

                            long res=myDb.updateData(id.getText().toString(), name.getText().toString(), dob.getText().toString(), total);
                            if(res>0){
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                            }else Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();

                    }
                }

                else Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
            }

            if (v == sel) {

                    Cursor c = myDb.getData(rno);
                    name.setText(c.getString(1));
                   dob.setText(c.getString(2));
                   tmark.setText(c.getString(3));
                }
            if(v == vi) {
                Cursor res = myDb.getData(rno);

                if (res.getCount() == 0) {
                    //showMessage("Error","Empty Db");
                    Toast.makeText(MainActivity.this, "Empty Database", Toast.LENGTH_LONG).show();

                } else {
                    ArrayList<String> res1 = new ArrayList();
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, res1);

                    do {

                        String rec = "ID : " + res.getString(0) + "\t Name  : " + res.getString(1) + "\t DOB :"
                                + res.getString(2) + "\t Total  : " + res.getString(3) + "\n";
                        Toast.makeText(MainActivity.this, "Empty Database"+rec, Toast.LENGTH_LONG).show();
                        res1.add(rec);



                    }while (res.moveToNext());

                    lv.setAdapter(adapter);
                }
            }



        }else{
            Toast.makeText(MainActivity.this, "Please enter roll number", Toast.LENGTH_LONG).show();
            id.requestFocus();

        }

    }
}
