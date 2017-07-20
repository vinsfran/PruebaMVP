package py.com.fuentepy.pruebamvp.mvp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import py.com.fuentepy.pruebamvp.domain.Persona;
import py.com.fuentepy.pruebamvp.network.JsonHttpRequest;
import py.com.fuentepy.pruebamvp.network.PersonaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by vinsfran on 20/07/17.
 */

public class Model implements MVP.ModelImpl {
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private MVP.PresenterImpl presenter;

    public Model(MVP.PresenterImpl presenter){
        this.presenter = presenter;
    }

    @Override
    public void retrievePersonas() {

        RequestParams requestParams = new RequestParams(JsonHttpRequest.METODO_KEY, "10&nat=en");
        asyncHttpClient.get(presenter.getContext(),
                JsonHttpRequest.URI,
                new JsonHttpRequest(presenter));
    }

    @Override
    public void updateEsFavoritoPersona(Persona persona) {
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, "update-favorito-persona" );
        requestParams.put( Persona.ID_KEY, persona.getId() );
        requestParams.put( Persona.EH_FAVORITO_KEY, persona.getGender() );

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }
}
