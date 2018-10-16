package student.pxl.be.mealapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import student.pxl.be.mealapp.domain.Meal;

public abstract class LocalMealsTableManager extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Meals";
    private static final String TABLE_LOCALMEALS = "LOCALMEALS";
    private static final String KEY_DESCRIPTION = "description"; //TODO: Add description for local meals
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_INGREDIENTS, KEY_THUMBNAIL};
    private SQLiteDatabase db;

    public LocalMealsTableManager(Context context){
        //TODO: Make sure that MealsDB is only created once
        super(context, DATABASE_NAME, null, 1);
    }

    public void addMeal(Meal meal){
        Log.d("addMeal", meal.toString());
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meal.getTitle());
        values.put(KEY_INGREDIENTS, meal.getIngredients());
        values.put(KEY_THUMBNAIL, meal.getThumbnail());

        db.insert(TABLE_LOCALMEALS, null, values);
        db.close();
    }

    public Meal getMeal(int id) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCALMEALS,
                COLUMNS, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Meal meal = new Meal();
        meal.setId(cursor.getInt(0));
        meal.setTitle(cursor.getString(1));
        meal.setIngredients(cursor.getString(2));
        meal.setThumbnail(cursor.getString(3));

        db.close();
        return meal;
    }
}
