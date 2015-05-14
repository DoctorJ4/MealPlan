package doctorj.mealplan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreatePlanForm extends ActionBarActivity {
    private final int maxPlanSize = 100;
    static final int START_DIALOG_ID = 0;
    static final int END_DIALOG_ID = 1;
    private int startMonth;
    private int startDay;
    private int startYear;
    private int endMonth;
    private int endDay;
    private int endYear;
    private MyCalendar myC;
    private String startDate;
    private String endDate;
    private MealPlanHelper MPdb;
    private int numPlans;
    private List<String> categories;
    //GUI COMPONENTS~~~~~~~~~~~~~~~~~~~~~~~~~~
    private TextView startText;
    private TextView endText;
    private EditText nameInput;
    private CheckBox Favorites;
    private CheckBox American;
    private CheckBox Italian;
    private CheckBox Mexican;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan_form);
        this.nameInput = (EditText)findViewById(R.id.EditTextView);
        this.myC = new MyCalendar();
        final Calendar c = Calendar.getInstance();
        this.MPdb = new MealPlanHelper(this);
        this.numPlans = MPdb.getNumPlans();
        this.categories = new ArrayList<>();
        this.startYear = c.get(Calendar.YEAR);
        this.startMonth = c.get(Calendar.MONTH);
        this.startDay = c.get(Calendar.DAY_OF_MONTH);
        this.endYear = this.startYear;
        this.endMonth = this.startMonth;
        this.endDay = this.startDay;
        this.startText = (TextView)findViewById(R.id.startDateText);
        this.endText = (TextView)findViewById(R.id.endDateText);
        this.Favorites = (CheckBox)findViewById(R.id.Favorites);
        this.American = (CheckBox)findViewById(R.id.American);
        this.Italian = (CheckBox)findViewById(R.id.Italian);
        this.Mexican = (CheckBox)findViewById(R.id.Mexican);
        setDateText();
    }

    public void sendToList(View view) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(startYear, startMonth, startDay);
        c2.set(endYear, endMonth, endDay);
        int tempNum = this.myC.getDaysBetweenDates(startMonth, startDay, startYear, endMonth, endDay, endYear);
        String name = nameInput.getEditableText().toString();
        List<String> categories = new ArrayList<>(getCategories());

        if(name.equals(""))
            name = "Plan " + numPlans;

        if((c1.compareTo(c2) < 0 )&&(tempNum < maxPlanSize)) {

            RecipeHelper db = new RecipeHelper(this);
            MealPlan newPlan = new MealPlan(numPlans, name, startMonth + 1, startDay, startYear, endMonth + 1, endDay, endYear, db, categories);
            MPdb.saveMealPlan(newPlan);
            finish();
        }
        else if(tempNum >= maxPlanSize)
            Toast.makeText(this, "Plan length is too long!\nMust be less than " + maxPlanSize, Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, "End date is on or before start date!", Toast.LENGTH_LONG).show();
        }
    }

    private List<String> getCategories(){
        if(Favorites.isChecked() == true)
            categories.add("Favorites");
        if(American.isChecked() == true)
            categories.add("American");
        if(Italian.isChecked() == true)
            categories.add("Italian");
        if(Mexican.isChecked() == true)
            categories.add("Mexican");
        return categories;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_plan_form, menu);
        return true;
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

    private void setDateText()
    {
        this.startDate = this.myC.getFullDateString(startMonth, startDay, startYear);
        this.endDate = this.myC.getFullDateString(endMonth, endDay, endYear);;
        this.startText.setText(this.startDate);
        this.endText.setText(this.endDate);
    }

    public void setStartDate(View view){
        showDialog(START_DIALOG_ID);
    }

    public void setEndDate(View view){
        showDialog(END_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == START_DIALOG_ID)
            return new DatePickerDialog(this, startDateSetListener, startYear, startMonth, startDay);
        else if(id == END_DIALOG_ID)
            return new DatePickerDialog(this, endDateSetListener, endYear, endMonth, endDay);
        return null;
    }

    private DatePickerDialog.OnDateSetListener startDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    startYear = year;
                    startMonth = monthOfYear;
                    startDay = dayOfMonth;
                    setDateText();
                }
            };

    private DatePickerDialog.OnDateSetListener endDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    endYear = year;
                    endMonth = monthOfYear;
                    endDay = dayOfMonth;
                    setDateText();
                }
            };
}
