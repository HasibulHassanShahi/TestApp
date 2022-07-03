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

        Intent intent = getIntent();
        if (intent.getStringExtra("firstName") != null) {
            ListUpdate(intent);
        }

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
                    user.setEmail(userData.optString("email"));
                    user.setPhone(userData.optString("phone"));

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

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("firstName",userModelArrayList.get(position).getFirstName());
        intent.putExtra("lastName",userModelArrayList.get(position).getLastName());
        intent.putExtra("email",userModelArrayList.get(position).getEmail());
        intent.putExtra("phone",userModelArrayList.get(position).getPhone());
        intent.putExtra("position",position);

        startActivity(intent);
    }

    public void ListUpdate(Intent intentSec) {
        int pos = Integer.parseInt(intentSec.getStringExtra("position"));
        String firstname = intentSec.getStringExtra("firstName");
        String lastname = intentSec.getStringExtra("lastName");
        String email = intentSec.getStringExtra("email");
        String phone = intentSec.getStringExtra("phone");

        UserModel user = new UserModel();

        user.setId(firstname);
        user.setFirstName(lastname);
        user.setLastName(email);
        user.setEmail(phone);

        userModelArrayList.set(pos,user);
    }
}