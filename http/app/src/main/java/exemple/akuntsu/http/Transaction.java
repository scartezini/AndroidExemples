package exemple.akuntsu.http;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Eduardo on 15/02/2016.
 */
public interface Transaction {


    public void doAfter(JSONObject jsonObject);
    public void doError(VolleyError volleyError);
}
