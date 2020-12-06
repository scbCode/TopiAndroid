package com.test.topi.network;

import com.android.volley.VolleyError;
import org.json.JSONObject;

public interface ModelManagerListener {

	public void onError(VolleyError error);
	public void onSuccess(JSONObject json);
}
