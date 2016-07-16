package com.lin.a3dmgame.commondao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * /**去掉了：【flag、color、filename、siteurl。空值。】
 * Created by my on 2016/7/9.
 */
public class NewsSQliteOpenHelper extends SQLiteOpenHelper {
    public NewsSQliteOpenHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists news (id String,typeid String,typeid2 String,sortrank String," +
                "flag String,ismake String,channel String,arcrank String,click String,money String,title String,shorttitle String,color String,writer String," +
                "source String,litpic String,pubdate String,senddate String,mid String,keywords String,lastpost String,scores String,goodpost String,badpost String," +
                "voteid String,notpost String,description String,filename String,dutyadmin String,tackid String,mtype String,weight String,fby_id String," +
                "game_id String,feedback String,typedir String,typename String,corank String,isdefault String,defaultname String,namerule String," +
                "namerule2 String,ispart String,moresite String,siteurl String,sitepath String,arcurl String ,typeurl String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
