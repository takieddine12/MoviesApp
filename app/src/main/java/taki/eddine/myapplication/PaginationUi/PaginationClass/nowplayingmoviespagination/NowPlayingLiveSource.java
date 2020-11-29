package taki.eddine.myapplication.PaginationUi.PaginationClass.nowplayingmoviespagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class NowPlayingLiveSource extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> listAdapterMutableLiveDat = new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource create() {
        NowPlayingSource nowPlayingSource = new NowPlayingSource();
        listAdapterMutableLiveDat.postValue(nowPlayingSource);

        return nowPlayingSource;
    }

   public  MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> getListAdapterMutableLiveDat() {
        return listAdapterMutableLiveDat;
    }
}
