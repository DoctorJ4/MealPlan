package doctorj.mealplan;


import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends ActionBarActivity {
    int planLength;
    MealPlan plan;

    String DB_PATH = "raw/";

    final Context context=this;
    public SQLiteDatabase db1;
    private static String DB_NAME ="mealplan.db"; // to change


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();

        DBMain db;
        db = new DBMain(this);

        try {
            db.createDB();
        } catch (IOException ioe) {

            throw new Error("Database not created....");
        }

        try {
            db.openDB();

        }catch(SQLException sqle){

            throw sqle;
        }

        //SQLiteDatabase db1;
        db1=openOrCreateDatabase(DB_PATH+DB_NAME,SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c= db1.rawQuery("SELECT * FROM bank",null); // TODO fix query

        c.moveToFirst();
        String temp="";

        while(! c.isAfterLast())
        {
            String s2=c.getString(0);
            String s3=c.getString(1);
            String s4=c.getString(2);
            temp=temp+"\n Id:"+s2+"\tType:"+s3+"\tBal:"+s4;

            c.moveToNext();

        }
    }

    private void populateListView(){
        this.planLength = 30;
        this.plan = new MealPlan(planLength);
        //String month = "April";
        String[] mealNames = {"Grilled Cheese", "Alfredo", "Spaghetti"};
        //String[] mealNames = MealPlan.getNames();    //TODO -> once sql is set up do this

        //TODO -> mealNames will be sub item and Date will be main item in listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,mealNames);
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
