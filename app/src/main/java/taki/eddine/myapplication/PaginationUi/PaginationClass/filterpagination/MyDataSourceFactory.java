package taki.eddine.myapplication.PaginationUi.PaginationClass.filterpagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import java.util.concurrent.Executor;

public class MyDataSourceFactory extends DataSource.Factory {
    private String input;
    private MyDataSource filterSource;
    private MutableLiveData<MyDataSource> liveData;
    private Executor executor;

    public MyDataSourceFactory(String input,Executor executor) {
        this.input = input;
        this.executor = executor;
        liveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

       filterSource = new MyDataSource(input,executor);
        liveData.postValue(filterSource);
        return filterSource;
    }

    public MyDataSource getFilterSource() {
        return filterSource;
    }

    public MutableLiveData<MyDataSource> getLiveData() {
        return liveData;
    }
}
