package taki.eddine.myapplication.PaginationUi.PaginationClass.filterpagination;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class FilterViewModel extends ViewModel {

    public MutableLiveData<String> filterTextAll = new MutableLiveData<>();
    public LiveData<PagedList<MoviesDataModel>> listLiveData;
    private Executor executor = Executors.newFixedThreadPool(5);

    public LiveData<PagedList<MoviesDataModel>> getData() {

        listLiveData = Transformations.switchMap(filterTextAll, new Function<String, LiveData<PagedList<MoviesDataModel>>>() {
            @Override
            public LiveData<PagedList<MoviesDataModel>> apply(String input) {
               MyDataSourceFactory myDataSourceFactory = new MyDataSourceFactory(input,executor);

                PagedList.Config config = new PagedList.Config.Builder()
                        .setPageSize(10)
                        .build();
                return new LivePagedListBuilder<Integer, MoviesDataModel>(myDataSourceFactory,config)
                        .setFetchExecutor(executor)
                        .build();
            }
        });
        return listLiveData;
    }

}
