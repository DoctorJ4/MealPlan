package doctorj.mealplan;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class RecipeAddViewActivity extends Activity {
    private int recipeID;
    private int recipeIndex;
    private String recipeName;
    private String ingredients;
    private int recipePortions;
    private String directions;
    private Context context = this;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeview);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipeview);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        this.recipeID = extras.getInt("RecipeID");
        this.recipeName = extras.getString("StringName");
        this.ingredients = extras.getString("StringIngredients");
        this.recipePortions = extras.getInt("Portions");
        this.directions = extras.getString("StringDirections");
        this.recipeIndex = extras.getInt("recipeIndex");

        final TextView nameView = (TextView)
                findViewById(R.id.Name);
        final TextView ingView = (TextView)
                findViewById(R.id.Ingredients);
        final TextView directionsView = (TextView)
                findViewById(R.id.Directions);
        nameView.setText(this.recipeName);
        ingView.setText(this.ingredients);
        directionsView.setText(this.directions);

        /*Button addButton = (Button) findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                MealPlanHelper mpH = new MealPlanHelper(context);
                mpH.updateRecipe( mpIndex, recipeName, recipePortions, directions);
            }
        });*/
    }

    public void addButtonClick(View view)
    {
        RecipeHelper rH = new RecipeHelper(context);
        MealPlanHelper mpH = new MealPlanHelper(context);
        List<Ingredient> ings = rH.getrecipeIngredients(recipeID);
        mpH.updateRecipe(recipeIndex, recipeName, ings, recipePortions, directions);
        Log.d("addRecipeView: index: ", String.valueOf(recipeIndex));
        Log.d("addRecipeView: name: ", recipeName);
        Log.d("addRecipeView: inName: ", ings.get(0).getName());
        Log.d("addRecipeView: port: ", String.valueOf(recipePortions));
        Log.d("addRecipeView: dire: ", directions);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 1);
        setResult(RESULT_OK, returnIntent);
        finish();
    }



    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 0);
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    private void populateTextBoxes()
    {
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.recipe_layout,this.RecipeName);
        TextView nameTag = (TextView) findViewById(R.id.Name);
        nameTag.setText(this.recipeName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display__details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
