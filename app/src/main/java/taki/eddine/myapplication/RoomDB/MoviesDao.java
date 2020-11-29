package taki.eddine.myapplication.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


import taki.eddine.myapplication.datamodels.DataModel;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM DataModel ORDER by id")
    List<DataModel> getMovies();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertMovies(DataModel dBdata);

    @Delete
    void deleteMovie(DataModel dBdata);

    @Query("DELETE FROM DataModel")
    void deleteAllMovies();


    ///Clear duplicates
    @Query("DELETE FROM DataModel WHERE id NOT IN (SELECT(id) FROM DataModel GROUP BY title)")
    void deleteDuplicate();
}
