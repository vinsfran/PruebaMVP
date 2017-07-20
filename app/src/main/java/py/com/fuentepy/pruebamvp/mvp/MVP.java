package py.com.fuentepy.pruebamvp.mvp;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import py.com.fuentepy.pruebamvp.domain.Persona;

/**
 * Created by vinsfran on 20/07/17.
 */

public interface MVP {
    interface ModelImpl{
        public void retrievePersonas();
        public void updateEsFavoritoPersona(Persona persona);
    }

    interface PresenterImpl{
        public void retrievePersona(Bundle savedInstanceState);
        public void updateEsFavoritoPersona(Persona persona);
        public void showToast(String mensaje);
        public void showProgressBar(boolean status);
        public void setView(MVP.ViewImpl view);
        public Context getContext();
        public void updateListaRecycler(ArrayList<Persona> personas);
        public void updateItemRecycler(Persona persona);
        public ArrayList<Persona> getPersonas();
    }

    interface ViewImpl{
        final String PERSONAS_KEY = "personas";

        public void showToast(String mensaje);
        public void showProgressBar(int visibilidad);
        public void updateListaRecycler();
        public void updateItemRecycler(int posicion);
    }
}
