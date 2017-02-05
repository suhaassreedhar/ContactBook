package com.example.suhaas.contactbook.ui.main;


import com.example.suhaas.contactbook.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showContacts(List<String> contacts);

    void showProgress(boolean show);

    void showError(Throwable error);
}