package doctorj.mealplan;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jesse Dodson on 4/14/2015.
 */
public class MyRecipeList extends DatabaseRecipeBookDefines {
    MyRecipeList(){
        MyList = new ArrayList<>();
        MyIngredientsList = new ArrayList<>();

        MyList.add(new DatabaseRecipeCreateObject("Cheeseburger Pizza", 2,
                "Heat oven to 400" + ds + "F. Spray or grease 15x10-inch or larger dark or nonstick cookie sheet. Unroll dough" +
                        "on cookie sheet; starting at center, press dough into 15x10-inch rectangle. In 10-inch nonstick skillet, cook beef, onion and pepper over medium-high heat, stirring frequently, " +
                        "until cooked thoroughly; drain. Stir in pickles, ketchup and mustard. Spoon evenly over dough. " +
                        "Sprinkle with bacon and cheese. Bake 14 to 19 minutes or until crust is golden brown and cheese is melted. Serve immediately.", American, false));
        AddIngToList(pillsPizzaCrust, 2, cans);
        AddIngToList(groundBeef, 1, lb);
        AddIngToList(choppedOnion, 0.5, cups);
        AddIngToList(pepper, 0.125, tsp);
        AddIngToList(choppedPickles, 0.33, cups);
        AddIngToList(mustard, 1, tbsp);
        AddIngToList(bacon, 4, slices);
        AddIngToList(S_cheddar, 1.5, cups);
        AddIngListToRecipe(MyIngredientsList);
/********************************************************************************************************************/
        MyList.add(new DatabaseRecipeCreateObject("Chicken Parmesan", 2,
                "Preheat oven to 350" + ds + "F. Lightly toast the bread, then place toast in blender or food processor and blend. " +
                        "Place parmesan cheese, garlic, and onion powder in blender with bread crumbs and blend well. Cover chicken " +
                        "with bread crumb mixture and place on a lightly greased baking sheet. Bake at 350" + ds + "F for at least 30 minutes (or until internal temp reaches at least 165" + ds + "F. " +
                        "While chicken is baking boil water in a medium saucepan for the spaghetti noodles (they take about 10 - 15 minutes to cook). " +
                        "With 10 minutes left on the chicken, begin heating spaghetti sauce in small or medium saucepan. When chicken is finished " +
                        "cover each chicken breast with a spoonful or two of sauce and place mozzarella on top. Turn off oven and let sit for about 5 minutes for cheese to melt. " +
                        "Mix remaining sauce with noodles and enjoy!", Italian, false));
        AddIngToList(chickenB, 1, lb);
        AddIngListToRecipe(MyIngredientsList);
        //AddIngToList();
        //MyIngredientsList.add(SQLIngredientInsert(1, chickenB, 1, lb));
        //MyIngredientsList.add(SQLIngredientInsert(1, ShreddedParmesean, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(1, S_mozzarella, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(1, bread, 2, slices));
        //MyIngredientsList.add(SQLIngredientInsert(1, mincedGarlic, 2, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(1, spagNoodles, 8, oz));
        //MyIngredientsList.add(SQLIngredientInsert(1, marinaraSpagSauce, 24, oz));
/*******//*************************************************************************************************************/
        //MyList.add(SQLRecipeInsert(2, "Chicken Tortilla Soup", 4,
        //        "Boil Chicken Breasts until tender. Cool Chicken and Shred " +
        //                "or Chop. Combine Soup, Broth, Cream Corn, Frozen Corn, " +
        //                "Garlic Salt, and Rotel in a pot. Add Chicken and slowly stir " +
        //                "in cubed Velveeta Cheese. Stir constantly while Cheese is " +
        //                "melting. \n\nIt will stick if you fail to stir!\n" +
        //                "Great with Tortilla Chips or Fritos\n" +
        //                "This is delicious soup for a cold day!", Mexican, 0));
        //MyIngredientsList.add(SQLIngredientInsert(2, chickenB, 2, lb));
        //MyIngredientsList.add(SQLIngredientInsert(2, chickenBroth, 3, cans));
        //MyIngredientsList.add(SQLIngredientInsert(2, creamOfChicken, 3, cans));
        //MyIngredientsList.add(SQLIngredientInsert(2, rotel, 1, cans));
        //MyIngredientsList.add(SQLIngredientInsert(2, creamOfCorn, 1, cans));
        //MyIngredientsList.add(SQLIngredientInsert(2, frozenCorn, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(2, garlicSalt, 1, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(2, velveeta, 0.75, block));
/*******//*************************************************************************************************************/
        //MyList.add(SQLRecipeInsert(3, "Fried Chicken", 2,
        //        "Potatoes: peel and cut potatoes into cubes. Place potatoes into a medium - large saucepan with enough water to cover the potatoes. Boil potatoes until you can easily smash a piece with a fork.\n\n" +
        //                "Chicken: Heat oil in a large frying pan on medium to medium-high heat. In a bowl beat eggs and milk until well beaten. Mix flour and seasonings and spread on a plate. " +
        //                "Rinse chicken and pat dry. Cut chicken into strips or nuggets. Cover each piece of chicken with the flour mixture, " +
        //                "then dip into egg mixture (being sure all the flour on the chicken is soaked with the egg mixture). Then place the chicken back into the flour covering the piece completely. " +
        //                "Place the chicken in the oil and cook until browned all around. Place chicken in a bowl with paper towels to let cool.\n\n" +
        //                "Gravy: Reduce heat to low to medium-low on oil. Mix just enough flour into oil until the mixture becomes a thick paste. Cook mixture for 5 minutes then add milk and mix constantly. " +
        //                "If mixture begins to clump use a wisk or fork to break up clumps and add more milk. Cook slowly until thickened.", American, 0));
        //MyIngredientsList.add(SQLIngredientInsert(3, chickenB, 1, lb));
        //MyIngredientsList.add(SQLIngredientInsert(3, flour, 2, cups));
        //MyIngredientsList.add(SQLIngredientInsert(3, salt, 2, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(3, pepper, 2, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(3, lemonPepper, 1, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(3, garlicPowder, 2, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(3, eggs, 2, whole));
        //MyIngredientsList.add(SQLIngredientInsert(3, milk, 0.25, cups));
        //MyIngredientsList.add(SQLIngredientInsert(3, vegetableOil, 4, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(3, potatoes, 3, large));
/*******//***************************************************************************************************************/
        //MyList.add(SQLRecipeInsert(4, "Velveeta Shells and Hamburger Skillet", 2,
        //        "BROWN meat in large skillet; drain. Return meat to skillet. " +
        //                "ADD milk, ketchup, mustard and onion powder; mix well. Bring to boil. Stir in Shell Pasta, " +
        //                "return to boil. Cover, simmer on medium-low heat 10 min. or until pasta is tender." +
        //                "STIR in Cheese Sauce and sour cream until blended. Add tomatoes and onions; mix well.", American, 0));
        //MyIngredientsList.add(SQLIngredientInsert(4, groundBeef, 1, lb));
        //MyIngredientsList.add(SQLIngredientInsert(4, milk, 2.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(4, ketchup, 0.25, cups));
        //MyIngredientsList.add(SQLIngredientInsert(4, mustard, 2, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(4, onionPowder, 1, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(4, velveetaShells, 1, box));
        //MyIngredientsList.add(SQLIngredientInsert(4, sourCream, 0.25, cups));
        //MyIngredientsList.add(SQLIngredientInsert(4, tomatoes, 1, large));
        //MyIngredientsList.add(SQLIngredientInsert(4, greenOnions, 0.25, cups));
/*******//*************************************************************************************************************/
        //MyList.add(SQLRecipeInsert(5, "Spaghetti and Meatballs", 2,
        //        "Preheat oven to 350" + ds + "F. Lightly toast the bread, then place toast in blender or food processor and blend. " +
        //                "Place 1/4 cup parmesan cheese, garlic, italian seasoning, and onion powder in blender with bread crumbs and blend well. " +
        //                "Mix milk with bread crumb mixture, next add the sausage and blend, then roll into balls (slightly smaller than a golf ball) and place on a lightly greased baking sheet. " +
        //                "Bake at 350" + ds + "F for at least 30 minutes (or until internal temp reaches at least 165" + ds + "F. " +
        //                "While meatballs are baking boil water in a medium saucepan for the spaghetti noodles (they take about 10 - 15 minutes to cook). " +
        //                "With 10 minutes left on the meatballs, begin heating spaghetti sauce in medium saucepan. When meatballs are finished " +
        //                "place meatballs in sauce to keep them warm and moist until noodles are cooked. " +
        //                "Mix sauce with noodles, sprinkle remaining parmesan cheese and enjoy!", Italian, 0));
        //MyIngredientsList.add(SQLIngredientInsert(5, groundSausage, 1, lb));
        //MyIngredientsList.add(SQLIngredientInsert(5, bread, 2, slices));
        //MyIngredientsList.add(SQLIngredientInsert(5, ShreddedParmesean, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(5, onionPowder, 1.5, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(5, italianSeasoning, 0.5, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(5, mincedGarlic, 1, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(5, spagNoodles, 8, oz));
        //MyIngredientsList.add(SQLIngredientInsert(5, spagSauce, 24, oz));
    /***//*****************************************************************************************************************/
        //MyList.add(SQLRecipeInsert(6, "Burrito Bowls", 4,
        //        "In a medium bowl, toss the rice with the cilantro and the orange zest. Divide the rice evenly among 4 individual serving bowls. " +
        //                "Top with the black beans and half of the pico de gallo. Add warm carnitas and top with the rest of the pico de gallo. " +
        //                "Sprinkle on the cheese, and spoon a dollop of the sour cream on top. Finish with the chopped green onions.\n\n" +
        //                "Easy Pico de Gallo:\n " +
        //                "In a small bowl, mix 1 teaspoon salt with the pinch of sugar. In a medium bowl, toss the tomatoes gently with the salt mixture. " +
        //                "Add onion and jalapeno and combine. Place the avocado on top of the tomato mixture and squeeze with lime juice to coat the avocado. " +
        //                "Sprinkle on the cilantro, and stir to combine. Serve.\n\n" +
        //                "Pork Carnitas:\n" +
        //                "Rinse and dry the pork shoulder. Salt and pepper liberally. Mix the oregano and the cumin with olive oil and rub all over pork. " +
        //                "Place the pork in a slow cooker and top with the onion, garlic, and jalapeno. Squeeze over the juice of the orange and add the two halves. " +
        //                "Cover and cook on low for 8 to 10 hours or on high 4 hours. Once the meat is tender, remove from slow cooker and let cool slightly " +
        //                "before pulling apart with a fork. In a large saute pan, heat the vegetable oil over high heat. " +
        //                "Press the carnitas into the oil and fry until crusty on one side. Serve.", Mexican, 0));
        //MyIngredientsList.add(SQLIngredientInsert(6, whiteRice, 3, cups));
        //MyIngredientsList.add(SQLIngredientInsert(6, cilantro, 2, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, orangeZest, 1, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, blackBeans, 2, cups));
        //MyIngredientsList.add(SQLIngredientInsert(6, S_cheddar, 1, cups));
        //MyIngredientsList.add(SQLIngredientInsert(6, sourCream, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(6, greenOnions, 2, whole));
        //MyIngredientsList.add(SQLIngredientInsert(6, salt, 1, pinch));
		//MyIngredientsList.add(SQLIngredientInsert(6, sugar, 1, pinch));
        //MyIngredientsList.add(SQLIngredientInsert(6, tomatoes, 2, medium));
        //MyIngredientsList.add(SQLIngredientInsert(6, choppedOnion, 0.5, cups));
        //MyIngredientsList.add(SQLIngredientInsert(6, jalapeno, 1, ""));
        //MyIngredientsList.add(SQLIngredientInsert(6, avocado, 1, ""));
        //MyIngredientsList.add(SQLIngredientInsert(6, lime, 1, ""));
        //MyIngredientsList.add(SQLIngredientInsert(6, cilantro, 3, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, porkRoast, 2, lb));
		//MyIngredientsList.add(SQLIngredientInsert(6, salt, 1, pinch));
		//MyIngredientsList.add(SQLIngredientInsert(6, oregano, 2, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, cumin, 1, tsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, oliveOil, 1, tbsp));
        //MyIngredientsList.add(SQLIngredientInsert(6, choppedOnion, 1, small));
        //MyIngredientsList.add(SQLIngredientInsert(6, mincedGarlic, 4, cloves));
        //MyIngredientsList.add(SQLIngredientInsert(6, jalapeno, 1, ""));
        //MyIngredientsList.add(SQLIngredientInsert(6, orange, 1, ""));
        //MyIngredientsList.add(SQLIngredientInsert(6, vegetableOil, 3, tbsp));
	}


}
