package taki.eddine.myapplication.RoomDB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import taki.eddine.myapplication.datamodels.DataModel;

@Database(entities = {DataModel.class},version = 1,exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MoviesDao moviesDao();
    public  static MoviesDatabase moviesDatabase;


    public static synchronized MoviesDatabase getInstance(Context context) {
        if(moviesDatabase == null)
        {
            moviesDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MoviesDatabase.class,"movies.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return moviesDatabase;
    }
}
