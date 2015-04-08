package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class Recipe
{
    private int _id;
    private String name;
    private Ingredient ingredients[];
    private String directions;

    public Recipe(String title, Ingredient []ings, String directs)
    {
        this.name = title;
        this.ingredients = ings;
        this.directions = directs;
    }

    public String getName()
    {
        return this.name;
    }

    public Ingredient [] getIngredients()
    {
        return this.ingredients;
    }

    public String getDirections()
    {
        return this.directions;
    }

}
