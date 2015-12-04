package doctorj.mealplan;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MealPlanEditActivity extends ActionBarActivity {
    DatabaseRecipeHelper db;
    MealPlanHelper MPdb;
    List<Recipe> recipes;
    int editLevel = 0;
    private String [] categories = {"Italian", "American", "Mexican"};
    private List<String> listFields = new ArrayList<>();
    private int recipeIndex;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sparse);
        db = new DatabaseRecipeHelper(this);
        MPdb = new MealPlanHelper(this);
        recipes = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.recipeIndex = extras.getInt("recipeIndex");
        }
        populateListView();
    }

    private void refreshListView(String category){
        recipes = db.getRecipesCategory(category);
        listFields = new ArrayList<>();
        for(int i = 0; i < recipes.size(); i++)
            listFields.add(recipes.get(i).getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, listFields);
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
        setListViewClickListener(mealTag);
    }

    private void populateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, this.categories);
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
        setListViewClickListener(mealTag);
    }

    private void setListViewClickListener(ListView LV)
    {
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (editLevel == 0) {
                    editLevel = 1;
                    refreshListView(categories[position]);
                } else {
                    Intent act = new Intent(getApplicationContext(), RecipeAddViewActivity.class);
                    act.putExtra("RecipeID", recipes.get(position).get_id());
                    act.putExtra("StringName", recipes.get(position).getName());
                    act.putExtra("StringIngredients", recipes.get(position).getIngredientsString());
                    act.putExtra("StringDirections", recipes.get(position).getDirections());
                    act.putExtra("Portions", recipes.get(position).getPortions());
                    act.putExtra("RecipeCategory", recipes.get(position).getCategory());
                    act.putExtra("recipeIndex", recipeIndex);
                    startActivityForResult(act, 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                finish();
            }
            else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        if(editLevel == 0) {
            super.onBackPressed();
            finish();
        }
        else if (editLevel == 1) {
            editLevel = 0;
            populateListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}
