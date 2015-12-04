package doctorj.mealplan;

import java.util.List;

/**
 * Created by Jesse on 12/4/2015.
 */
class DatabaseRecipeBookDefines {
    protected final String TABLE_RECIPES = DatabaseDefines.TABLE_RECIPES;
    protected final String TABLE_INGREDIENTS = DatabaseDefines.TABLE_INGREDIENTS;
    protected final String COLUMN_RECIPE_ID = DatabaseDefines.COLUMN_RECIPE_ID;
    protected final String COLUMN_NAME = DatabaseDefines.COLUMN_NAME;
    protected final String COLUMN_DIRECTIONS = DatabaseDefines.COLUMN_DIRECTIONS;
    protected final String COLUMN_PORTIONS = DatabaseDefines.COLUMN_PORTIONS;
    protected final String COLUMN_AMOUNT = DatabaseDefines.COLUMN_AMOUNT;
    protected final String COLUMN_MRULE = DatabaseDefines.COLUMN_MRULE;
    protected final String COLUMN_CATEGORY = DatabaseDefines.COLUMN_CATEGORY;
    protected final String COLUMN_FAVORITE = DatabaseDefines.COLUMN_FAVORITE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    protected List<String> MyList;
    protected List<String> MyIngredientsList;
    protected final char ds = 0x00B0; //degree symbol

    //region CULTURE MEAL TYPE CATEGORIES
    protected final String Italian = "Italian";
    protected final String American = "American";
    protected final String Mexican = "Mexican";
    //endregion
    //region MEASUREMENT TYPES
    protected final String slices = "Slice(s)";
    protected final String cups = "Cup(s)";
    protected final String tsp = "tsp.";
    protected final String tbsp = "Tbsp";
    protected final String oz = "oz.";
    protected final String lb = "lb.";
    protected final String box = "Box";
    protected final String block = "Block";
    protected final String cans = "Can(s)";
    protected final String small = "Small";
    protected final String medium = "Medium";
    protected final String large = "Large";
    protected final String whole = "Whole";
    protected final String pinch = "Pinch";
    protected final String cloves = "Cloves";
    //endregion
    //region INGREDIENT NAMES
    //region boxes and jars
    protected final String velveetaShells = "Velveeta Shells";
    protected final String marinaraSpagSauce = "Marinara Spaghetti Sauce";
    protected final String spagSauce = "Spaghetti Sauce";
    protected final String pillsPizzaCrust = "Pillsbury Pizza Crust";
    //endregion
    //region meats
    protected final String chickenB = "Chicken Breast";
    protected final String groundBeef = "Ground Beef";
    protected final String groundSausage = "Ground Sausage";
    protected final String porkRoast = "Pork Roast";
    protected final String bacon = "Bacon";
    protected final String eggs = "Egg(s)";
    //endregion
    //region Fruits
    protected final String lime = "Lime(s)";
    protected final String avocado = "Avacado(es)";
    protected final String orange = "Orange(s)";
    protected final String orangeZest = "Orange Zest";
    //endregion
    //region Vegetables
    protected final String tomatoes = "Tomato(es)";
    protected final String potatoes = "Potato(es)";
    protected final String greenOnions = "Green Onion(s)";
    protected final String choppedOnion = "Chopped Onion";
    protected final String choppedPickles = "Chopped pickles";
    protected final String frozenCorn = "Frozen Corn";
    protected final String blackBeans = "Black Beans";
    protected final String jalapeno = "Jalapeño(es)";
    //endregion
    //region bread
    protected final String bread = "Bread";
    protected final String whiteRice = "White Rice";
    //endregion
    //region cheese
    protected final String AmericanCheese = "American Cheese";
    protected final String ShreddedParmesean = "Shredded Parmesan";
    protected final String S_mozzarella = "Shredded Mozzarella";
    protected final String S_cheddar = "Shredded Cheddar";
    protected final String velveeta = "Velveeta";
    //endregion
    //region butter/oils
    protected final String butter = "Butter";
    protected final String vegetableOil = "Vegetable Oil";
    protected final String oliveOil = "Olive Oil";
    //endregion
    //region dairy
    protected final String HCream = "Heavy Cream";
    protected final String milk = "Milk";
    protected final String sourCream = "Sour Cream";
    //endregion
    //region condiments
    protected final String ketchup = "Ketchup";
    protected final String mustard = "Mustard";
    //endregion
    //region seasoning
    protected final String onionPowder = "Onion Powder";
    protected final String italianSeasoning = "Italian Seasoning";
    protected final String mincedGarlic = "Minced Garlic";
    protected final String garlicSalt = "Garlic Salt";
    protected final String garlicPowder = "Garlic Powder";
    protected final String lemonPepper = "Lemon Pepper";
    protected final String pepper = "Pepper";
    protected final String salt = "Salt";
    protected final String cilantro = "Cilantro";
    protected final String cumin = "Cumin";
    protected final String oregano = "Oregano";
    //endregion
    //region Pasta
    protected final String spagNoodles = "Spaghetti Noodles";
    //endregion
    //region Cans or Soup
    protected final String creamOfChicken = "Cream of Chicken";
    protected final String creamOfCorn = "Cream of Corn";
    protected final String chickenBroth = "Chicken Broth";
    protected final String rotel = "Rotel";
    //endregion
    //region Baking supplies
    protected final String flour = "Flour";
    protected final String sugar = "Sugar";
    //endregion
    //endregion


    protected String SQLRecipeInsert(int id, String name, int portion, String directions, String category, int favorite)
    {
        String insertRecipeSQL = "INSERT INTO " + TABLE_RECIPES + " ("+
                COLUMN_RECIPE_ID + ", " +
                COLUMN_NAME + ", " +
                COLUMN_PORTIONS + ", " +
                COLUMN_DIRECTIONS + ", " +
                COLUMN_CATEGORY + ", " +
                COLUMN_FAVORITE + ") VALUES (";
        String endSQL = ");";
        String insert;
        insert = new String (
                insertRecipeSQL +
                        Integer.toString(id) + ", '" +
                        name + "', " +
                        Integer.toString(portion) + ", '" +
                        directions + "', '" +
                        category + "', " +
                        favorite + endSQL);

        return insert;
    }

    protected String SQLIngredientInsert(int id, String name, double amount, String mRule)
    {
        String insertIngredientSQL = "INSERT INTO " + TABLE_INGREDIENTS + " (" +
                COLUMN_RECIPE_ID + ", " +
                COLUMN_NAME + ", " +
                COLUMN_AMOUNT + ", " +
                COLUMN_MRULE + ") VALUES (";
        String endSQL = "');";
        String insert;
        insert = new String (insertIngredientSQL + Integer.toString(id) + ", '" + name + "', "
                + Double.toString(amount) + ", '" + mRule + endSQL);

        return insert;
    }

    protected List <String> getMyIngredientsList(){return this.MyIngredientsList;}
    protected List <String> getMyList(){return this.MyList;}

}
