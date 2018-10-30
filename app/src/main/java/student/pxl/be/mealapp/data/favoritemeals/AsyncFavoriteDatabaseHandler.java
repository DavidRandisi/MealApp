package student.pxl.be.mealapp.data.favoritemeals;

import android.os.AsyncTask;

import student.pxl.be.mealapp.data.LocalMealsTableManager;
import student.pxl.be.mealapp.domain.Meal;

public class AsyncFavoriteDatabaseHandler {
    private FavoriteMealsTableManager tabelManager;

    public final void asyncInsert(Meal meal) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                tabelManager.addMeal(meal);
                return meal.getId();
            }
        }.execute();
    }

    public final void asyncDelete(Meal meal){
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                int mealId = meal.getId();
                tabelManager.deleteMeal(meal);
                return mealId;
            }
        }.execute();
    }

    public final void asyndDeleteAll(){
        //TODO: Delete all async
    }
}

