package py.com.fuentepy.pruebamvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import py.com.fuentepy.pruebamvp.adapter.PersonaAdapter;
import py.com.fuentepy.pruebamvp.domain.Persona;
import py.com.fuentepy.pruebamvp.mvp.MVP;
import py.com.fuentepy.pruebamvp.mvp.Presenter;
import py.com.fuentepy.pruebamvp.network.PersonaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MVP.ViewImpl {

    private PersonaAdapter adapter;
    private static MVP.PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonaService service = retrofit.create(PersonaService.class);
        Call<Persona> personaCall = service.listPersonas();
        personaCall.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                System.out.println("onResponse: " + response);
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                System.out.println("onFailure");
            }
        });

        if (presenter == null) {
            presenter = new Presenter();
        }
        presenter.setView(this);
        presenter.retrievePersona(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView rvPersonas = (RecyclerView) findViewById(R.id.rv_personas);
        rvPersonas.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvPersonas.setLayoutManager(layoutManager);

        adapter = new PersonaAdapter(this, presenter.getPersonas());
        rvPersonas.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(PERSONAS_KEY, presenter.getPersonas());
        super.onSaveInstanceState(outState);
    }

    public void updateEhFavoritoPersona(Persona persona) {
        presenter.updateEsFavoritoPersona(persona);
    }

    @Override
    public void updateListaRecycler() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateItemRecycler(int posicion) {
        adapter.notifyItemChanged(posicion);
    }

    @Override
    public void showToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(int visibilidad) {
        findViewById(R.id.pb_loading).setVisibility(visibilidad);
    }
}
