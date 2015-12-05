package doctorj.mealplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jesse on 4/12/2015.
 */
public class DatabaseRecipeHelper extends SQLiteOpenHelper {
    private final String TABLE_RECIPE_BOOKS = DatabaseDefines.TABLE_RECIPE_BOOKS;
    private final String TABLE_RECIPES = DatabaseDefines.TABLE_RECIPES;
    private final String TABLE_INGREDIENTS = DatabaseDefines.TABLE_INGREDIENTS;
    private final String COLUMN_BOOK_ID = DatabaseDefines.COLUMN_BOOK_ID;
    private final String COLUMN_RECIPE_ID = DatabaseDefines.COLUMN_RECIPE_ID;
    private final String COLUMN_NAME = DatabaseDefines.COLUMN_NAME;
    private final String COLUMN_DIRECTIONS = DatabaseDefines.COLUMN_DIRECTIONS;
    private final String COLUMN_PORTIONS = DatabaseDefines.COLUMN_PORTIONS;
    private final String COLUMN_AMOUNT = DatabaseDefines.COLUMN_AMOUNT;
    private final String COLUMN_MRULE = DatabaseDefines.COLUMN_MRULE;
    private final String COLUMN_CATEGORY = DatabaseDefines.COLUMN_CATEGORY;
    private final String COLUMN_FAVORITE = DatabaseDefines.COLUMN_FAVORITE;

    private static final String DATABASE_NAME = DatabaseDefines.DATABASE_NAME;
    private static final int DATABASE_VERSION = DatabaseDefines.DATABASE_VERSION;

    public DatabaseRecipeHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_BOOK_DETAILS_TABLE = "CREATE TABLE " + TABLE_RECIPE_BOOKS + " (" +
        //        COLUMN_BOOK_ID + " INT (0) NOT NULL PRIMARY KEY UNIQUE, " +
        //        COLUMN_NAME + " TEXT (1) NOT NULL;";
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                //COLUMN_BOOK_ID + " INTEGER (0) REFERENCES " + TABLE_RECIPE_BOOKS + " (" + COLUMN_BOOK_ID + "), "+
                COLUMN_NAME + " TEXT (1) NOT NULL, " +
                COLUMN_PORTIONS + " number (1) DEFAULT (2), " +
                COLUMN_DIRECTIONS + " STRING (0), " +
                COLUMN_CATEGORY + " STRING (0), "+
                COLUMN_FAVORITE + " INT DEFAULT (0));";
        String CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_INGREDIENTS + " ("+
                COLUMN_RECIPE_ID + " INTEGER REFERENCES recipes (ID), "+
                COLUMN_NAME +" TEXT (1) NOT NULL, " +
                COLUMN_AMOUNT + " REAL, " +
                COLUMN_MRULE +" TEXT)";
        //db.execSQL(CREATE_BOOK_DETAILS_TABLE);
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
        List<DatabaseRecipeCreateObject> FTS; //FILL_TABLE_STATEMENTS
        MyRecipeList RL = new MyRecipeList();
        ContentValues values;
        long idNum = 0;
        FTS = RL.getMyList();
        for(int i = 0; i < FTS.size(); i++) {
            values = new ContentValues();
            values.put(DatabaseDefines.COLUMN_NAME, FTS.get(i).name);
            values.put(DatabaseDefines.COLUMN_PORTIONS, FTS.get(i).portions);
            values.put(DatabaseDefines.COLUMN_DIRECTIONS, FTS.get(i).directions);
            values.put(DatabaseDefines.COLUMN_CATEGORY, FTS.get(i).category);
            values.put(DatabaseDefines.COLUMN_FAVORITE, FTS.get(i).favorite);
            idNum = db.insert(TABLE_RECIPES, null, values);
            Log.d("DBhelper", "1HERE!!" + idNum);
            for(int j = 0; j < FTS.get(i).ingredients.size(); j++) {
                values = new ContentValues();
                values.put(COLUMN_RECIPE_ID, idNum);
                values.put(COLUMN_NAME, FTS.get(i).ingredients.get(j).getName());
                values.put(COLUMN_AMOUNT, FTS.get(i).ingredients.get(j).getAmount());
                values.put(COLUMN_MRULE, FTS.get(i).ingredients.get(j).getMeasurement());
                long test = db.insert(TABLE_INGREDIENTS, null, values);
                Log.d("DBhelper", "2HERE!! " + idNum + " - " +test );
            }
        }
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


