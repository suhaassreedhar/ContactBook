package com.example.suhaas.contactbook.ui.main;


import com.example.suhaas.contactbook.data.model.Contacts;
import com.example.suhaas.contactbook.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showContacts(List<Contacts> contacts);

    void showProgress(boolean show);

    void showError(Throwable error);
}