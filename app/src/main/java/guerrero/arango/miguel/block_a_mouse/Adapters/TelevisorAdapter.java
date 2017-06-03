package guerrero.arango.miguel.block_a_mouse.Adapters;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import java.util.ArrayList;

import guerrero.arango.miguel.block_a_mouse.Actividades.SeleccionarPersonaje;
import guerrero.arango.miguel.block_a_mouse.JsonHelper;
import guerrero.arango.miguel.block_a_mouse.Listeners.DesaplgListener;
import guerrero.arango.miguel.block_a_mouse.R;
import guerrero.arango.miguel.block_a_mouse.Singletons.AlertSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.ProgressSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;

/**
 * Created by Miguel on 25/09/2016.
 */

public class TelevisorAdapter extends RecyclerView.Adapter<TelevisorAdapter.ViewHolder> {


    ArrayList<ConnectableDevice> dispositivos;
    Activity activity;

    public TelevisorAdapter(ArrayList<ConnectableDevice> dispositivos, Activity activity) {
        this.dispositivos = dispositivos;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_televisor, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvNombre.setText(dispositivos.get(position).getFriendlyName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressSingleton.getInstance(activity).pDialog.show();

                try{

                    WebOSTVService webOSTVService = (WebOSTVService) dispositivos.get(position).getServiceByName(WebOSTVService.ID);
                    webOSTVService.connect();
                    webOSTVService.launchWebApp(VariablesGlobales.getInstance().getWEBAPP(), new WebAppSession.LaunchListener() {
                        @Override
                        public void onSuccess(WebAppSession object) {
                            VariablesGlobales.getInstance().setWebAppSession(object);
                            VariablesGlobales.getInstance().setDesaplgListener(new DesaplgListener(activity.getApplicationContext()));
                            VariablesGlobales.getInstance().getWebAppSession().setWebAppSessionListener(VariablesGlobales.getInstance().getDesaplgListener());

                            try{
                                object.sendMessage(JsonHelper.ConnectTv(), new ResponseListener<Object>() {
                                    @Override
                                    public void onSuccess(Object object) {
                                        //ProgressSingleton.getInstance(activity).pDialog.cancel();

                                /*
                                Toast.makeText(activity,"CONECTAR AL TELEVISOR: "+dispositivos.get(position).getFriendlyName(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity, SeleccionarPersonaje.class);
                                activity.startActivity(intent);*/

                                    }

                                    @Override
                                    public void onError(ServiceCommandError error) {
                                        ProgressSingleton.getInstance(activity).pDialog.cancel();
                                        AlertSingleton.getInstance(activity,error.getMessage()).dialog.show();

                                    }
                                });

                            }   catch (Exception e){
                                AlertSingleton.getInstance(activity, "La TV seleccionada no es compatible").dialog.show();
                            }



                        }

                        @Override
                        public void onError(ServiceCommandError error) {
                            ProgressSingleton.getInstance(activity).pDialog.cancel();
                            AlertSingleton.getInstance(activity,error.getMessage()).dialog.show();
                        }
                    });

                }   catch (Exception e){
                    e.printStackTrace();
                    ProgressSingleton.getInstance(activity).pDialog.cancel();
                    Toast.makeText(activity, "La TV seleccionada no es vàlida",Toast.LENGTH_SHORT).show();

                }





            }
        });


    }

    @Override
    public int getItemCount() {
        return dispositivos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView tvNombre;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
        }
    }
}
