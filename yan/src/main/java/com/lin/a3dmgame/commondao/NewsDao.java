package com.lin.a3dmgame.commondao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lin.a3dmgame.models.News;


/**
 * Created by my on 2016/7/9.
 */
public class NewsDao {
    //Dao   database access object  对数据库数据的访问层。

    private NewsSQliteOpenHelper sQliteOpenHelper;
    private Context context;

    public NewsDao(Context context) {
        this.context = context;
        sQliteOpenHelper = new NewsSQliteOpenHelper(context);
        //这里又没有初始化数据库帮助类，诶，调用Dao工具的时候就没初始化。和Json里的没初始化数组一样，草
        //Dao类，是对数据库的操作类，想用Dao先初始化Dao，但用Dao必须先初始化Dao需要的数据库帮助类。，所有在构造方法里初始化。
    }

    /**
     * 添加数据到数据库的News表中。news对象。对数据库的操作类，先根据数据库帮助类得到数据库。
     */
    public synchronized void addNewsToSQliteTable(News news, String filename) {
        //添加数据，先判断数据库里面有没有这个数据，根据title属性是否相同来判断。
        SQLiteDatabase db = sQliteOpenHelper.getReadableDatabase();
        String newtitle = news.getTitle().trim();
        Cursor cursor = getAllTitleCursor();
        //把要加入的作为是否唯一的title和已经存在的所有title进行比较。
        while (cursor.moveToNext()) {
            String title = cursor.getColumnName(cursor.getColumnIndex("title")).trim();
//            String title = cursor.getColumnName(0); ………………………………………………
            if (title.equals(newtitle)) {
                return;//比较插入的title,一旦相同就返回。
            }
        }
        //进入到这里数据就是新的数据，要添加进数据库。
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
        values.put("litpic", filename);//添加数据到数据库的时候，注意Dao方法里的这里，传来sd卡图片目录，直接放在加入的news对象图片值中。
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
        values.put("arcurl", news.getArcurl());
        db.insert("news", null, values);
        //数据添加完，关闭数据库。
        if (db != null) {
            db.close();
        }
    }

    /**
     * 得到所有文章的Cursor      title
     */
    public Cursor getAllTitleCursor() {
        SQLiteDatabase db = sQliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("news", new String[]{"title"}, null, null, null, null, null);
        return cursor;
    }

    /**
     * 分页显示数据的cursor
     */
    public Cursor getCursorPaging(int pageindex, int pagesize) {
        SQLiteDatabase db = sQliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("news", null, null, null, null, null, null, (pageindex - 1) * pagesize + "," + pagesize);
        //这里query参数少了个null，方法就变了。仔细！  报错代码在下面
        return cursor;
    }

    /**
     * 返回指定type的cursor
     */
    public Cursor getTypeCursor(int type, int pageindex, int pagesize) {
        SQLiteDatabase db = sQliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("news", null, "type=?", new String[]{type + ""}, null, null, null, pagesize * (pageindex - 1) + "," + pagesize);
        return cursor;
    }
}
/*
*
 * 07-11 04:31:56.397 4289-4800/com.lin.a3dmgame E/AndroidRuntime: FATAL EXCEPTION: Thread-157
 * Process: com.lin.a3dmgame, PID: 4289
 * android.database.sqlite.SQLiteException: 1st ORDER BY term out of range - should be between 1 and 48 (code 1): , while compiling: SELECT * FROM news ORDER BY 0,10
 * at android.database.sqlite.SQLiteConnection.nativePrepareStatement(Native Method)
 * at android.database.sqlite.SQLiteConnection.acquirePreparedStatement(SQLiteConnection.java:889)
 * at android.database.sqlite.SQLiteConnection.prepare(SQLiteConnection.java:500)
 * at android.database.sqlite.SQLiteSession.prepare(SQLiteSession.java:588)
 * at android.database.sqlite.SQLiteProgram.<init>(SQLiteProgram.java:58)
 * at android.database.sqlite.SQLiteQuery.<init>(SQLiteQuery.java:37)
 * at android.database.sqlite.SQLiteDirectCursorDriver.query(SQLiteDirectCursorDriver.java:44)
 * at android.database.sqlite.SQLiteDatabase.rawQueryWithFactory(SQLiteDatabase.java:1314)
 * at android.database.sqlite.SQLiteDatabase.queryWithFactory(SQLiteDatabase.java:1161)
 * at android.database.sqlite.SQLiteDatabase.query(SQLiteDatabase.java:1032)
 * at android.database.sqlite.SQLiteDatabase.query(SQLiteDatabase.java:1200)
 * at com.lin.a3dmgame.commondao.NewsDao.getCursorPaging(NewsDao.java:114)
 * at com.lin.a3dmgame.fragment.ChapterFragment_Inner$2.run(ChapterFragment_Inner.java:102)
 * at java.lang.Thread.run(Thread.java:841)
*/
