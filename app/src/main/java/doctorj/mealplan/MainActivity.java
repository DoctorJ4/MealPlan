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
        int month = 4;  //April
        this.planLength = 30;

        Bundle extras = getIntent().getExtras();

        if ((GlobalPlan.globalPlan == null) && (extras != null)) {
            if(extras.getString("PlanName").isEmpty())
                this.name = "Plan " + Integer.toString(numPlans);
            else
                this.name = extras.getString("PlanName");
            this.startDay = extras.getInt("startDay");
            this.startMonth = extras.getInt("startMonth");
            this.startYear = extras.getInt("startYear");
            this.endDay = extras.getInt("endDay");
            this.endMonth = extras.getInt("endMonth");
            this.endYear = extras.getInt("endYear");
            plan = new MealPlan(numPlans, name, startMonth, startDay, startYear, endMonth, endDay, endYear, db);
            //plan = new MealPlan(numPlans, name, 5, 30, 2015, 7, 30, 2015, db);
            GlobalPlan.globalPlan = plan;
            MPdb.saveMealPlan(plan);
        }
        else if (GlobalPlan.globalPlan == null){
            plan = new MealPlan(numPlans, "Plan " + Integer.toString(numPlans), "January 1", "January 30", planLength, db);
            GlobalPlan.globalPlan = plan;
            MPdb.saveMealPlan(plan);
        }
        else
        {
            this.plan = GlobalPlan.globalPlan;
        }
        this.setTitle(plan.getPlanName());
        populateListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.plan = MPdb.getPlan(this.plan.getMP_ID());
        populateListView();
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

                    act.putExtra("mpDBid", plan.getMealID(position));
                    //act.putExtra("MP_ID", plan.getPlan)
                    //act.putExtra("StringIngredients", plan.getMealIngredientsString(position));
                    //act.putExtra("StringDirections", plan.getMealDirections(position));
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
                Log.d("checked: ", String.valueOf(item.isChecked()));

            }
            else if(item.isChecked() == true){
                item.setChecked(false);
                editflag = false;
                Log.d("false checked: ", String.valueOf(item.isChecked()));
            }
        }
        //return super.onOptionsItemSelected(item);
        return false;
    }
}
