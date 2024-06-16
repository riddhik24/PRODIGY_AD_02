package com.example.to_dolist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemAdapter;
    private Button button;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView=findViewById(R.id.listView);
        button=findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        items=new ArrayList<>();
        itemAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(itemAdapter);
        setupListViewListener();

    }

    private void setupListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Context context =getApplicationContext();
                Toast.makeText(context, "Task Removed", Toast.LENGTH_SHORT).show();
                items.remove(position);
                itemAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }

    private void addItem(View v) {
        EditText input=findViewById(R.id.ed1);
        String itemText=input.getText().toString();

        if(!(itemText.isEmpty())){
            itemAdapter.add(itemText);
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Please Enter Task!", Toast.LENGTH_SHORT).show();
        }
    }

}