package doctorj.mealplan;


import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    int planLength;
    MealPlan plan;

    String DB_PATH = "raw/";

    final Context context=this;
    private SQLiteDatabase db1;
    //DBMain db;
    private static String DB_NAME ="mealplan.db"; // to change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*DBMain db;
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

        this.db1 = openOrCreateDatabase(DB_PATH+DB_NAME,SQLiteDatabase.CREATE_IF_NECESSARY,null);*/
        populateListView();
    }

    private void populateListView(){
        String[] mealNames = {"Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti","Grilled Cheese", "Alfredo", "Spaghetti"};
        String month = "April";
        this.planLength = 30;
        this.plan = new MealPlan(month, this.planLength, mealNames);
        //this.plan = new MealPlan(planLength, db1);

        //String[] mealNames = plan.getNames();    //TODO -> once sql is set up do this

        //TODO -> mealNames will be sub item and Date will be main item in listview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,this.plan.getNames());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,this.plan.getSchedule());
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
