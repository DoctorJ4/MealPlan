package doctorj.mealplan;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jesse on 4/12/2015.
 */
public class MealPlanHelper extends SQLiteOpenHelper {
    private List<MealPlan> DEBUG_PLANS = new ArrayList<>();
    public static final String TABLE_MEALPLANS = "MealPlans";
    public static final String TABLE_MPRECIPES = "MPRecipes";
    public static final String TABLE_MPINGREDIENTS = "MPIngredients";

    public static final String MP_ID = "MP_ID";
    public static final String COLUMN_RECIPEID = "Recipe_ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_INDEX = "Ing_ID";
    public static final String COLUMN_STARTMONTH = "StartDate";
    public static final String COLUMN_STARTDAY = "StartMonth";
    public static final String COLUMN_STARTYEAR = "StartYear";
    public static final String COLUMN_ENDMONTH = "EndMonth";
    public static final String COLUMN_ENDDAY = "EndDay";
    public static final String COLUMN_ENDYEAR = "EndYear";
    public static final String COLUMN_LENGTH = "Length";
    public static final String COLUMN_DIRECTIONS = "Directions";
    public static final String COLUMN_AMOUNT = "Amount";
    public static final String COLUMN_PORTION = "Portions";
    public static final String COLUMN_MRULE = "MRule";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_FAVORITE = "Favorite";

    private static final String DATABASE_NAME = "MealPlansDatabase.db";
    private static final int DATABASE_VERSION = 1;
    //private SQLiteDatabase db;

    public MealPlanHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //----------------------SAVED MEAL PLANS------------
        // id - name - startDate - endDate - length
        String CREATE_MEAL_PLANS_TABLE = "CREATE TABLE " + TABLE_MEALPLANS + " (" +
                MP_ID + "     INT    PRIMARY KEY," +
                COLUMN_NAME + "      STRING," +
                COLUMN_STARTMONTH + " INT," +
                COLUMN_STARTDAY + " INT," +
                COLUMN_STARTYEAR + " INT," +
                COLUMN_ENDMONTH + " INT," +
                COLUMN_ENDDAY + " INT," +
                COLUMN_ENDYEAR + " INT," +
                COLUMN_LENGTH + "    INT" +
                ");";

            String CREATE_MP_RECIPES_TABLE = "CREATE TABLE " + TABLE_MPRECIPES + " (" +
                    MP_ID + "      INT    REFERENCES "+ TABLE_MEALPLANS +" (" + MP_ID + ")," +
                    COLUMN_INDEX + "    INT    PRIMARY KEY," +
                    COLUMN_RECIPEID + " INT NOT NULL, " +
                    COLUMN_NAME + "       STRING," +
                    COLUMN_PORTION + "   INT," +
                    COLUMN_DIRECTIONS + " STRING (0), " +
                    COLUMN_CATEGORY + " STRING (0), "+
                    COLUMN_FAVORITE + " INT DEFAULT (0));";

            String CREATE_MP_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_MPINGREDIENTS + " (" +
                    MP_ID + "      INT    REFERENCES "+ TABLE_MEALPLANS +" (" + MP_ID + ")," +
                    COLUMN_INDEX + " INTEGER (0) REFERENCES "+ TABLE_MPRECIPES +" (" + COLUMN_INDEX + ")," +
                    COLUMN_NAME + " TEXT (1) NOT NULL," +
                    COLUMN_AMOUNT + " REAL," +
                    COLUMN_MRULE + " TEXT" +
                    ");";
        //----------------------^^^^^^^^^^^^^^^^^------------

        db.execSQL(CREATE_MEAL_PLANS_TABLE);
        db.execSQL(CREATE_MP_RECIPES_TABLE);
        db.execSQL(CREATE_MP_INGREDIENTS_TABLE);

