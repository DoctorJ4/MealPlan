package doctorj.mealplan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class Recipe
{
    private int _id;
    private int index;
    private String name;
    private List<Ingredient> ingredients;
    private String ingString;
    private int portions;
    private String directions;
    private String category;
    private boolean favorite;

    public Recipe()
    {
        this._id = 0;
        this.name = "Not Set";
        this.directions = "Not Set";
        this.ingString = "Not Set";
        this.portions = 2;
        this.ingredients = new ArrayList<>();
        this.favorite = false;
    }

    public Recipe(int idNum, String title, List<Ingredient> ings, String directs)
    {
        this._id = idNum;
        this.index = 0;
        this.name = title;
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ings);// = new ArrayList<>(ings);
        this.ingString = "";
        this.portions = 2;
        this.directions = directs;
        this.favorite = false;
    }

    public Recipe(int idNum, int newIndex, String title, List<Ingredient> ings, String directs)
    {
        this._id = idNum;
        this.index = newIndex;
        this.name = title;
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ings);// = new ArrayList<>(ings);
        this.ingString = "";
        this.portions = 2;
        this.directions = directs;
        this.favorite = false;
    }

    public void set_id(int idNum) {this._id = idNum;}
    public void setIndex(int index) {this.index = index;}
    public void setName(String tempName){ this.name = tempName; }
    public void setIngredients(List<Ingredient> tempIngs){ this.ingredients = new ArrayList<>(tempIngs); }
    public void setDirections(String direct) { this.directions = direct; }
    public void setIngString(String is){ this.ingString = is; }
    public void setCategory(String c){ this.category = c; }
    public void setFavorite(boolean fav){ this.favorite = fav; }

    public int get_id(){ return this._id; }
    public int getIndex() {return index;}
    public String getName()
    {
        return this.name;
    }
    public int getPortions(){ return this.portions; }
    public String getDirections() { return this.directions; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public String getCategory(){ return this.category; }
    public boolean isFavorite(){ return this.favorite; }

    public String getIngredientsString()
    {
        if(this.ingString != "")
            return ingString;
        Ingredient tempIng;
        for(int i = 0; i < this.ingredients.size(); i++) {
            tempIng = this.ingredients.get(i);
            ingString = ingString + "\t " + formatAmount(tempIng.getAmount()) + " " + tempIng.getMeasurement() + " " + tempIng.getName() + "\n";
        }
        return this.ingString;
    }

    private String formatAmount(double amt)
    {
        String newAmt;
        String delim = "[.]";
        String fraction;
        newAmt = Double.toString(amt);
        String[] tokens = newAmt.split(delim);
        if(!tokens[0].equals("0"))
            newAmt = tokens[0] + " ";
        else
            newAmt = "";
        switch (tokens[1])
        {
            case "0": fraction = "";
                break;
            case "125": fraction = "1/8 ";
                break;
            case "25": fraction = "1/4 ";
                break;
            case "33": fraction = "1/3 ";
                break;
            case "5": fraction = "1/2 ";
                break;
            case "66": fraction = "2/3 ";
                break;
            case "75": fraction = "3/4 ";
                break;
            default: fraction = "";
                break;
        }

        newAmt = newAmt + fraction;
        return newAmt;
    }
}
