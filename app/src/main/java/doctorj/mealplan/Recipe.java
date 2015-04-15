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

    public Recipe()
    {
        this._id = 0;
        this.name = "Not Set";
        this.directions = "Not Set";
        return;
    }

    public Recipe(int idNum, String title)
    {
        this._id = idNum;
        this.name = title;

    }

    public Recipe(int idNum, String title, Ingredient []ings, String directs)
    {
        this._id = idNum;
        this.name = title;
        this.ingredients = ings;
        this.directions = directs;
    }
    public void set_id(int num) {this._id = num;}
    public void setName(String tempName){ this.name = tempName; }
    public void setDirections(String direct) {this.directions = direct; }
    public int get_id(){ return this._id; }
    public String getName()
    {
        return this.name;
    }
    public String getDirections() { return this.directions; }

    public Ingredient [] getIngredients(int idNum)
    {
        //TODO -> SQL in RecipeHelper to get ingredients by recipe idNum
        return this.ingredients;
    }

    public String getDirections(int idNum)
    {
        //TODO -> SQL in RecipeHelper to get Directions by recipe idNum
        return this.directions;
    }

}
