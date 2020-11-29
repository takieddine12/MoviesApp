package taki.eddine.myapplication.PaginationUi.PaginationClass;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import taki.eddine.myapplication.datamodels.MoviesDataModel;

public class ItemDataSourcFactory extends DataSource.Factory<Integer, MoviesDataModel> {

    private MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> mutableLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {

        ItemDataSource itemDataSource = new ItemDataSource();

        mutableLiveData.postValue(itemDataSource);

        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MoviesDataModel>> getMutableLiveData() {
        return mutableLiveData;
    }
}
