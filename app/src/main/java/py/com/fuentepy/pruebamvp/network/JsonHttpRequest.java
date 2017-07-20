package py.com.fuentepy.pruebamvp.network;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import py.com.fuentepy.pruebamvp.domain.Persona;
import py.com.fuentepy.pruebamvp.mvp.MVP;
import cz.msebera.android.httpclient.Header;

/**
 * Created by vinsfran on 20/07/17.
 */

public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://api.randomuser.me/";
    public static final String METODO_KEY = "?results";

    private MVP.PresenterImpl presenter;

    public JsonHttpRequest(MVP.PresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        presenter.showProgressBar(true);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Gson gson = new Gson();
        Persona persona = gson.fromJson(response.toString(), Persona.class);
        presenter.updateItemRecycler(persona);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Persona> personas = new ArrayList<>();
        Persona persona;

        for (int i = 0; i < response.length(); i++) {
            try {
                persona = gson.fromJson(response.getJSONObject(i).toString(), Persona.class);
                personas.add(persona);
            } catch (JSONException e) {
            }
        }
        presenter.updateListaRecycler(personas);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        presenter.showToast(responseString);
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar(false);
    }
}
