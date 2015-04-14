package doctorj.mealplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSU on 4/12/2015.
 */
public class RecipeHelper extends SQLiteOpenHelper {
    public static final String TABLE_RECIPES = "Recipes";

    public static final String ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DIRECTIONS = "Directions";

    private static final String DATABASE_NAME = "MealPlan";
    private static final int DATABASE_VERSION = 1;

    public RecipeHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_MEALS_TABLE = "CREATE TABLE " + TABLE_MEALS
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT" + ")";*/
        String CREATE_RECIPES_TABLE = "CREATE TABLE Recipes " +
                "(ID INT (0) NOT NULL PRIMARY KEY UNIQUE, Name TEXT (1) NOT NULL, " +
                "Portions number (1) DEFAULT (2), Directions STRING (0));";
        db.execSQL(CREATE_RECIPES_TABLE);
        fillRecipeTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion < oldVersion)
            db.setVersion(oldVersion);
        //super.onDowngrade(db,newVersion,oldVersion);
    }

    public List<String> getAllNames(){
        List<String> array = new ArrayList<>();
        String getNamesQuery = "SELECT " + COLUMN_NAME + " FROM " + TABLE_RECIPES;
        String temp = "no Temp";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(getNamesQuery, null);

        if(c.moveToFirst())
        {
            do
            {
                //temp = new String();
                array.add(c.getString(0));
            }while(c.moveToNext());
        }
        else
            array.add("did not work");
        //array.add(temp);
        return array;
    }

    public void fillRecipeTable(SQLiteDatabase db)
    {
        List<String> FTS = new ArrayList<>(); //FILL_TABLE_STATEMENTS
        String insertRecipeSQL = "INSERT INTO Recipes (id, Name, Portions, Directions) VALUES (";
        String endSQL = ");";
        String insertFields [] = new String [] {
                "1, 'Grilled Cheese Array', 2, 'Make cheese sandwich and grill.'",
                "2, 'Alfredo', 2, 'Make alfredo sauce awesomely.'",
                "3, 'Burgers', 2, 'Mix meat with Daddy Hinkel''s and grill!'",
                "4, 'Test Food', 2, 'Make this food and eat it!'",
                "5, 'More Fake Food', 2, 'Do not make this food.'",
                "6, 'Last Test', 2, 'Why not make this the last test.'",
                "7, 'Tricked You', 2, 'This should be the last last test.'",
                "8, 'Confirmation', 2, 'This is how you really know it is working.'"
        };

        for(int i = 0; i < insertFields.length; i++)
        {
            FTS.add(insertRecipeSQL + insertFields[i] + endSQL);
        }

        for(int i = 0; i < FTS.size(); i++)
            db.execSQL(FTS.get(i));
    }

    public void addRecipe(String name){// TODO just do a execSQL statement for recipe table

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL();
    }

    public void addRecipe(String name, List <Ingredient> ings, int portions, String directions) {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL();
        //TODO db.execSQL();

        db.close();
    }
}
