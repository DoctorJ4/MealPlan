package doctorj.mealplan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;
    private String schedule [];
    private int year;
    private int monthInt;
    private String monthString;
    private int dayBegin;
    private int dayEnd;
    private int calendarOffset;
    private String dayArray [] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public MealPlan(int month, int days, RecipeHelper db)
    {
        /*******************TEST DATA *****************/
        this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        /*******************TEST DATA *****************/

        this.monthInt = month;
        this.monthString = getMonthString(month);
        this.planLength = days;
        this.recipes = new Recipe [days];
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }

    private void buildRandomPlan(RecipeHelper db)
    {
        for(int j = 0; j < this.recipes.length; j++)
        {
            this.recipes[j] = new Recipe();
        }

        Recipe tempRecipe;
        for(int i = 0; i < this.planLength; i++)
        {
            tempRecipe = db.getRandomRecipe();
            this.recipes[i].set_id(tempRecipe.get_id());
            this.recipes[i].setName(tempRecipe.getName());
            this.recipes[i].setIngString(tempRecipe.getIngredientsString());
            this.recipes[i].setDirections(tempRecipe.getDirections());
        }
    }

    private void buildSchedule()
    {
        int dayNum = getDayNum();
        this.schedule = new String [this.planLength];
        for(int k = 0; k < schedule.length; k++)
        {
            this.schedule[k] = dayArray[(dayNum+k)%7] + ", " + this.monthString + " " + (dayBegin + k) + "\n" + recipes[k].getName();
        }
    }

    private int getDayNum()
    {
        Calendar getDate = Calendar.getInstance();
        getDate.set(this.year, (this.monthInt - 1), this.dayBegin);
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


    public String getMealName(int num)
    {
        return recipes[num].getName();
    }
    public String getMealDirections(int num) { return recipes[num].getDirections(); }
    public String getMealIngredientsString(int num)
    {
        return recipes[num].getIngredientsString();
    }
    public int getCalendarOffset(){return this.calendarOffset;}


    public String [] getNames()
    {

        String names [];
        names = new String [this.planLength];
        //loop to fill array with names
        for(int i = 0; i < this.planLength; i++)
        {
            names[i] = this.recipes[i].getName();
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


    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
