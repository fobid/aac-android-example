package com.fobid.aac.viewmodels

import android.arch.lifecycle.*
import android.util.Log
import com.fobid.aac.models.User
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MyPageViewModel : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val name = MutableLiveData<String>()
    private val email = MutableLiveData<String>()

    private fun fetchUser() {
        Log.d("MyPageViewModel", "fetchUser: ")
        disposables.add(
                Flowable.just(User("james", "fobidlim@gmail.com"))
                        .delay(1500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribe { user ->
                            name.postValue(user.name)
                            email.postValue(user.email)
                        }
        )
    }

    fun name(): LiveData<String> {
        return name
    }

    fun email(): LiveData<String> {
        return email
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d("MyPageViewModel", "onResume: ")
        fetchUser()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("MyPageViewModel", "onPause: ")
        disposables.clear()
    }
}