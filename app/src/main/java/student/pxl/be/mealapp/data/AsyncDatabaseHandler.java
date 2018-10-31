package student.pxl.be.mealapp.data;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import student.pxl.be.mealapp.MainActivity;
import student.pxl.be.mealapp.domain.Meal;

public class AsyncDatabaseHandler {

    private LocalMealsTableManager tabelManager;

    public void asyncInsert(Meal meal, Context context) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                tabelManager = new LocalMealsTableManager(context);
                tabelManager.addMeal(meal);
                return meal.getId();
            }
        }.execute();
    }

    public void asyncUpdate(Meal meal) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                tabelManager.updateMeal(meal);
                return meal.getId();
            }
        }.execute();
    }

    public void asyncDelete(Meal meal){
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... params){
                int mealId = meal.getId();
                tabelManager.deleteMeal(meal);
                return mealId;
            }
        }.execute();
    }

    public void asyndDeleteAll(){
        //TODO: Delete all async
    }
}