    public Recipe getRandomRecipe(List<String> categories)
    {
        Recipe recipe = new Recipe();
        Random rand = new Random();
        List <Ingredient> ingredients = new ArrayList<>();
        Ingredient tempIng;
        String getNamesQuery = "SELECT * FROM " + TABLE_RECIPES;
        String getCategories = "";
        if(categories.size() > 0) {
            getCategories = " WHERE " + COLUMN_CATEGORY + "='" + categories.get(0) + "'";
            for (int num = 1; num < categories.size(); num++) {
                getCategories = getCategories + " OR " + COLUMN_CATEGORY + "='" + categories.get(num) + "'";
            }
        }
        getNamesQuery = getNamesQuery + getCategories + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(getNamesQuery, null);

        if(c.moveToPosition(rand.nextInt(c.getCount())))
        {
            String getIngredientsQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_RECIPE_ID + "=" + c.getInt(0);
            Cursor i = db.rawQuery(getIngredientsQuery, null);
            //ingredients = new ArrayList<>();
            if(i.moveToFirst())
            {
                do {
                    tempIng = new Ingredient(i.getString(1), i.getDouble(2), i.getString(3));
                    ingredients.add(tempIng);
                }while(i.moveToNext());
            }
            i.close();
            recipe = new Recipe(c.getInt(0), c.getString(1), ingredients, c.getString(3));

        }
        c.close();
        return recipe;
    }

    //grabs a single recipe
    public Recipe getRecipe(int id)
    {
        Recipe recipe = new Recipe();
        String getNamesQuery = "SELECT * FROM " + TABLE_RECIPES;// + " WHERE Name=\'" +item+"\'";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(getNamesQuery, null);
            List <Ingredient> ingredients = new ArrayList<>();
            Ingredient tempIng;

            if(c.moveToFirst()) {
                    if (c.getInt(0) == id) {
                        String getIngredientsQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE ID=" + c.getInt(0);
                        Cursor i = db.rawQuery(getIngredientsQuery, null);
                        if (i.moveToFirst()) {
                            do {
                                tempIng = new Ingredient();
                                tempIng.setName(i.getString(1));
                                tempIng.setAmount(i.getDouble(2));
                                tempIng.setMeasurement(i.getString(3));
                                ingredients.add(tempIng);
                            } while (i.moveToNext());
                        }
                        i.close();
                        recipe = new Recipe(c.getInt(0), c.getString(1), ingredients, c.getString(3));
                }
            }
            c.close();
        }

        catch(Exception ex)
        {
            Log.d("RecipeHelper: Broke", String.valueOf(ex));
        }
        return recipe;
    }

    public List <Ingredient> getrecipeIngredients (int id)
    {
        List<Ingredient> ings = new ArrayList<>();
        Ingredient tempIng;
        SQLiteDatabase db = this.getReadableDatabase();
        String getIngsQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_RECIPE_ID + "=" + id;
        Cursor i = db.rawQuery(getIngsQuery, null);
        if(i.moveToFirst())
        {
            do {
                tempIng = new Ingredient();
                tempIng.setName(i.getString(1));
                tempIng.setAmount(i.getDouble(2));
                tempIng.setMeasurement(i.getString(3));
                ings.add(tempIng);
            }while(i.moveToNext());
        }
        i.close();
        return ings;
    }

    public List <Recipe> getRecipesCategory (String category)
    {
        List<Recipe> recs;
        String getRecipesQuery;
        if(category.equals(COLUMN_FAVORITE))
            getRecipesQuery = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_FAVORITE + "=1";
        else
            getRecipesQuery = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_CATEGORY + "='" + category + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        //String getRecipesQuery = "SELECT * FROM " + TABLE_MPRECIPES + " WHERE " + MP_ID + "=" + i.getInt(0);
        Cursor c = db.rawQuery(getRecipesQuery, null);
        recs = new ArrayList<>();
        Ingredient tempIng;
        List <Ingredient> ingredients;
        Recipe tempRec;

        if (c.moveToFirst()) {
            do {
                String getIngredientsQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_RECIPE_ID + "=" + c.getInt(0);
                Cursor i = db.rawQuery(getIngredientsQuery, null);
                ingredients = new ArrayList<>();
                if(i.moveToFirst())
                {
                    do {
                        tempIng = new Ingredient();
                        tempIng.setName(i.getString(1));
                        tempIng.setAmount(i.getDouble(2));
                        tempIng.setMeasurement(i.getString(3));
                        ingredients.add(tempIng);
                    }while(i.moveToNext());
                    i.close();
                }
                tempRec = new Recipe(c.getInt(0), c.getString(1), ingredients, c.getString(3));
                recs.add(tempRec);
            } while (c.moveToNext());
            c.close();
        }
        return recs;
    }

}
