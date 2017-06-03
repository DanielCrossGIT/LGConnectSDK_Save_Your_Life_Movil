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
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import guerrero.arango.miguel.block_a_mouse.Actividades.Juego;
import guerrero.arango.miguel.block_a_mouse.JsonHelper;
import guerrero.arango.miguel.block_a_mouse.R;
import guerrero.arango.miguel.block_a_mouse.Singletons.AlertSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;

/**
 * Created by Miguel on 25/09/2016.
 */

public class FinalizadoDialog extends DialogFragment {

    ImageView ivReiniciar,ivHome;
    ConstraintLayout conslayContenedor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        conslayContenedor = (ConstraintLayout) inflater.inflate(R.layout.dialog_finalizado,container,false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);
        int width = getActivity().getResources().getDisplayMetrics().widthPixels;
        int height = getActivity().getResources().getDisplayMetrics().heightPixels;
        width = (int) (width/1.5);
        height = (int) (height/1.5);

        getDialog().getWindow().setLayout(width, height);



        ivReiniciar = (ImageView) conslayContenedor.findViewById(R.id.ivReiniciar);
        ivReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().finish();

                VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.volverJugar(), new ResponseListener<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        getDialog().cancel();
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        //AlertSingleton.getInstance(Juego.this,error.getMessage()).dialog.show();
                    }
                });

            }
        });


        ivHome = (ImageView) conslayContenedor.findViewById(R.id.ivHome);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.close(), new ResponseListener<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        getDialog().cancel();
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        //AlertSingleton.getInstance(Juego.this,error.getMessage()).dialog.show();
                    }
                });
            }
        });

        return conslayContenedor;
    }
}