        //DEBUG_fillTables(db);//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TEST~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALPLANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MPINGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MPRECIPES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion < oldVersion)
            db.setVersion(oldVersion);
        //super.onDowngrade(db,newVersion,oldVersion);
    }



    public void addPlan(MealPlan tempPlan) {

        ContentValues values = new ContentValues();
        values.put(MP_ID, 0);//_______________________________TEST!!!!!!!!!_____________________________=======
        values.put(COLUMN_NAME, tempPlan.getPlanName());
        //values.put(COLUMN_STARTDATE, tempPlan.getStartDate());


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MEALPLANS, null, values);
        db.close();
    }

    public List<String> getPlansNames(){

        List<String> array = new ArrayList<>();
        String getNamesQuery = "SELECT " + COLUMN_NAME + " FROM MealPlans";
        String temp = "no Temp";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(getNamesQuery, null);

        if(c.moveToFirst())
        {
            do
            {
                array.add(c.getString(0));
            }while(c.moveToNext());
            c.close();
        }
        else
            array.add("did not work");

        db.close();
        return array;
    }

    public void updateRecipe( int index, String name, List<Ingredient> ings, int portion, String directions){
        Log.d("update index: ", String.valueOf(index));//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        SQLiteDatabase db = this.getReadableDatabase();
        String SQLgetMP_ID = "SELECT " + MP_ID + " FROM " + TABLE_MPRECIPES + " WHERE " + COLUMN_INDEX + "=" + index;
        Cursor cID = db.rawQuery(SQLgetMP_ID, null);
        cID.moveToFirst();
        int mp_ID = cID.getInt(0);
        cID.close();

        db = this.getWritableDatabase();
        String SQLremoveIngredients = "DELETE FROM " + TABLE_MPINGREDIENTS + " WHERE " + COLUMN_INDEX + "=" + index;
        db.execSQL(SQLrecipeUpdate( index, name, portion, directions));
        db.execSQL(SQLremoveIngredients);
        //List<Ingredient> list = getIngredientsFromRecipe(recipeID);
        for(int i = 0; i < ings.size(); i++) {
            db.execSQL(SQLingredientsInsert(index, mp_ID, ings.get(i).getName(), ings.get(i).getAmount(), ings.get(i).getMeasurement()));
        }
        db.close();

    }

    public MealPlan getPlan(int id){
        MealPlan tempPlan = new MealPlan();
        List<Recipe> recs = new ArrayList<>();
        List<Ingredient> ingredients;
        Ingredient tempIng;
        Recipe tempRec;
        SQLiteDatabase db = this.getReadableDatabase();
        String getMPQuery = "SELECT * FROM " + TABLE_MEALPLANS + " WHERE " + MP_ID + "=" + id;
        Cursor i = db.rawQuery(getMPQuery, null);
        Cursor c;
        Cursor j;

        if (i.getCount() > 0) {
            i.moveToFirst();
            String getRecipesQuery = "SELECT * FROM " + TABLE_MPRECIPES + " WHERE " + MP_ID + "=" + i.getInt(0);
            c = db.rawQuery(getRecipesQuery, null);
            //recs = new ArrayList<>();


            if (c.moveToFirst()) {
                do {
                    String getIngredientsQuery = "SELECT * FROM " + TABLE_MPINGREDIENTS +
                            " WHERE "+ COLUMN_INDEX +"=" + c.getInt(1) + " AND " + MP_ID + "=" + c.getInt(0);
                    j = db.rawQuery(getIngredientsQuery, null);
                    ingredients = new ArrayList<>();

                    if(j.moveToFirst())
                    {
                        do{
                            tempIng = new Ingredient(j.getString(2), j.getDouble(3), j.getString(4));
                            ingredients.add(tempIng);
                        }while(j.moveToNext());
                        j.close();
                    }
                    tempRec = new Recipe(c.getInt(2), c.getInt(1), c.getString(3), ingredients, c.getString(5));
                    //TODO -> make constructor for mealplan database index in recipe class
                    recs.add(tempRec);
                } while (c.moveToNext());
                c.close();
            }

            tempPlan = new MealPlan(i.getInt(0), i.getString(1), i.getInt(2), i.getInt(3), i.getInt(4), i.getInt(5), i.getInt(6), i.getInt(7), i.getInt(8), recs);
            i.close();
        }
        db.close();
        return tempPlan;
    }

    public List<MealPlan> getPlans() {

        ///&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        List<MealPlan> plans = new ArrayList<>();
        MealPlan temp;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Recipe> recs = new ArrayList<>();
        Recipe tempRec;
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient tempIng;
        String getMPQuery = "SELECT * FROM " + TABLE_MEALPLANS;
        Cursor c;
        Cursor j;
        Cursor i = db.rawQuery(getMPQuery, null);
        if (i.getCount() > 0) {
            i.moveToFirst();
            do {
                String getRecipesQuery = "SELECT * FROM " + TABLE_MPRECIPES + " WHERE " + MP_ID + "=" + i.getInt(0);
                c = db.rawQuery(getRecipesQuery, null);
                recs = new ArrayList<>();


                if (c.moveToFirst()) {
                    do {
                        String getIngredientsQuery = "SELECT * FROM " + TABLE_MPINGREDIENTS +
                                " WHERE "+ COLUMN_INDEX +"=" + c.getInt(1) + " AND " + MP_ID + "=" + c.getInt(0);
                        j = db.rawQuery(getIngredientsQuery, null);
                        ingredients = new ArrayList<>();

                        if(j.moveToFirst())
                        {
                            do{
                                tempIng = new Ingredient(j.getString(2), j.getDouble(3), j.getString(4));
                                ingredients.add(tempIng);
                            }while(j.moveToNext());
                            j.close();
                        }
                        tempRec = new Recipe(c.getInt(2),c.getInt(1), c.getString(3), ingredients, c.getString(5));
                        recs.add(tempRec);
                    } while (c.moveToNext());
                    c.close();
                }
                temp = new MealPlan(i.getInt(0), i.getString(1), i.getInt(2), i.getInt(3), i.getInt(4), i.getInt(5), i.getInt(6), i.getInt(7), i.getInt(8), recs);
                plans.add(temp);
            } while (i.moveToNext());
            i.close();
        }
        db.close();
        return plans;
    }

    private String SQLPlanInsert(int id, String name, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear, int length) {
        String insertRecipeSQL = "INSERT INTO " + TABLE_MEALPLANS + " (" + MP_ID + ", " +
                COLUMN_NAME + ", " + COLUMN_STARTMONTH + ", " + COLUMN_STARTDAY + ", " + COLUMN_STARTYEAR +
                ", " + COLUMN_ENDMONTH + ", " + COLUMN_ENDDAY + ", " + COLUMN_ENDYEAR + ", "  + COLUMN_LENGTH + ") VALUES (";
        String endSQL = ");";
        String insert;
        insert = new String(insertRecipeSQL + Integer.toString(id) + ", '" + name + "', "
                + Integer.toString(startMonth) + ", " + Integer.toString(startDay) + ", " + Integer.toString(startYear) + ", " +
                Integer.toString(endMonth) + ", " + Integer.toString(endDay) + ", " + Integer.toString(endYear) + ", " + Integer.toString(length) + endSQL);

        return insert;
    }
    private String SQLrecipesInsert(int id, int index, int recipe_ID, String name, int portion, String directions) {
        String insertRecipeSQL = "INSERT INTO " + TABLE_MPRECIPES + " (" + MP_ID + ", " + COLUMN_INDEX + ", " +
                COLUMN_RECIPEID + ", " + COLUMN_NAME + ", " + COLUMN_PORTION + ", " + COLUMN_DIRECTIONS + ") VALUES (";
        String endSQL = "');";
        String insert;
        insert = new String(insertRecipeSQL + Integer.toString(id) + ", " + Integer.toString(index) + ", " +
                Integer.toString(recipe_ID) + ", '" + name + "', " + Integer.toString(portion) + ", '" + directions + endSQL);

        return insert;
    }

    private String SQLrecipeUpdate( int index, String name, int portion, String directions) {
        String insertRecipeSQL = "UPDATE " + TABLE_MPRECIPES + " SET " +
                COLUMN_NAME + " = '" + name + "', " +
                COLUMN_PORTION + " = " + portion + ", " +
                COLUMN_DIRECTIONS + " = '" + directions + "' " +
                " WHERE " + COLUMN_INDEX + " = " + index + ";";
        return insertRecipeSQL;
    }

    private String SQLingredientsInsert(int index, int mp_id, String name, double amount, String mRule)
    {
        String insertRecipeSQL = "INSERT INTO " + TABLE_MPINGREDIENTS + " (" + COLUMN_INDEX + ", " + MP_ID + ", " +
                COLUMN_NAME + ", " + COLUMN_AMOUNT + ", " + COLUMN_MRULE + ") VALUES (";
        String endSQL = "');";
        String insert;
        insert = new String (insertRecipeSQL + Integer.toString(index) + ", " + Integer.toString(mp_id) + ", '" + name + "', "
                + Double.toString(amount) + ", '" + mRule + endSQL);

        return insert;
    }

    private List <Ingredient> getIngredientsFromRecipe(int index)
    {
        List<Ingredient> list = new ArrayList<>();
        Ingredient tempIng;
        Cursor j;
        SQLiteDatabase db = this.getReadableDatabase();
        String getIngredientsQuery = "SELECT * FROM " + TABLE_MPINGREDIENTS +
                " WHERE "+ COLUMN_INDEX +"=" + index;
        j = db.rawQuery(getIngredientsQuery, null);


        if(j.moveToFirst())
        {
            do{
                tempIng = new Ingredient(j.getString(2), j.getDouble(3), j.getString(4));
                list.add(tempIng);
            }while(j.moveToNext());
        }
        return list;
    }

    public void saveMealPlan(MealPlan mp)
    {
        int numPlans = getNumPlans();
        int numRecipes = getNumRecipes();

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQLPlanInsert(numPlans , mp.getPlanName(), mp.getStartMonth(), mp.getStartDay(), mp.getStartYear(), mp.getEndMonth(), mp.getEndDay(), mp.getEndYear(), mp.getLength()));
        for(int i = 0; i < mp.getLength(); i++) {
            db.execSQL(SQLrecipesInsert(numPlans , numRecipes, mp.getMealID(i), mp.getMealName(i), mp.getMealPortions(i), mp.getMealDirections(i)));
            List<Ingredient> ings = mp.getMealIngredients(i);
            for(int j = 0; j < ings.size(); j++) {
                db.execSQL(SQLingredientsInsert(numRecipes, numPlans , ings.get(j).getName(), ings.get(j).getAmount(), ings.get(j).getMeasurement()));
            }
            numRecipes++;
        }
        db.close();
    }

    public int getNumPlans(){
        SQLiteDatabase db = this.getReadableDatabase();
        String getMPQuery = "SELECT * FROM " + TABLE_MEALPLANS;
        Cursor i = db.rawQuery(getMPQuery, null);
        return i.getCount();
    }

    public int getNumRecipes(){
        SQLiteDatabase db = this.getReadableDatabase();
        String getMPQuery = "SELECT * FROM " + TABLE_MPRECIPES;
        Cursor i = db.rawQuery(getMPQuery, null);
        return i.getCount();
    }

    /*private List<MealPlan> DEBUG_TEST (){
        //~~~~~~~~~~~~~~~~~TEST~~~~~~~~~~~~~~~~~~~~~~~~~~
        List<MealPlan> myPlans = new ArrayList<>();
        List<Recipe> testRecs = new ArrayList<>();
        Recipe tempRec2 = new Recipe();
        for(int f = 0; f < 30; f++)
            testRecs.add(tempRec2);
        MealPlan testPlan = new MealPlan(0, "TEST!!!!", "April 1, 1990", "April 30, 1990", 30, testRecs);
        myPlans.add(testPlan);
        return myPlans;
        //~~~~~~~~~~~~~~~~~TEST~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    private void DEBUG_PLANS_TEST(){
        DEBUG_PLANS.addAll(DEBUG_TEST());
    }

    public void DEBUG_fillTables(SQLiteDatabase db)
    {
        db.execSQL(SQLPlanInsert(0, "%NEW TEST%", "April 1 1970", "April 30 1970", 30));
        for(int i = 0; i < 30; i++)
        {
            db.execSQL(SQLrecipesInsert(0, i, "DEBUG RECIPE", 2, "DONT EVEN THINK ABOUT COOKING THIS!"));
        }
        db.execSQL(SQLingredientsInsert(0, "CHOCOLATE", 2.0, "BARS"));


        String getMPQuery = "SELECT * FROM " + TABLE_MEALPLANS;
        Cursor i = db.rawQuery(getMPQuery, null);

        if(i.moveToFirst())//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TEST~~~~~~~~~~~~~
        {
            DEBUG_PLANS_TEST();
        }
    }*/
}
