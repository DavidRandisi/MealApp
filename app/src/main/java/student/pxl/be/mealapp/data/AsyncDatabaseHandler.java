package student.pxl.be.mealapp.data;

import android.content.Context;
import android.os.AsyncTask;
import student.pxl.be.mealapp.domain.Meal;

public abstract class AsyncDatabaseHandler {

    private LocalMealsTableManager tabelManager;

    public final void asyncInsert(Meal meal) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                tabelManager.addMeal(meal);
                return meal.getId();
            }
        }.execute();
    }

    public final void asyncUpdate(Meal meal) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                tabelManager.updateMeal(meal);
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
