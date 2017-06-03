package guerrero.arango.miguel.block_a_mouse.Dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import guerrero.arango.miguel.block_a_mouse.R;

/**
 * Created by Miguel on 25/09/2016.
 */

public class SalirDialog extends DialogFragment {


    ImageView ivSalir, ivCancelar;
    ConstraintLayout rl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rl = (ConstraintLayout) inflater.inflate(R.layout.dialog_salir,container,false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ivSalir = (ImageView) rl.findViewById(R.id.ivSalir);
        ivSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();

            }
        });


        ivCancelar = (ImageView) rl.findViewById(R.id.ivCancelar);
        ivCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });

        return rl;
    }
}
