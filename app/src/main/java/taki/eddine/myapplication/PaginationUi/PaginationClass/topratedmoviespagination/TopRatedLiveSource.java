package taki.eddine.myapplication.PaginationUi.PaginationClass.topratedmoviespagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.PaginationUi.PaginationClass.upcomingmoviessource.UpcomingSource;

public class TopRatedLiveSource extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> listAdapterMutableLiveDat = new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource create() {
        UpcomingSource upcomingSource = new UpcomingSource();
        listAdapterMutableLiveDat.postValue(upcomingSource);

        return upcomingSource;
    }

   public  MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> getListAdapterMutableLiveDat() {
        return listAdapterMutableLiveDat;
    }
}
