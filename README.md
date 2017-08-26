# MultiRecyclerView

recyclerview with loadmore & show empty,loading,error view  <br />



## [Demo](https://raw.githubusercontent.com/jiang111/MultiRecyclerView/master/demo.apk)
![art](https://raw.githubusercontent.com/jiang111/MultiRecyclerView/master/art/art.gif)

## Usage:

### initRecyclerview
```java
recyclerview.config(layoutmanager,adapter);
```

### Loadmore
```java
recyclerView.setLoadMoreEnabled(true);
recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //TODO...
                recyclerView.loadMoreComplete();
            }
        });
```

### Refresh 
any refresh view 

### ViewState
```java
recyclerView.setViewState(MultiRecyclerView.ViewState.CONTENT);
```


### Other Methods
```java
recyclerView.setFooterLoadingView(R.layout.loadmore); 
```

### Other ViewState click event config
```java
//other state control
recyclerView.setOtherStateBindListener(new OtherStateBindImpl() {
                    @Override
                    public void onBindView(BaseViewHolder holder, MultiRecyclerView.ViewState currentState) {
                        switch (currentState){
                            case EMPTY:
                                //TODO...
                                break;
                            case ERROR:
                                //TODO...
                                break;
                            case LOADING:
                                //TODO...
                                break;
                        }
                    }

                    @Override
                    public boolean clickable() {
                        return true;
                    }

                    @Override
                    public void onItemClick(View v, MultiRecyclerView.ViewState mViewState) {
                        //TODO...
                    }
                });
```

### Layout
```xml
<com.jiang.android.multirecyclerview.MultiRecyclerView
        android:layout_width="match_parent"
        android:id="@+id/recyclerview"
        app:emptyView="@layout/empty"
        app:errorView="@layout/error"
        app:loadingView="@layout/loading"
        app:footer_loading_view="@layout/footer_loading"
        android:layout_height="match_parent" />
```

## attention
if you want to use the ViewState in MultiRecyclerView,we'd better not use wrap_content for MultiRecyclerView


### License

    Copyright 2016 NewTab

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
