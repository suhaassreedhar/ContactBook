package com.example.suhaas.contactbook.ui.main;

import com.example.suhaas.contactbook.data.DataManager;
import com.example.suhaas.contactbook.data.model.Contacts;
import com.example.suhaas.contactbook.injection.ConfigPersistent;
import com.example.suhaas.contactbook.ui.base.BasePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.id.list;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getContacts(int limit) {
        checkViewAttached();
        getMvpView().showProgress(true);
        Subscription subs = mDataManager.getContactList(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Contacts>>() {
                    @Override
                    public void onSuccess(List<Contacts> contacts) {
                        getMvpView().showProgress(false);
                        getMvpView().showContacts(contacts);
                    }

                    @Override
                    public void onError(Throwable error) {
                        getMvpView().showProgress(false);
                        getMvpView().showError(error);
                    }
                });
        addSubscription(subs);
    }

}