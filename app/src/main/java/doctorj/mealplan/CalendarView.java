package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;

public class CalendarView extends Activity {
    MealPlan plan;
    CheckBox editModeBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        plan = GlobalPlan.globalPlan;
        editModeBox = (CheckBox) findViewById(R.id.edit_mode);
        populateGridView();
    }

    private void populateGridView()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.calendar_items, MainActivity.plan.getCalendarSchedule());
        GridView mealTag = (GridView) findViewById(R.id.gridView);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int oldPosition = position;
                position = position - MainActivity.plan.getCalendarOffset();
                if(MainActivity.plan.getCalendarOffset() <= oldPosition)
                {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void groceryList(View view) {
        Intent act = new Intent(getApplicationContext(), GroceryListActivity.class);
        startActivity(act);
    }
}
