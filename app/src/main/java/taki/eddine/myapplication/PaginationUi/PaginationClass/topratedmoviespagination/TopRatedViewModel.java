package taki.eddine.myapplication.PaginationUi.PaginationClass.topratedmoviespagination;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class TopRatedViewModel extends ViewModel {

    public LiveData<PagedList<MoviesDataModel>> liveData;
    private MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>>  mutableLiveData;

    public TopRatedViewModel()
    {
        TopRatedLiveSource topRatedLiveSource = new TopRatedLiveSource();
        mutableLiveData = topRatedLiveSource.getListAdapterMutableLiveDat();


        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build();

        liveData = new LivePagedListBuilder(topRatedLiveSource,config).build();

    }
}
