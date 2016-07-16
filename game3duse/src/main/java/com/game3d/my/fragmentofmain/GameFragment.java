package com.game3d.my.fragmentofmain;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.game3d.my.adapter.GameItemAdapter;
import com.game3d.my.game3duse.R;
import com.game3d.my.listen.GridItemSelectListener;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/6.
 */
public class GameFragment extends Fragment {
    View view;
    Spinner spinner;
    SqliteHander sqliteHander;
    String[] spinnerstrings = {"动作(ART)", "射击(FPS)", "角色扮演(RPG)", "养成(GAL)"};
    int  type = 181;
    int pageindex = 1;
    int pagesize = 10;
    PullToRefreshGridView pullToRefreshGridView;
    GridView gridView;
    ArrayAdapter<String> spinneradapter;
    List<HashMap<String, String>> data;
    Handler handler;
    GameItemAdapter adapter;
    Cursor cursor;
    GridItemSelectListener gridItemSelectListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_game, null);
        pullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.pulltorefreshgridview);
        gridView = pullToRefreshGridView.getRefreshableView();
        gridView.setNumColumns(3);
        sqliteHander = new SqliteHander(getContext());
        spinner = (Spinner) view.findViewById(R.id.game_spinner);
        spinneradapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerstrings);
        spinner.setAdapter(spinneradapter);

        //给spinner 加监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String typechoose = spinnerstrings[position];
                data.clear();
                if(typechoose.contains("ART")){
                    type = 181;
                }
                if(typechoose.contains("FPS")){
                    type = 182;
                }
                if(typechoose.contains("RPG")){
                    type = 183;
                }
                if(typechoose.contains("GAL")){
                    type = 184;
                }
                setData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //数据的处理
        handler = new Handler();
        data = new ArrayList<>();
         setData();
        adapter = new GameItemAdapter(getContext(), data, handler);
        gridView.setAdapter(adapter);
        gridItemSelectListener = new GridItemSelectListener(getActivity(),data);
        gridView.setOnItemClickListener(gridItemSelectListener);
        return view;
    }
    //得到数据
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cursor = sqliteHander.getTypeCursor(type,pageindex, pagesize);
                while (cursor.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String typename = cursor.getString(cursor.getColumnIndex("typename"));
                    String writer = cursor.getString(cursor.getColumnIndex("writer"));
                    String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));
                    // Log.i("12345",arcurl+"+++++++");
                    map.put("litpic", litpic);
                    map.put("title", title);
                    map.put("typename",typename);
                    map.put("writer",writer);
                    map.put("keywords",keywords);
                    map.put("description",description);
                    data.add(map);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                       // pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        }).start();
    }

}
