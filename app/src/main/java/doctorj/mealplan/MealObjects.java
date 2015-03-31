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
    private ArrayList<Ingredient> ingList;

    public MealObjects(String title, String ingredientString, String directions)
    {
        this.name = title;

        this.ingList = new ArrayList<Ingredient>();
        //TODO -> loop through ingredient string until Each name and amount field is initialized

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
