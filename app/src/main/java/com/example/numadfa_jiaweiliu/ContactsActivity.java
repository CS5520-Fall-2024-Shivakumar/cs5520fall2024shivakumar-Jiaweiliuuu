package com.example.numadfa_jiaweiliu;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private List<Contact> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Initialize the contact list and adapter
        contactsList = new ArrayList<>();
        adapter = new ContactsAdapter(contactsList, this);

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Set up Floating Action Button for adding new contacts
        FloatingActionButton fab = findViewById(R.id.fabAddContact);
        fab.setOnClickListener(view -> showAddContactDialog());
    }

    // Show a dialog to add a new contact
    private void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Contact");

        // Set up the input fields for name and phone number
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
        final EditText inputName = dialogView.findViewById(R.id.input_name);
        final EditText inputPhone = dialogView.findViewById(R.id.input_phone);

        builder.setView(dialogView);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String phone = inputPhone.getText().toString().trim();

            if (!name.isEmpty() && !phone.isEmpty()) {
                Contact newContact = new Contact(name, phone);
                contactsList.add(newContact);
                adapter.notifyDataSetChanged();

                Snackbar.make(recyclerView, "Contact added successfully", Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {
                            contactsList.remove(newContact);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "Contact addition undone", Toast.LENGTH_SHORT).show();
                        })
                        .show();
            } else {
                Toast.makeText(ContactsActivity.this, "Please enter both name and phone number", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // Edit contact details
    public void showEditContactDialog(int position) {
        Contact contact = contactsList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
        final EditText inputName = dialogView.findViewById(R.id.input_name);
        final EditText inputPhone = dialogView.findViewById(R.id.input_phone);
        inputName.setText(contact.getName());
        inputPhone.setText(contact.getPhoneNumber());

        builder.setView(dialogView);
        builder.setPositiveButton("Save", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String phone = inputPhone.getText().toString().trim();

            if (!name.isEmpty() && !phone.isEmpty()) {
                contact.setName(name);
                contact.setPhoneNumber(phone);
                adapter.notifyItemChanged(position);

                Snackbar.make(recyclerView, "Contact updated", Snackbar.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ContactsActivity.this, "Please enter both name and phone number", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // Delete a contact
    public void deleteContact(int position) {
        Contact contact = contactsList.get(position);
        contactsList.remove(position);
        adapter.notifyItemRemoved(position);

        Snackbar.make(recyclerView, "Contact deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", v -> {
                    contactsList.add(position, contact);
                    adapter.notifyItemInserted(position);
                })
                .show();
    }

    // Start a phone call
    public void callContact(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}

