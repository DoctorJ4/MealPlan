package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import static doctorj.mealplan.GlobalPlan.globalPlan;


public class MainActivity extends Activity {
    CheckBox editModeBox;
    public static MealPlan plan;
    DatabaseRecipeHelper db;
    MealPlanHelper MPdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseRecipeHelper(this);
        MPdb = new MealPlanHelper(this);
        plan = globalPlan;
        this.setTitle(plan.getPlanName());
        editModeBox = (CheckBox) findViewById(R.id.edit_mode);
        populateListView();
    }

    @Override
    protected void onResume() {
        plan = MPdb.getPlan(plan.getMP_ID());
        populateListView();
        super.onResume();
    }


    private void populateListView(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.meal_items, plan.getSchedule());
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (!editModeBox.isChecked()) {
                    Intent act = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    act.putExtra("StringName", plan.getMealName(position));
                    act.putExtra("StringIngredients", plan.getMealIngredientsString(position));
                    act.putExtra("StringDirections", plan.getMealDirections(position));
                    startActivity(act);
                } else {
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


    public void calendarView(View view){
        Intent act = new Intent(getApplicationContext(), CalendarView.class);
        startActivity(act);
    }

    public void groceryList(View view){
        Intent act = new Intent(getApplicationContext(), GroceryListActivity.class);
        startActivity(act);
    }
}
