package doctorj.mealplan;

import android.app.Fragment;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.List;

import static doctorj.mealplan.GlobalPlan.globalPlan;


public class MainActivity extends ActionBarActivity {
    private int planLength;
    private String name;
    private int numPlans;
    private int startMonth;
    private int startDay;
    private int startYear;
    private int endMonth;
    private int endDay;
    private int endYear;
    public static MealPlan plan;
    RecipeHelper db;

    public boolean editflag = false;
    ArrayAdapter<String> spinnerAdapter;

    MealPlanHelper MPdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new RecipeHelper(this);
        MPdb = new MealPlanHelper(this);
        numPlans = MPdb.getNumPlans();
        this.planLength = 30;
        this.plan = globalPlan;
        this.setTitle(plan.getPlanName());
        populateListView();
    }

    @Override
    protected void onResume() {
        this.plan = MPdb.getPlan(this.plan.getMP_ID());
        populateListView();
        //refreshListView();
        super.onResume();
    }

    private void refreshListView(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,this.plan.getSchedule());
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
    }

    private void populateListView(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items,this.plan.getSchedule());
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(editflag == false) {
                    Intent act = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    act.putExtra("StringName", plan.getMealName(position));
                    act.putExtra("StringIngredients", plan.getMealIngredientsString(position));
                    act.putExtra("StringDirections", plan.getMealDirections(position));
                    startActivity(act);
                }
                else {
                    Intent act = new Intent(getApplicationContext(), MealPlanEditActivity.class);

                    act.putExtra("recipeIndex", plan.getMealIndex(position));
                    startActivity(act);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
            return super.onOptionsItemSelected(item);
        }
        else if(id == R.id.edit_items)
        {
            if(item.isChecked() == false) {
                item.setChecked(true);
                editflag = true;

            }
            else if(item.isChecked() == true){
                item.setChecked(false);
                editflag = false;
            }
        }
        //return super.onOptionsItemSelected(item);
        return false;
    }
}
