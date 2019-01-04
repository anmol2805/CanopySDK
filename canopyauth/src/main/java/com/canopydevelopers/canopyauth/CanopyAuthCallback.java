package com.canopydevelopers.canopyauth;

import com.android.volley.VolleyError;

public interface CanopyAuthCallback {
    public void onLoginSuccess(Boolean loginresponse);
    public void onLoginFailure(String loginerror);
}
