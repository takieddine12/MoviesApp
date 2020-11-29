package taki.eddine.myapplication.PaginationUi.PaginationClass;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<MoviesDataModel>> listLiveData;
    public MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> liveData;


    public ItemViewModel() {
        ItemDataSourcFactory itemDataSourcFactory = new ItemDataSourcFactory();
        liveData = itemDataSourcFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();
       listLiveData = new LivePagedListBuilder(itemDataSourcFactory,config).build();


    }

    public LiveData<PagedList<MoviesDataModel>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
