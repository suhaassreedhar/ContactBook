package com.example.suhaas.contactbook.ui.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suhaas.contactbook.R;
import com.example.suhaas.contactbook.data.model.Contacts;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private List<Contacts> mContacts;
    private ClickListener mClickListener;

    @Inject
    public ContactListAdapter() {
        mContacts = Collections.emptyList();
    }

    public void setContacts(List<Contacts> contacts) {
        mContacts = contacts;
    }

    public void setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_contact_list, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contacts contacts = mContacts.get(position);
        holder.mContacts = contacts;
        holder.nameText.setText(String.format("%s %s"
                , contacts.getFirstName().substring(0).toUpperCase(), contacts.getLastName().substring(0).toUpperCase()));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public interface ClickListener {
        void onContactClick(Contacts contacts);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        Contacts mContacts;
        @BindView(R.id.text_name)
        TextView nameText;

        ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            nameText = (TextView) itemView.findViewById(R.id.text_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) mClickListener.onContactClick(mContacts);
                }
            });
        }
    }

}