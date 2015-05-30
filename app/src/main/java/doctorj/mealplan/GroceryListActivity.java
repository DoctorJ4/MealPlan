package doctorj.mealplan;

import doctorj.mealplan.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GroceryListActivity extends Activity {

    //MealPlan selectd;
    String RecipeName;
    String Ingredients;
    String Directions;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeview);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        final TextView groceryListView;
        groceryListView = (TextView) findViewById(R.id.grocery_list_text);
        //groceryListView.setText(*******);
//groceryListView.
        populateGroceryList(groceryListView);
        //String listString = getGLstring();


    }

    private void populateGroceryList(TextView GLview)
    {
        String listString = getGLstring();
        GLview.setText(listString);
    }

    private String getGLstring(){
        GroceryList GL = new GroceryList();
        for(int i = 0; i < GlobalPlan.globalPlan.getLength(); i++)
            GL.addGL(GlobalPlan.globalPlan.getMealIngredients(i));

        return GL.getListString();
        //return "empty";
    }

}
