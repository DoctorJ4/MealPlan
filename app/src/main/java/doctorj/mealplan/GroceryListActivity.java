package doctorj.mealplan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GroceryListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        final TextView groceryListView;
        groceryListView = (TextView) findViewById(R.id.grocery_list_text);
        populateGroceryList(groceryListView);
    }


    private void populateGroceryList(TextView GLview)
    {

        String listString = getGLstring();
        GLview.setText(listString);
    }

    private String getGLstring(){
        GroceryList GL = new GroceryList();
        Log.d("PlanIngredients1", MainActivity.plan.getMealIngredientsString(0) );
        for(int i = 0; i < MainActivity.plan.getLength(); i++) {
            GL.addGL(MainActivity.plan.getMealIngredients(i));
        }
        Log.d("PlanIngredients2", MainActivity.plan.getMealIngredientsString(0) );
        GL.sort();
        return GL.getListString();
    }

}
