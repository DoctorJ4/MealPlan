package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class CalendarView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        populateGridView();
    }

    private void populateGridView()
    {
        /*GridLayout mealTag = (GridLayout) findViewById(R.id.gridView);
        TextView tv = (TextView) findViewById(R.id.meal_items);
        tv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        tv.setText("TextView");
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        tv.setLayoutParams(params);
        mealTag.addView(tv);*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, MainActivity.plan.getSchedule());
        GridView mealTag = (GridView) findViewById(R.id.gridView);
        mealTag.setAdapter(adapter);

        mealTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent act = new Intent(getApplicationContext(), RecipeViewActivity.class);

                act.putExtra("StringName", MainActivity.plan.getMealName(position));
                act.putExtra("StringIngredients",MainActivity.plan.getMealIngredientsString(position));
                act.putExtra("StringDirections", MainActivity.plan.getMealDirections(position));
                startActivity(act);
            }
        });
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
}
