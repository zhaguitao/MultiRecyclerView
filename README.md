# MultiRecyclerView

recyclerview with loadmore & show empty,loading,error view  <br />
[![](https://jitpack.io/v/jiang111/MultiRecyclerView.svg)](https://jitpack.io/#jiang111/MultiRecyclerView) <br />


## [demo](https://raw.githubusercontent.com/jiang111/MultiRecyclerView/master/demo.apk)
![art](https://raw.githubusercontent.com/jiang111/MultiRecyclerView/master/art/art.gif)

## Usage:

### gradle
```
Add it in your root build.gradle at the end of repositories:
allprojects {
	repositories {
	            ...
	            maven { url 'https://jitpack.io' }
	}
}
```

```
Add the dependency
compile 'com.github.jiang111:MultiRecyclerView:v1.0'
```


### loadmore
```
recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //TODO...
                recyclerView.loadMoreComplete();
            }
        });
```

### refresh 
any refresh view 

### other Methods
```
recyclerView.setViewState(MultiRecyclerView.ViewState.CONTENT);  //set current state
recyclerView.setLoadMoreEnabled(true); //set loadmore enable
recyclerView.setFooterView(R.layout.loadmore); //custom footer view 
```
```
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
```
<com.jiang.android.multirecyclerview.MultiRecyclerView
        android:layout_width="match_parent"
        android:id="@+id/recyclerview"
        app:emptyView="@layout/empty"
        app:errorView="@layout/error"
        app:loadingView="@layout/loading"
        android:layout_height="match_parent" />
```

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
