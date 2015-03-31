package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealObjects
{
    private String name;
    //private String ingredients;
    private Ingredient ingredients[];
    private String directions;

    public MealObjects(String title, String ingredientString, String directions)
    {
        this.name = title;
        //this.ingredients = ingredients;
        //TODO 1 -> ingredients object[].name = String   object[].amount = int
        //loop through ingredient string until Each
        List<Ingredient> list = new ArrayList<Ingredient>(ingredients);//TODO -> create collection with the ingreients.name and ingredients.amount fields -> Also need to change getter
        this.directions = directions;
    }

    public String getName()
    {
        return name;
    }

    public Ingredient [] getIngredients()
    {
        return ingredients;
    }

    public String getDirections()
    {
        return directions;
    }

}
