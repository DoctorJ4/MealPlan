package doctorj.mealplan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class CreatePlanForm extends ActionBarActivity {
    private final int maxPlanSize = 100;
    private EditText nameInput;
    static final int START_DIALOG_ID = 0;
    static final int END_DIALOG_ID = 1;
    private int startMonth;
    private int startDay;
    private int startYear;
    private int endMonth;
    private int endDay;
    private int endYear;
    private String startDate;
    private String endDate;
    /*private Spinner startMonth;
    private Spinner startDay;
    private Spinner startYear;*/
    String [] monthNames = new String [] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan_form);
        this.nameInput = (EditText)findViewById(R.id.EditTextView);
        final Calendar c = Calendar.getInstance();
        this.startYear = c.get(Calendar.YEAR);
        this.startMonth = c.get(Calendar.MONTH);
        this.startDay = c.get(Calendar.DAY_OF_MONTH);
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

    public void sendToList(View view) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(startYear, startMonth, startDay);
        c2.set(endYear, endMonth, endDay);
        MyCalendar myC = new MyCalendar();
        int tempNum = myC.getDaysBetweenDates(startMonth, startDay, startYear, endMonth, endDay, endYear);
        if((c1.compareTo(c2) < 0 )&&(tempNum < maxPlanSize)) {
            Intent act = new Intent(this, MainActivity.class);
            act.putExtra("PlanName", this.nameInput.getEditableText().toString());
            act.putExtra("startDay", startDay);
            act.putExtra("startMonth", startMonth + 1);
            act.putExtra("startYear", startYear);
            act.putExtra("endDay", endDay);
            act.putExtra("endMonth", endMonth);
            act.putExtra("endYear", endYear);
            GlobalPlan.globalPlan = null;
            startActivity(act);
            finish();
        }
        else if(tempNum >= maxPlanSize)
            Toast.makeText(this, "Plan length is too long!\nMust be less than " + maxPlanSize, Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, "End date is before start date!", Toast.LENGTH_LONG).show();
        }
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
            return new DatePickerDialog(this, endDateSetListener, startYear, startMonth, startDay);
        return null;
    }

    private DatePickerDialog.OnDateSetListener startDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    startYear = year;
                    startMonth = monthOfYear;
                    startDay = dayOfMonth;
                }
            };

    private DatePickerDialog.OnDateSetListener endDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    endYear = year;
                    endMonth = monthOfYear + 1;
                    endDay = dayOfMonth;
                }
            };
}
