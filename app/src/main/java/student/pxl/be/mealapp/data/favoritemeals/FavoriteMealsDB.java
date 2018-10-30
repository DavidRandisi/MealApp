package student.pxl.be.mealapp.data.favoritemeals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteMealsDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FacoriteMeals";

    public FavoriteMealsDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITEMEALS_TABLE = "CREATE TABLE FAVORITEMEALS(" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT)"; //TODO: thumbnail from camera
        db.execSQL(CREATE_FAVORITEMEALS_TABLE);
        //TODO: FAVORITEMEALS DB
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS FAVORITEMEALS");

        //create fresh table
        this.onCreate(db);
    }

}
