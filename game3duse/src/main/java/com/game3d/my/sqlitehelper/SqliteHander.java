package com.game3d.my.sqlitehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import com.game3d.my.modles.News;

/**
 * Created by my on 2016/7/6.
 */
public class SqliteHander {
    private Context context;
    private OpenHelper helper;
    public SqliteHander(Context context){
        this.context = context;
        helper = new OpenHelper(context);
    }
    /*
        向数据库中增加News数据
     */
    public synchronized void insertToNewstable(News news,String filename){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = getAllTypeCursor();
        while(cursor.moveToNext()){
            String title = cursor.getColumnName(cursor.getColumnIndex("title")).trim();
            if(title.equals(news.getTitle().trim())){
                return;
            }
        }
        ContentValues values = new ContentValues();
        values.put("id", news.getId());
        values.put("typeid", news.getTypeid());
        values.put("typeid2", news.getTypeid2());
        values.put("sortrank", news.getSortrank());
        values.put("flag", news.getFlag());
        values.put("ismake", news.getIsmake());
        values.put("channel", news.getChannel());
        values.put("arcrank", news.getArcrank());
        values.put("click", news.getClick());
        values.put("money", news.getMoney());
        values.put("title", news.getTitle());
        values.put("shorttitle", news.getShorttitle());
        values.put("color", news.getColor());
        values.put("writer", news.getWriter());
        values.put("source", news.getSource());
        values.put("litpic", filename);
        values.put("pubdate", news.getPubdate());
        values.put("senddate", news.getSenddate());
        values.put("mid", news.getMid());
        values.put("keywords", news.getKeywords());
        values.put("lastpost", news.getLastpost());
        values.put("scores", news.getScores());
        values.put("goodpost", news.getGoodpost());
        values.put("badpost", news.getBadpost());
        values.put("voteid", news.getVoteid());
        values.put("notpost", news.getNotpost());
        values.put("description", news.getDescription());
        values.put("filename", news.getFilename());
        values.put("dutyadmin", news.getDutyadmin());
        values.put("tackid", news.getTackid());
        values.put("mtype", news.getMtype());
        values.put("weight", news.getWeight());
        values.put("fby_id", news.getFby_id());
        values.put("game_id", news.getGame_id());
        values.put("feedback", news.getFeedback());
        values.put("typedir", news.getTypedir());
        values.put("typename", news.getTypename());
        values.put("corank", news.getCorank());
        values.put("isdefault", news.getIsdefault());
        values.put("defaultname", news.getDefaultname());
        values.put("namerule", news.getNamerule());
        values.put("namerule2", news.getNamerule2());
        values.put("ispart", news.getIspart());
        values.put("moresite", news.getMoresite());
        values.put("siteurl", news.getSiteurl());
        values.put("sitepath", news.getSitepath());
        values.put("typeurl", news.getTypeurl());
        values.put("arcurl",news.getArcurl());
        db.insert("news",null,values);
        if(db!=null){
            db.close();
        }
    }
    /*
        返回所有的文章的Cursor
     */
    public Cursor getAllTypeCursor(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor =  db.query("news",new String[]{"title"},null,null,null,null,null);
        return  cursor;
    }
    /*
        返回分页显示的数据
     */
    public Cursor getCursorlimit(int pageindex,int pagesize){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("news",null,null,null,null,null,null,(pageindex-1)*pagesize+","+pagesize);
        return cursor;
    }
    /*
        返回类型为2的Cursor
     */
    public Cursor getTypeCursor(int type,int pageindex,int pagesize){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("news",null,"typeid=?",new String[]{type+""},null,null,null,(pageindex-1)*pagesize+","+pagesize);
        return cursor;
    }

}
