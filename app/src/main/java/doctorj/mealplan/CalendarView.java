package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;

public class CalendarView extends Activity {
    private MealPlan plan;
    private CheckBox editModeBox;
    MealPlanHelper MPdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        MPdb = new MealPlanHelper(this);
        //plan = MainActivity.plan;
        plan = MPdb.getPlan(MainActivity.plan.getMP_ID());
        editModeBox = (CheckBox) findViewById(R.id.edit_mode);
        populateGridView();
    }

    private void populateGridView()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.calendar_items, plan.getCalendarSchedule());
        GridView mealTag = (GridView) findViewById(R.id.gridView);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int oldPosition = position;
                position = position - MainActivity.plan.getCalendarOffset();
                if (MainActivity.plan.getCalendarOffset() <= oldPosition) {
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
            }
        });
    }

    public void listViewButton(View view)
    {
        super.onBackPressed();
    }


    public void groceryList(View view) {
        Intent act = new Intent(getApplicationContext(), GroceryListActivity.class);
        startActivity(act);

    }

    @Override
    protected void onResume() {
        plan = MPdb.getPlan(MainActivity.plan.getMP_ID());
        populateGridView();
        super.onResume();
    }
}
