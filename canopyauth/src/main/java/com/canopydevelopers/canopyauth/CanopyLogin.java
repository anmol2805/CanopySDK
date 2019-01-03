package com.canopydevelopers.canopyauth;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.canopydevelopers.mysingleton.Mysingleton;
import org.json.JSONException;
import org.json.JSONObject;

public class CanopyLogin {
    public void generate_token(String student_id,
                                         String password,
                                         String url,
                                         Context context,
                                         Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errlsn
    ){
        JSONObject credentials = new JSONObject();
        try {
            credentials.put("username",student_id);
            credentials.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                credentials, listener, errlsn);
        Mysingleton.getInstance(context).addToRequestqueue(loginRequest);
    }
}
