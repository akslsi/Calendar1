package com.example.chenjutsu.calendar1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class calenderActivity extends AppCompatActivity {
    public static int yr;
    public static int mon;
    public static int day;
    public static String time;
    public static CalendarView calendarView;

    public static DaoMaster.DevOpenHelper eventCalendarDBHelper;
    public static SQLiteDatabase eventCalendarDB;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;
    public static EVENTDao eventDao;
    List<EVENT> eventListFromDB;
    public static ArrayList<String> eventLists;
    public static long ID;

    public static ArrayAdapter adapt;
    public static ArrayList<Long> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initDatabase();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, calenderFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }

    public void initDatabase(){
        eventCalendarDBHelper = new DaoMaster.DevOpenHelper(this,"Calendar1.sqlite",null);
        eventCalendarDB = eventCalendarDBHelper.getWritableDatabase();

        daoMaster = new DaoMaster(eventCalendarDB);

        daoMaster.createAllTables(eventCalendarDB, true);

        daoSession = daoMaster.newSession();

        eventDao = daoSession.getEVENTDao();

        if(eventDao.queryBuilder().where(EVENTDao.Properties.Display.eq(true)).list() == null){
            closeReopenDatabase();
        }
        eventListFromDB = eventDao.queryBuilder().where(EVENTDao.Properties.Display.eq(true)).list();
        if(eventListFromDB != null){
            for(EVENT event : eventListFromDB){
                if(event == null){
                    return;
                }
            }
        }
    }

    public  void closeDatabase(){
        daoSession.clear();
        eventCalendarDB.close();
        eventCalendarDBHelper.close();
    }

    public void closeReopenDatabase(){
        closeDatabase();
        eventCalendarDBHelper = new DaoMaster.DevOpenHelper(this,"Calendar1.sqlite",null);
        eventCalendarDB = eventCalendarDBHelper.getWritableDatabase();
        daoMaster = new DaoMaster(eventCalendarDB);
        daoMaster.createAllTables(eventCalendarDB, true);
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEVENTDao();
    }

}
