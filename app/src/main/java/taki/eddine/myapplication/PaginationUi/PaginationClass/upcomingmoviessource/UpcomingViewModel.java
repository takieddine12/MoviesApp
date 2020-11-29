package taki.eddine.myapplication.PaginationUi.PaginationClass.upcomingmoviessource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class UpcomingViewModel extends ViewModel {

    public LiveData<PagedList<MoviesDataModel>> liveData;
    private MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>>  mutableLiveData;

    public UpcomingViewModel()
    {
        UpcomingLiveSource upcomingLiveSource  = new UpcomingLiveSource();
        mutableLiveData = upcomingLiveSource.getListAdapterMutableLiveDat();


        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build();

        liveData = new LivePagedListBuilder(upcomingLiveSource,config).build();

    }
}
