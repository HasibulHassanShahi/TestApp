package com.example.myapplication.Controller;

import android.content.Context;

import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;

import java.io.InputStream;
import java.util.ArrayList;

public class ServiceController {
    public static String GetData(Context context){
        String json = null;

        try {
            InputStream in = context.getResources().openRawResource(R.raw.data);
            int sizeOfFile = in.available();
            byte[] bufferData = new byte[sizeOfFile];
            in.read(bufferData);
            in.close();
            json = new String(bufferData, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
