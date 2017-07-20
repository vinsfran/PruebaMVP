package py.com.fuentepy.pruebamvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import py.com.fuentepy.pruebamvp.domain.Persona;

/**
 * Created by vinsfran on 20/07/17.
 */

public class Presenter implements MVP.PresenterImpl {
    private MVP.ModelImpl model;
    private MVP.ViewImpl view;
    private ArrayList<Persona> personas = new ArrayList<>();

    public Presenter() {
        this.model = new Model(this);
    }

    @Override
    public void setView(MVP.ViewImpl view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void updateListaRecycler(ArrayList<Persona> personas) {
        this.personas.clear();
        this.personas.addAll(personas);
        view.updateListaRecycler();
    }

    @Override
    public void updateItemRecycler(Persona persona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId() == persona.getId()) {
                personas.get(i).setGender(persona.getGender());
                view.updateItemRecycler(i);
                break;
            }
        }
    }

    @Override
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    @Override
    public void retrievePersona(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            personas = savedInstanceState.getParcelableArrayList(MVP.ViewImpl.PERSONAS_KEY);
            return;
        }
        model.retrievePersonas();
    }

    @Override
    public void updateEsFavoritoPersona(Persona persona) {

    }

    @Override
    public void showToast(String mensaje) {
        view.showToast(mensaje);
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidad = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidad);
    }
}
