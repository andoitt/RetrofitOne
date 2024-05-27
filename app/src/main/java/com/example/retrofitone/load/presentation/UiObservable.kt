package com.example.retrofitone.load.presentation


interface UpdateUi {

    fun updateUiState(uiState: LoadUiState)
}

interface UpdateObserver {

    fun updateObserver(observer: (LoadUiState) -> Unit)

    fun clearObserver()
}


interface UiObservable : UpdateObserver, UpdateUi {

    class Base : UiObservable {

        private var observerShowUi: ((LoadUiState) -> Unit)? = null
        private var cacheUiState: LoadUiState? = null

        override fun updateUiState(uiState: LoadUiState) {
            if (observerShowUi != null) {
                observerShowUi!!.invoke(uiState)
            } else {
                cacheUiState = uiState
            }
        }

        override fun updateObserver(observer: (LoadUiState) -> Unit) {
            observerShowUi = observer
            if (cacheUiState != null) {
                observer.invoke(cacheUiState!!)
                cacheUiState = null
            }
        }

        override fun clearObserver() {
            observerShowUi = null
        }
    }
}