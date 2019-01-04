package com.canopydevelopers.canopyauth;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.canopydevelopers.mysingleton.Mysingleton;
import org.json.JSONException;
import org.json.JSONObject;

public class CanopyLogin {
    private CanopyAuthCallback canopyAuthCallback = null;
    private Context context;

    public CanopyLogin(CanopyAuthCallback canopyAuthCallback, Context context) {
        this.canopyAuthCallback = canopyAuthCallback;
        this.context = context;
    }

    public void generate_token(String student_id,
                               String password,
                               String url
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
                credentials, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("com.canopydevelopers.canopyauth", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("authtoken", response.getString("token"));
                    editor.putString("refreshtoken", response.getJSONObject("refreshtoken").getString("refreshtoken"));
                    if (editor.commit()) {
                        AuthConfig authConfig = new AuthConfig(context);
                        if (authConfig.writeloginstatus(true)) {
                            if (canopyAuthCallback != null) {
                                canopyAuthCallback.onLoginSuccess(true);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(canopyAuthCallback!=null){
                    if(error.toString().contains("ServerError")){
                        canopyAuthCallback.onLoginFailure("Password is incorrect");
                    }
                    else if(error.toString().contains("NoConnectionError")){
                        canopyAuthCallback.onLoginFailure("Please check your network connection");
                    }
                    else{
                        canopyAuthCallback.onLoginFailure("Server not responding");
                    }

                }
            }
        });
        Mysingleton.getInstance(context).addToRequestqueue(loginRequest);
    }
}
