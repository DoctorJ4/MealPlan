package doctorj.mealplan;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    int planLength;
    public static MealPlan plan;
    RecipeHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new RecipeHelper(this);

        int month = 4;  //April
        this.planLength = 30;
        plan = new MealPlan(month, planLength, db);

        populateListView();
    }

    private void populateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,this.plan.getSchedule());
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent act = new Intent(getApplicationContext(), RecipeViewActivity.class);

                act.putExtra("StringName", plan.getMealName(position));
                act.putExtra("StringIngredients", plan.getMealIngredientsString(position));
                act.putExtra("StringDirections", plan.getMealDirections(position));
                startActivity(act);
            }
        });
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
        //startActivity(Display_Details);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {

            Intent act = new Intent(getApplicationContext(), CalendarView.class);
            startActivity(act);
        }

        return super.onOptionsItemSelected(item);
    }
}
