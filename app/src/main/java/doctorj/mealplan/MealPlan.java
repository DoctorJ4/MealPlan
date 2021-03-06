package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan{
    private MyCalendar CAL = new MyCalendar();
    private int MP_ID;
    private String planName;
    private List<Recipe> recipes;
    private int planLength;
    private String schedule [];
    //private int year;
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

    public MealPlan(){}

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
        this.monthString = CAL.getMonthString(this.monthBegin);
        this.planLength = length;
        this.recipes = recs;
        //buildRandomPlan(db);
        buildSchedule();
    }

    //CREATE PLAN FORM
    public MealPlan(int id, String name, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear,  RecipeHelper db, List<String> categories)
    {
        /*******************TEST DATA *****************/
        //this.year = 2015;
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
        this.monthString = CAL.getMonthString(this.monthBegin);
        this.planLength = CAL.getDaysBetweenDates(startMonth, startDay, startYear, endMonth, endDay, endYear);
        this.recipes = new ArrayList<>();
        //RecipeHelper db = new RecipeHelper(this);
        buildRandomPlan(db, categories);
        buildSchedule();
    }

    /*GENERATE RANDOM PLAN
    public MealPlan(int id, String name, String startD, String endD, int length,  RecipeHelper db)
    {
        //*******************TEST DATA *****************
        //this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        this.monthBegin = 4;
        //this.startDate = "May 1, 1950";
        //this.endDate = "May 30, 1950";
        //*******************TEST DATA *****************
        this.MP_ID = id;
        this.planName = name;
        this.monthString = CAL.getMonthString(this.monthBegin);
        this.planLength = length;
        this.recipes = new ArrayList<>();
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }*/
    /* Generate new mealplan
    public MealPlan(String name, int month, int days, RecipeHelper db)
    {
        //*******************TEST DATA *****************
        //this.year = 2015;
        this.dayBegin = 1;
        this.dayEnd = 30;
        this.monthBegin = month;
        this.startDate = "NEVER";
        this.endDate = "ALWAYS";
        //*******************TEST DATA *****************
        this.planName = name;
        this.monthBegin = month;
        this.monthString = CAL.getMonthString(month);
        this.planLength = days;
        this.recipes = new ArrayList<>();
        buildRandomPlan(db);
        buildSchedule();
        sendIngredientsToGL();
    }*/

    private void buildRandomPlan(RecipeHelper db, List<String> categories)
    {
        Recipe tempRecipe;
        for(int i = 0; i < this.planLength; i++)
        {
            tempRecipe = db.getRandomRecipe(categories);
            this.recipes.add(tempRecipe);
        }
    }

    private void buildSchedule() {
        int dayNum = CAL.getDayOfWeek(this.yearStart, (this.monthBegin), this.dayBegin);
        this.schedule = new String[this.planLength];
        int num = 0;
        int[] daysInMonths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((this.yearEnd % 4) == 0)
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
        boolean yearCheck = false;

        while (((monthCount < endMonth) || (startDay < endDay)) && (startYear <= endYear)) {
            this.schedule[num] = dayArray[(dayNum + num) % 7] + ", " + monthName + " " + (startDay) + "\n" + recipes.get(num).getName();
            num++;
            startDay++;
            if ((daysInMonths[monthCount % 12] - (startDay - 1)) <= 0) {
                //startMonth = (startMonth % 12);
                monthCount++;
                monthName = CAL.getMonthString(monthCount % 12);
                startDay = 1;
                if ((!yearCheck)&&((monthCount % 12) == 0)) {
                    yearCheck = true;
                    startYear++;
                }
            }
        }
        this.schedule[num] = dayArray[(dayNum + num) % 7] + ", " + monthName + " " + (startDay) + "\n" + recipes.get(num).getName();
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
    public int getMealID(int num){return recipes.get(num).get_id();}
    public int getMealIndex(int num) {return recipes.get(num).getIndex();}
    public String getMealCategory(int num){return recipes.get(num).getCategory();}
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
        int dayNum = CAL.getDayOfWeek(this.yearStart, (this.monthBegin - 1), this.dayBegin);
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




}
