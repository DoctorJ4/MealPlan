package doctorj.mealplan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;
    private String schedule [];

    String DB_PATH = "raw/";

    //final MealPlan mealplan=this;
    //private final Context context = this.context;
    //private static SQLiteDatabase mDataBase;
    private static String DB_NAME ="mealplan.db";

    //public MealPlan(int days, SQLiteDatabase dbInput)
    public MealPlan(String month, int days, String []mealNames)
    {
        this.recipes = new Recipe [days];
        for(int j = 0; j < this.recipes.length; j++)
        {
            this.recipes[j] = new Recipe();
        }
        this.schedule = new String [days];
        for(int k = 0; k < schedule.length; k++)
        {
            this.schedule[k] = month + " " + (k+1) + "\n" + mealNames[k];
        }
        this.planLength = days;
        //TODO write sql to fill array of meal_objects with all Recipe fields
        /*String path = DB_PATH + DB_NAME;
        //Log.i("myPath ......", path);
        //dbInput = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //SQLiteDatabase.op
        String[] whereArgs = new String[] { "Grilled Cheese" };
        String queryString =
                "SELECT meal_name, (SELECT max(meal_name) FROM meals) AS max FROM meals " +
                        "WHERE * ORDER BY meal_name";
        Cursor c = dbInput.rawQuery(queryString, whereArgs);
        //Cursor c = dbInput.query("SELECT meal_names FROM meals", temp); // TODO fix query
        c.moveToFirst();
        String stringArray [] = new String [c.getCount()];
        int i = 0;
        for(i=0;i < c.getCount();i++)
        {
            this.recipes[i].setName(c.getString(0));
            c.moveToNext();
        }*/

        for(int i = 0; i < this.planLength; i++)
        {
            this.recipes[i].setName(mealNames[i]);
        }
        sendIngredientsToGL();
    }

    public String getMealName(int num)
    {
        return recipes[num].getName();
    }

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

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
