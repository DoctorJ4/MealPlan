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
    private List<Ingredient> ingredients;
    private String ingString;
    private int portions;
    private String directions;

    public Recipe()
    {
        this._id = 0;
        this.name = "Not Set";
        this.directions = "Not Set";
        this.ingString = "Not Set";
        this.portions = 2;
        this.ingredients = new ArrayList<>();
        return;
    }

    public Recipe(int idNum, String title, List<Ingredient> ings, String directs)
    {
        this._id = idNum;
        this.name = title;
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ings);// = new ArrayList<>(ings);
        this.ingString = "";
        this.portions = 2;
        this.directions = directs;
    }

    public void set_id(int idNum) {this._id = idNum;}
    public void setName(String tempName){ this.name = tempName; }
    public void setIngredients(List<Ingredient> tempIngs){ this.ingredients = new ArrayList<>(tempIngs); }
    public void setDirections(String direct) { this.directions = direct; }
    public void setIngString(String is){ this.ingString = is; }

    public int get_id(){ return this._id; }
    public String getName()
    {
        return this.name;
    }
    public int getPortions(){ return this.portions; }
    public String getDirections() { return this.directions; }
    public List<Ingredient> getIngredients() { return this.ingredients; }

    public String getIngredientsString()
    {
        if(this.ingString != "")
            return ingString;
        Ingredient tempIng;
        for(int i = 0; i < this.ingredients.size(); i++) {
            tempIng = this.ingredients.get(i);
            ingString = ingString + "\t- " + tempIng.getAmount() + " " + tempIng.getMeasurement() + " " + tempIng.getName() + "\n";
        }
        return this.ingString;
    }
}
