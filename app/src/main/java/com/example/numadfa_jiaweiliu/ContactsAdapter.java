package com.example.numadfa_jiaweiliu;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final List<Contact> contacts;
    private final Context context;

    public ContactsAdapter(List<Contact> contacts, ContactsActivity activity) {
        this.contacts = contacts;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());

        // Click to initiate a call
        holder.itemView.setOnClickListener(v -> {
            if (context instanceof ContactsActivity) {
                ((ContactsActivity) context).callContact(contact.getPhoneNumber());
            }
        });

        // Long-click to show options for edit or delete
        holder.itemView.setOnLongClickListener(v -> {
            showOptionsDialog(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // Show dialog with options to Edit or Delete
    private void showOptionsDialog(int position) {
        CharSequence[] options = {"Edit", "Delete"};

        new AlertDialog.Builder(context)
                .setTitle("Choose Action")
                .setItems(options, (dialog, which) -> {
                    if (context instanceof ContactsActivity) {
                        ContactsActivity activity = (ContactsActivity) context;
                        if (which == 0) {
                            activity.showEditContactDialog(position); // Edit option
                        } else if (which == 1) {
                            activity.deleteContact(position); // Delete option
                        }
                    }
                }).show();
    }

    // ViewHolder for holding item view components
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, phoneTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textName);
            phoneTextView = itemView.findViewById(R.id.textPhone);
        }
    }
}
