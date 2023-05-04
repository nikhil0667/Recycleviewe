package com.example.recycleviewe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrConteact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btninsert = findViewById(R.id.btnDialog);
        RecyclerView recyclerView = findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrConteact.add(new ContactModel(R.drawable.img, "Nikhil", "9825684774"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nayan", "123456789"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nik", "9825684774"));
        arrConteact.add(new ContactModel(R.drawable.img, "Na", "123456789"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nikhil", "9825684774"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nayan", "123456789"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nik", "9825684774"));
        arrConteact.add(new ContactModel(R.drawable.img, "Na", "123456789"));
        arrConteact.add(new ContactModel(R.drawable.img, "Nikhil", "9825684774"));
        RecyclerContectAdeapter adapter = new RecyclerContectAdeapter(this, arrConteact);
        recyclerView.setAdapter(adapter);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_dialog);
                EditText edtname = dialog.findViewById(R.id.edt_name);
                EditText edtnumber = dialog.findViewById(R.id.edt_number);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                            name = edtname.getText().toString();
                           number = edtnumber.getText().toString();

                        arrConteact.add(new ContactModel(R.drawable.img, name, number));
                        adapter.notifyItemInserted(arrConteact.size() - 1);
                        recyclerView.scrollToPosition(arrConteact.size() - 1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}