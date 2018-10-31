package student.pxl.be.mealapp.domain;

import android.content.Context;

import student.pxl.be.mealapp.data.AsyncDatabaseHandler;

public class MealController {

    private final Context appContext;
    private final AsyncDatabaseHandler asyncDatabaseHandler;

    public MealController(Context context){
        appContext = context.getApplicationContext();
        asyncDatabaseHandler = new AsyncDatabaseHandler() { //TODO: not sure why we have to initialize this
        };
    }

    public void addMeal(Meal meal) {
        asyncDatabaseHandler.asyncInsert(meal, appContext);
    }

}
