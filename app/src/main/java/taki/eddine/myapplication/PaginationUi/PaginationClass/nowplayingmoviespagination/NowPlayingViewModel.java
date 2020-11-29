package taki.eddine.myapplication.PaginationUi.PaginationClass.nowplayingmoviespagination;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class NowPlayingViewModel extends ViewModel {

    public LiveData<PagedList<MoviesDataModel>> liveData;

    public NowPlayingViewModel() {
        NowPlayingLiveSource topRatedLiveSource = new NowPlayingLiveSource();
        MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> mutableLiveData =
                topRatedLiveSource.getListAdapterMutableLiveDat();


        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build();

        liveData = new LivePagedListBuilder(topRatedLiveSource,config).build();

    }
}
