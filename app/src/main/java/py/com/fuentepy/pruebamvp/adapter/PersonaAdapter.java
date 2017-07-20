package py.com.fuentepy.pruebamvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import py.com.fuentepy.pruebamvp.MainActivity;
import py.com.fuentepy.pruebamvp.R;
import py.com.fuentepy.pruebamvp.domain.Persona;

/**
 * Created by vinsfran on 20/07/17.
 */

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder>{

    private MainActivity activity;
    private ArrayList<Persona> personas;


    public PersonaAdapter( MainActivity activity, ArrayList<Persona> personas ){
        this.activity = activity;
        this.personas = personas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate(R.layout.item_persona, parent, false);
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setDados( personas.get( position ) );
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ivGener;
        private TextView tvMail;

        private ViewHolder(View itemView) {
            super(itemView);

            ivGener = (TextView) itemView.findViewById(R.id.tv_gener);
            tvMail = (TextView) itemView.findViewById(R.id.tv_mail);

        }

        private void setDados(Persona persona) {
//            Picasso.with( ivMoto.getContext() )
//                    .load( moto.getImagem() )
//                    .into( ivMoto );

            ivGener.setText(persona.getGender());
            tvMail.setText(persona.getMail());
        }

        @Override
        public void onClick(View view) {
            //activity.updateEhFavoritoMoto( motos.get( getAdapterPosition() ) );
        }
    }
}
