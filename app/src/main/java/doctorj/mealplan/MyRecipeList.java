package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSU on 4/14/2015.
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
    //ingredient names----------------------------------------------------
    //meats~~~~~~~~~~~~~~~~~~~~~~~~~
    String ChickenB = "Chicken Breast";
    String HMeat = "Hamburger Meat";
    //bread~~~~~~~~~~~~~~~~~~~~~~~~~~
    String bread = "Bread";
    //cheese~~~~~~~~~~~~~~~~~~~~~~~~~
    String Acheese = "American Cheese";
    String parm = "Parmesean";
    //butter/oils~~~~~~~~~~~~~~~~~~~~
    String butter = "Butter";
    //dairy~~~~~~~~~~~~~~~~~~~~~~~~~~
    String HCream = "Heavy Cream";


    MyRecipeList(){
        MyList = new ArrayList<>();
        MyIngredientsList = new ArrayList<>();

        MyList.add(SQLRecipeInsert(0, "Grilled Chicken", 2,
                "Marinate chicken and grill it."));
        MyIngredientsList.add(SQLIngredientInsert(0, bread, 4, slices));

        MyList.add(SQLRecipeInsert(1, "Chicken Parmesean", 2,
                "Bread chicken and bake it."));
        MyList.add(SQLRecipeInsert(2, "Chicken Pot Pie", 2,
                "Cook chicken and put it in the pie."));
        MyList.add(SQLRecipeInsert(3, "Fried Chicken", 2,
                "Fry the chicken."));

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
