package doctorj.mealplan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;

    String DB_PATH = "raw/";

    //final MealPlan mealplan=this;
    //private final Context context = this.context;
    //private static SQLiteDatabase mDataBase;
    private static String DB_NAME ="mealplan.db";

    public MealPlan(int days, SQLiteDatabase dbInput)
    {
        this.recipes = new Recipe [days];
        this.planLength = days;
        //TODO write sql to fill array of meal_objects with all Recipe fields
        String[] whereArgs = new String[] { "Grilled Cheese" };
        String queryString =
                "SELECT meal_name, (SELECT max(meal_name) FROM meals) AS max FROM meals " +
                        "WHERE * ORDER BY meal_name";
        Cursor c = dbInput.rawQuery(queryString, whereArgs);
        //Cursor c = dbInput.rawQuery("SELECT meal_names FROM meals", temp); // TODO fix query
        c.moveToFirst();
        String stringArray [] = new String [c.getCount()];
        int i = 0;
        for(i=0;i < c.getCount();i++)
        {
            this.recipes[i].setName(c.getString(0));
            c.moveToNext();
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
            names[i] = this.recipes[i].getName();
        return names;
    }
    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
