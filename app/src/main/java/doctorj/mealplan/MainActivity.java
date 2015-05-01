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
            //TODO -> get extras from form activity for mealplan fields
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
        }

        return super.onOptionsItemSelected(item);
    }
}
