package com.example.suhaas.contactbook.ui.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suhaas.contactbook.R;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private List<String> mContacts;
    private ClickListener mClickListener;

    @Inject
    public ContactListAdapter() {
        mContacts = Collections.emptyList();
    }

    public void setContacts(List<String> contacts) {
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
        String contacts = mContacts.get(position);
        holder.mContacts = contacts;
        holder.nameText.setText(String.format("%s%s"
                , contacts.substring(0, 1).toUpperCase(), contacts.substring(1)));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public interface ClickListener {
        void onContactClick(String contacts);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        String mContacts;
        @BindView(R.id.text_name)
        TextView nameText;

        ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) mClickListener.onContactClick(mContacts);
                }
            });
        }
    }

}