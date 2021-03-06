package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static doctorj.mealplan.GlobalPlan.globalPlan;


public class MainMenuActivity extends Activity {
    private MealPlanHelper MPdb;
    private List<MealPlan> myPlans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        MPdb = new MealPlanHelper(this);
        myPlans.addAll(MPdb.getPlans());
        createMPList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        myPlans.clear();
        myPlans.addAll(MPdb.getPlans());
        createMPList();
    }

    public void createPlanForm(View view) {
        Intent act = new Intent(this, CreatePlanForm.class);
        GlobalPlan.globalPlan = null;
        startActivity(act);
    }

    public void viewRecipes(View view) {
        Intent act = new Intent(this, viewRecipesActivity.class);
        startActivity(act);
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

    private void createMPList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_plan_list, getPlanNames());
        ListView planTag = (ListView) findViewById(R.id.MealPlanList);
        planTag.setAdapter(adapter);

        planTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent act = new Intent(getApplicationContext(), MainActivity.class);
                globalPlan = myPlans.get(position);
                startActivity(act);
            }
        });
    }

    private List<String> getPlanNames(){
        List<String> names = new ArrayList<>();
        for(int i = 0; i < myPlans.size(); i++)
        {
            names.add(myPlans.get(i).getPlanName());
        }
        return names;
    }
}
