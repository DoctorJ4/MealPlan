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
    int planLength;
    public static MealPlan plan;
    RecipeHelper db;
    public boolean editflag = false;
    ArrayAdapter<String> spinnerAdapter;

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
                if(editflag == true){
                    //creates a spinner
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);

                    //creates example text
                    List<String> items; //= new String[] { "Chai Latte", "Green Tea", "Black Tea" };
                    //gets all items that can be used in the DB
                    items = db.getAllNames();
                    //sets up the spinner adapter

                    spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, items);

                    spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                    //creates on click events
                    dynamicSpinner.setAdapter(spinnerAdapter);
                        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentT, View viewT, int positionT, long idT) {
                                Log.d("postion: ", String.valueOf(position));
                                Log.d("Item: ", String.valueOf(positionT));
                                plan.changemeal(db, position, positionT);
                                //refreshListView();

                                return;
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                return;
                            }
                        });
                    }

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
        if(id == R.id.edit_items)
        {
            if(item.isChecked() == false) {
                item.setChecked(true);
                editflag = true;
                Log.d("checked: ", String.valueOf(item.isChecked()));

            }
            else if(item.isChecked() == true){
                item.setChecked(false);
                editflag = false;
                Log.d("checked: ", String.valueOf(item.isChecked()));


                //spinnerAdapter = null;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
