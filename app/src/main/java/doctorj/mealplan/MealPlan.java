package doctorj.mealplan;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.util.Log;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan{
    private MyCalendar CAL = new MyCalendar();
    private int MP_ID;
    private String planName;
    private List<Recipe> recipes;
    private GroceryList gl;
    private int planLength;
    private String schedule [];
    private int year;
    //private int monthInt;
    private String monthString;
    private int dayBegin;
    private int dayEnd;
    private int monthBegin;
    private int monthEnd;
    private int yearStart;
    private int yearEnd;
    private String startDate;
    private String endDate;
    private int calendarOffset;
    private String dayArray [] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    // id - name - startDate - endDate - length
    // obtain from database
    public MealPlan(int id, String name, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear, int length, List<Recipe> recs)
    {

        /*******************TEST DATA *****************/
        //this.year = startYear;
        this.dayBegin = startDay;
        this.dayEnd = endDay;
        this.monthBegin = startMonth;
        this.monthEnd = endMonth;
        this.yearStart = startYear;
        this.yearEnd = endYear;
        this.startDate = "May 1, 1950";
        this.endDate = "May 30, 1950";
        /*******************TEST DATA *****************/
        this.MP_ID = id;
        this.planName = name;
        this.monthString = getMonthString(this.monthBegin);
        this.planLength = length;
        this.recipes = recs;
        //buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }

    //CREATE PLAN FORM
    public MealPlan(int id, String name, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear,  RecipeHelper db)
    {
        /*******************TEST DATA *****************/
        this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        this.monthBegin = 4;
        this.dayBegin = startDay;
        this.dayEnd = endDay;
        this.monthBegin = startMonth;
        this.monthEnd = endMonth;
        this.yearStart = startYear;
        this.yearEnd = endYear;
        //this.startDate = "May 1, 1950";
        //this.endDate = "May 30, 1950";
        /*******************TEST DATA *****************/
        this.MP_ID = id;
        this.planName = name;
        this.monthString = getMonthString(this.monthBegin);
        this.planLength = getDaysBetweenDates(startMonth, startDay, startYear, endMonth, endDay, endYear);
        this.recipes = new ArrayList<>();
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }

    //GENERATE RANDOM PLAN
    public MealPlan(int id, String name, String startD, String endD, int length,  RecipeHelper db)
    {
        /*******************TEST DATA *****************/
        this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        this.monthBegin = 4;
        //this.startDate = "May 1, 1950";
        //this.endDate = "May 30, 1950";
        /*******************TEST DATA *****************/
        this.MP_ID = id;
        this.planName = name;
        this.monthString = getMonthString(this.monthBegin);
        this.planLength = length;
        this.recipes = new ArrayList<>();
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }
    // Generate new mealplan
    public MealPlan(String name, int month, int days, RecipeHelper db)
    {
        /*******************TEST DATA *****************/
        this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        this.monthBegin = month;
        this.startDate = "NEVER";
        this.endDate = "ALWAYS";
        /*******************TEST DATA *****************/
        this.planName = name;
        this.monthBegin = month;
        this.monthString = getMonthString(month);
        this.planLength = days;
        this.recipes = new ArrayList<>();
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }

    private void buildRandomPlan(RecipeHelper db)
    {
        /*for(int j = 0; j < this.recipes.size(); j++)
        {
            this.recipes[j] = new Recipe();
        }*/

        Recipe tempRecipe;
        for(int i = 0; i < this.planLength; i++)
        {
            tempRecipe = db.getRandomRecipe();
            this.recipes.add(tempRecipe);
            /*this.recipes.get(i).set_id(tempRecipe.get_id());
            this.recipes.get(i).setName(tempRecipe.getName());
            this.recipes.get(i).setIngString(tempRecipe.getIngredientsString());
            this.recipes.get(i).setDirections(tempRecipe.getDirections());*/
        }
    }

    private void buildSchedule()
    {
        int dayNum = getDayNum();
        this.schedule = new String [this.planLength];
        int num = 0;
        int[] daysInMonths = new int []{31,28,31,30,31,30,31,31,30,31,30,31};
        if((this.yearStart % 4) == 0)
            daysInMonths[1] = 29;
        int startMonth = this.monthBegin;
        int startDay = this.dayBegin;
        int startYear = this.yearStart;
        int endMonth = this.monthEnd;
        int endDay = this.dayEnd;
        int endYear = this.yearEnd;
        int monthCount = startMonth;
        String monthName = this.monthString;
        endMonth = endMonth + (12 * (endYear - startYear));

        while(((monthCount < endMonth)||(startDay < endDay))&&(startYear <= endYear))
        {
            this.schedule[num] = dayArray[(dayNum+num)%7] + ", " + monthName + " " + (startDay) + "\n" + recipes.get(num).getName();
            num++;
            startDay++;
            if((daysInMonths[startMonth - 1] - (startDay-1)) <= 0)
            {
                startMonth = (startMonth % 12) + 1;
                monthCount++;
                monthName = getMonthString(startMonth);
                startDay = 1;
                if(startMonth == 1) {
                    startYear++;
                    if((startYear % 4) == 0)
                        daysInMonths[1] = 29;
                    else
                        daysInMonths[1] = 28;
                }
            }
        }
    }

    private int getDayNum()
    {
        Calendar getDate = Calendar.getInstance();
        getDate.set(this.yearStart, (this.monthBegin - 1), this.dayBegin);
        int dayNum = 0;

        switch(getDate.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY: dayNum = 0;
                break;
            case Calendar.MONDAY: dayNum = 1;
                break;
            case Calendar.TUESDAY: dayNum = 2;
                break;
            case Calendar.WEDNESDAY: dayNum = 3;
                break;
            case Calendar.THURSDAY: dayNum = 4;
                break;
            case Calendar.FRIDAY: dayNum = 5;
                break;
            case Calendar.SATURDAY: dayNum = 6;
                break;
        }
        return dayNum;
    }

    private String getMonthString(int mI){
        String monthString;
        switch (mI) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public int getMP_ID() { return this.MP_ID; }
    public String getPlanName(){ return this.planName; }
    public int getLength(){ return this.planLength; }
    public int getStartDay(){ return this.dayBegin; }
    public int getEndDay(){ return this.dayEnd; }
    public int getStartMonth(){ return this.monthBegin; }
    public int getEndMonth(){ return this.monthEnd; }
    public int getStartYear(){ return this.yearStart; }
    public int getEndYear(){ return this.yearEnd; }
    public String getStartDate(){ return this.startDate; }
    public String getEndDate(){ return this.endDate; }
    public int getMealPortions(int num) { return recipes.get(num).getPortions(); }
    public String getMealName(int num){  return recipes.get(num).getName(); }
    public String getMealDirections(int num) { return recipes.get(num).getDirections(); }
    public List<Ingredient> getMealIngredients(int num){ return recipes.get(num).getIngredients(); }
    public String getMealIngredientsString(int num) { return recipes.get(num).getIngredientsString();}
    public int getCalendarOffset(){return this.calendarOffset;}


    public String [] getNames()
    {

        String names [];
        names = new String [this.planLength];
        //loop to fill array with names
        for(int i = 0; i < this.planLength; i++)
        {
            names[i] = this.recipes.get(i).getName();
        }
        return names;
    }

    public String [] getSchedule()
    {
        return this.schedule;
    }
    public String [] getCalendarSchedule()
    {
        List<String> calendarSched = new ArrayList<>();
        String [] CS;
        int dayNum = getDayNum();
        this.calendarOffset = dayNum;

        int i;
        for(i = 0; i < dayNum; i++)
            calendarSched.add(this.dayArray[i]);

        int commaIndex = 0;
        for(int k = 0; k < this.schedule.length; k++) {
            commaIndex = this.schedule[k].indexOf(',');
            this.schedule[k] = this.schedule[k].substring(0, commaIndex) + "\n" + this.schedule[k].substring(commaIndex + 2, this.schedule[k].length());
            calendarSched.add(this.schedule[k]);
        }

        CS = new String[calendarSched.size()];
        for(int j = 0; j < calendarSched.size(); j++)
            CS[j] = calendarSched.get(j);

        return CS;
    }

    public int getDaysBetweenDates(int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear)
    {
        int num = 0;
        int[] daysInMonths = new int []{31,28,31,30,31,30,31,31,30,31,30,31};
        if((startYear % 4) == 0)
            daysInMonths[1] = 29;
        endMonth = endMonth + (12 * (endYear - startYear));
        int monthCount = startMonth;
        while(((monthCount < endMonth)||(startDay < endDay))&&(startYear <= endYear))
        {
            num++;
            startDay++;
            if((daysInMonths[startMonth - 1] - (startDay-1)) <= 0)
            {
                startMonth = (startMonth % 12) + 1;
                monthCount++;
                startDay = 1;
                if(startMonth == 1) {
                    startYear++;
                    if((startYear % 4) == 0)
                        daysInMonths[1] = 29;
                    else
                        daysInMonths[1] = 28;
                }
            }

        }
        return num;
    }

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }

    //made by Reed
    public void changemeal(RecipeHelper db,int loc_id,int item_id)
    {
        Log.d("Mealplan: ", String.valueOf(item_id));
        Recipe tempRecipe;
        tempRecipe = db.getRecipe(item_id);
        Log.d("MP:finished temp: ", String.valueOf(item_id));
        this.recipes[loc_id].setName(tempRecipe.getName());
        this.recipes[loc_id].setIngString(tempRecipe.getIngredientsString());
        this.recipes[loc_id].setDirections(tempRecipe.getDirections());
        //Log.d("cursor0: ", this.recipes[loc_id].getName());
        //Log.d("cursor1: ", this.recipes[loc_id].getIngredientsString());
        //Log.d("cursor3: ", this.recipes[loc_id].getDirections());
    }
}
