package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse Dodson on 4/14/2015.
 */
public class MyRecipeList {
    private List<String> MyList;
    private List<String> MyIngredientsList;
    //measurement types--------------------------------------------------
    String slices = "Slice(s)";
    String cups = "Cup(s)";
    String tsp = "tsp.";
    String tbsp = "Tbsp";
    String oz = "oz.";
    String LB = "lb.";
    String box = "box";
    //ingredient names----------------------------------------------------
    //boxes~~~~~~~~~~~~~~~~~~~~~~~~~
    String velveetaShells = "Velveeta Shells";
    //meats~~~~~~~~~~~~~~~~~~~~~~~~~
    String ChickenB = "Chicken Breast";
    String HMeat = "Hamburger Meat";
    //Vegetables~~~~~~~~~~~~~~~~~~~~~
    String tomatoes = "Tomatoe(s)";
    String greenOnions = "Green Onion(s)";
    //bread~~~~~~~~~~~~~~~~~~~~~~~~~~
    String bread = "Bread";
    //cheese~~~~~~~~~~~~~~~~~~~~~~~~~
    String Acheese = "American Cheese";
    String parm = "Parmesean";
    //butter/oils~~~~~~~~~~~~~~~~~~~~
    String butter = "Butter";
    //dairy~~~~~~~~~~~~~~~~~~~~~~~~~~
    String HCream = "Heavy Cream";
    String milk = "Milk";
    String sourCream = "Sour Cream";
    //condiments~~~~~~~~~~~~~~~~~~~~~
    String ketchup = "Ketchup";
    String mustard = "Mustard";
    //seasoning~~~~~~~~~~~~~~~~~~~~~~
    String onionPowder = "Onion Powder";


    MyRecipeList(){
        MyList = new ArrayList<>();
        MyIngredientsList = new ArrayList<>();

        MyList.add(SQLRecipeInsert(0, "Grilled Chicken", 2,
                "Marinate chicken and grill it."));
        MyIngredientsList.add(SQLIngredientInsert(0, bread, 4, slices));
        MyIngredientsList.add(SQLIngredientInsert(0, Acheese, 2, slices));

        MyList.add(SQLRecipeInsert(1, "Chicken Parmesean", 2,
                "Bread chicken and bake it."));
        MyIngredientsList.add(SQLIngredientInsert(1, parm, 4, cups));

        MyList.add(SQLRecipeInsert(2, "Chicken Pot Pie", 2,
                "Cook chicken and put it in the pie."));
        MyIngredientsList.add(SQLIngredientInsert(2, onionPowder, 1, tbsp));
        MyIngredientsList.add(SQLIngredientInsert(2, velveetaShells, 1, box));

        MyList.add(SQLRecipeInsert(3, "Fried Chicken", 2,
                "Fry the chicken."));
        MyIngredientsList.add(SQLIngredientInsert(3, bread, 4, slices));

        MyList.add(SQLRecipeInsert(4, "Velveeta Shells and Hamburger Skillet", 2,
                "BROWN meat in large skillet; drain. Return meat to skillet. " +
                        "ADD milk, ketchup, mustard and onion powder; mix well. Bring to boil. Stir in Shell Pasta, " +
                        "return to boil. Cover, simmer on medium-low heat 10 min. or until pasta is tender." +
                        "STIR in Cheese Sauce and sour cream until blended. Add tomatoes and onions; mix well."));
        MyIngredientsList.add(SQLIngredientInsert(4, HMeat, 1, LB));
        MyIngredientsList.add(SQLIngredientInsert(4, milk, 2.5, cups));
        MyIngredientsList.add(SQLIngredientInsert(4, ketchup, 0.25, cups));
        MyIngredientsList.add(SQLIngredientInsert(4, mustard, 2, tbsp));
        MyIngredientsList.add(SQLIngredientInsert(4, onionPowder, 1, tbsp));
        MyIngredientsList.add(SQLIngredientInsert(4, velveetaShells, 1, box));
        MyIngredientsList.add(SQLIngredientInsert(4, sourCream, 0.25, cups));
        MyIngredientsList.add(SQLIngredientInsert(4, tomatoes, 1, "Large"));
        MyIngredientsList.add(SQLIngredientInsert(4, greenOnions, 0.25, cups));



    }

    private String SQLRecipeInsert(int id, String name, int portion, String directions)
    {
        String insertRecipeSQL = "INSERT INTO Recipes (id, Name, Portions, Directions) VALUES (";
        String endSQL = ");";
        String insert;
        insert = new String (insertRecipeSQL + Integer.toString(id) + ", '" + name + "', "
                + Integer.toString(portion) + ", '" + directions + "'" + endSQL);

        return insert;
    }

    private String SQLIngredientInsert(int id, String name, double amount, String mRule)
    {
        String insertIngredientSQL = "INSERT INTO Ingredients (id, Name, Amount, MRule) VALUES (";
        String endSQL = ");";
        String insert;
        insert = new String (insertIngredientSQL + Integer.toString(id) + ", '" + name + "', "
                + Double.toString(amount) + ", '" + mRule + "'" + endSQL);

        return insert;
    }

    List <String> getMyIngredientsList(){return this.MyIngredientsList;}
    List <String> getMyList(){return this.MyList;}

}
