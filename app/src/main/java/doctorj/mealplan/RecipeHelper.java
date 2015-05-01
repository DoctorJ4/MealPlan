package doctorj.mealplan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jesse on 4/12/2015.
 */
public class RecipeHelper extends SQLiteOpenHelper {
    public static final String TABLE_RECIPES = "Recipes";
    public static final String TABLE_INGREDIENTS = "Ingredients";

    public static final String ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DIRECTIONS = "Directions";

    private static final String DATABASE_NAME = "Recipes";
    private static final int DATABASE_VERSION = 1;

    public RecipeHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE Recipes " +
                "(ID INT (0) NOT NULL PRIMARY KEY UNIQUE, Name TEXT (1) NOT NULL, " +
                "Portions number (1) DEFAULT (2), Directions STRING (0));";
        String CREATE_INGREDIENTS_TABLE = "CREATE TABLE Ingredients " +
                "(ID INTEGER (0) REFERENCES recipes (ID), Name TEXT (1) NOT NULL, Amount REAL, MRule TEXT)";
        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_INGREDIENTS_TABLE);
        fillTables(db);
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

    public void fillTables(SQLiteDatabase db)
    {
        List<String> FTS; //FILL_TABLE_STATEMENTS
        MyRecipeList RL = new MyRecipeList();
        FTS = RL.getMyList();
        for(int i = 0; i < FTS.size(); i++)
            db.execSQL(FTS.get(i));
        FTS = RL.getMyIngredientsList();
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
                array.add(c.getString(0));
            }while(c.moveToNext());
        }
        else
            array.add("did not work");

        return array;
    }


    public Recipe getRandomRecipe()
    {
        Recipe recipe = new Recipe();
        Random rand = new Random();
        String getNamesQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(getNamesQuery, null);
        List <Ingredient> ingredients = new ArrayList<>();
        Ingredient tempIng;

        if(c.moveToPosition(rand.nextInt(c.getCount())))
        {
            String getIngredientsQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE ID=" + c.getInt(0);
            Cursor i = db.rawQuery(getIngredientsQuery, null);
            //ingredients = new ArrayList<>();
            if(i.moveToFirst())
            {
                do {
                    tempIng = new Ingredient(i.getString(1), i.getDouble(2), i.getString(3));
                    ingredients.add(tempIng);
                }while(i.moveToNext());
            }

            recipe = new Recipe(c.getInt(0), c.getString(1), ingredients, c.getString(3));

        }
        return recipe;
    }

}
