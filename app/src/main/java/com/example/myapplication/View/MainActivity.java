package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Adapter.ContactAdapter;
import com.example.myapplication.Controller.ServiceController;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contactRecycle);

        try {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

            try {
                JSONObject jsonObject = new JSONObject(ServiceController.GetData(getApplicationContext()));
                JSONArray jsonArray = jsonObject.getJSONArray("users");
                for (int i =0; i<jsonArray.length(); i++){
                    JSONObject userData = jsonArray.getJSONObject(i);
                    UserModel user = new UserModel();

                    user.setId(userData.getString("id"));
                    user.setFirstName(userData.getString("firstName"));
                    user.setLastName(userData.getString("lastName"));
                    user.setEmail(userData.getString("email"));
                    user.setPhone(userData.getString("phone"));

                    userModelArrayList.add(user);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            contactAdapter = new ContactAdapter(getApplicationContext(),userModelArrayList,this);

            recyclerView.setAdapter(contactAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnItemClick(int position) {
        UserModel user = new UserModel();

        user.setId(userModelArrayList.get(position).getId());
        user.setFirstName(userModelArrayList.get(position).getFirstName());
        user.setLastName(userModelArrayList.get(position).getLastName());
        user.setEmail(userModelArrayList.get(position).getEmail());
        user.setPhone(userModelArrayList.get(position).getPhone());

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}