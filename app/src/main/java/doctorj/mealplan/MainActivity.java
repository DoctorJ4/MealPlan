package doctorj.mealplan;

import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();
    }

    private void populateListView(){
        int planLength = 30;
        MealPlan plan = new MealPlan(planLength);

        String[] mealNames = {"Grilled Cheese", "Alfredo", "Spaghetti"};
        //String[] mealNames = MealPlan.getNames;    //TODO -> once sql is set up do this

        //TODO -> mealNames will be sub item and Date will be main item in listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, mealNames);
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
