package com.example.tarea_ix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "alimentos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Alimentos(name TEXT primary key, image INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Alimentos");
    }

    public Boolean insertdata(String name, Integer image)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("image", image);
        long result = DB.insert("Alimentos", null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from Alimentos", null);
        return cursor;
    }

    public Boolean editdata(String name, Integer imageValue)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Alimentos where name = ?", new String[]{name});
        if(cursor.getCount()>0)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("image", imageValue);

            long result = DB.update("Alimentos", contentValues,"name=?", new String[]{name});

            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

    public Boolean deletedata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Alimentos where name = ?", new String[]{name});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("Alimentos", "name=?", new String[]{name});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

}
