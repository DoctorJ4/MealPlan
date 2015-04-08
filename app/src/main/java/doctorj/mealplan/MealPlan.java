package doctorj.mealplan;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;

    String DB_PATH = "raw/";

    final MealPlan mealplan=this;
    private SQLiteDatabase mDataBase;
    private static String DB_NAME ="mealplan.db";

    public MealPlan(int days)
    {
        this.recipes = new Recipe [days];
        this.planLength = days;
        //TODO write sql to fill array meal_objects with all Recipe fields

        //copied code
        DBMain db;

        db = new DBMain(this);

        try {

            db.createDB();
        } catch (IOException ioe) {

            throw new Error("Database not created....");
        }

        try {

            db.openDB();
        }catch(SQLException sqle){

            throw sqle;
        }

        SQLiteDatabase db1;
        db1=openOrCreateDatabase(DB_PATH+DB_NAME,null);
        Cursor c= db1.rawQuery("SELECT * FROM bank",null);

        c.moveToFirst();

        String temp="";

        while(! c.isAfterLast())
        {
            String s2=c.getString(0);
            String s3=c.getString(1);
            String s4=c.getString(2);
            temp=temp+"\n Id:"+s2+"\tType:"+s3+"\tBal:"+s4;

            c.moveToNext();
        }
        //copied code

        sendIngredientsToGL();
    }

    public String getMealName(int num)
    {
        return recipes[num].getName();
    }

    public String [] getNames()
    {

        String list [];
        list = new String [this.planLength];
        //loop to fill array with names
        for(int i = 0; i < this.planLength; i++)
            list[i] = this.recipes[i].getName();
        return list;
    }

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
