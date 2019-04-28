package com.mylooms.darkroom.lab9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public Database(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table if not exists tbl_stud(id integer primary key,name varchar(20),dob varchar(20),tmark integer)");

    }

    public long insertData(int id,String name,String dob,int tmark){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("dob",dob);
        cv.put("tmark",tmark);
        long res=db.insert("tbl_stud",null,cv);
        return res;
    }

    public Cursor getData(int id){
        SQLiteDatabase db=this.getWritableDatabase();

        String query = "SELECT * FROM tbl_stud WHERE id =" + id ;
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }
    public long updateData(String id,String name,String dob,int tmark){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("dob",dob);
        cv.put("tmark",tmark);
        long res=db.update("tbl_stud",cv,"id=?",new String[] {id});
        return res;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
