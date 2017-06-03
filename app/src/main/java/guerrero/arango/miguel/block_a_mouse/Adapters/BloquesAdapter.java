package guerrero.arango.miguel.block_a_mouse.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import guerrero.arango.miguel.block_a_mouse.Models.Bloque;
import guerrero.arango.miguel.block_a_mouse.R;

/**
 * Created by Miguel on 23/05/2016.
 */
public class BloquesAdapter extends RecyclerView.Adapter<BloquesAdapter.ViewHolder> {

    ArrayList<Bloque> bloques;

    int contador = 0;

    public BloquesAdapter(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }

    @Override
    public BloquesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloque_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final BloquesAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return bloques.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);

        }
    }
}
