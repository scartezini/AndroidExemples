package exemple.akuntsu.http;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Eduardo on 15/02/2016.
 */
public class Server {

    private static final String TAG = "Server";

    private Context mContext;
    private RequestQueue mRequestQueue;

    private Server(Context context){
        this.mContext = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if( mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }


    public void execute(final Transaction transaction, String tag, String header, HashMap<String,String> params){
        Gson gson = new Gson();


        final CustomRequestJsonObject request = new CustomRequestJsonObject(
                Request.Method.POST,
                ServerConfig.URL + header,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "String" + response);
                        transaction.doAfter(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d(TAG,"onErrorResponse(): " + volleyError);
                        transaction.doAfter(null);
                    }
                }
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy());

        addRequestQueue(request);

    }


    public <T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
