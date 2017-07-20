package py.com.fuentepy.pruebamvp.network;

import py.com.fuentepy.pruebamvp.domain.Persona;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vinsfran on 20/07/17.
 */

public interface PersonaService {
    //http://api.randomuser.me/?results=10&nat=en
    @GET("pokemon")
    Call<Persona> listPersonas();
}
