package guerrero.arango.miguel.block_a_mouse.Actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.DevicePicker;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.DiscoveryManagerListener;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.WebAppSession;
import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import guerrero.arango.miguel.block_a_mouse.Adapters.TelevisorAdapter;
import guerrero.arango.miguel.block_a_mouse.ConnectionHelper;
import guerrero.arango.miguel.block_a_mouse.Dialogs.FinalizadoDialog;
import guerrero.arango.miguel.block_a_mouse.Dialogs.SalirDialog;
import guerrero.arango.miguel.block_a_mouse.JsonHelper;
import guerrero.arango.miguel.block_a_mouse.Models.Televisor;
import guerrero.arango.miguel.block_a_mouse.R;
import guerrero.arango.miguel.block_a_mouse.Singletons.AlertSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.ProgressSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    ArrayList<ConnectableDevice> dispositivos = new ArrayList<>();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        VariablesGlobales.getInstance().setMainActivity(this);


        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rv);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new TelevisorAdapter(dispositivos, this);
        recycler.setAdapter(adapter);


        DiscoveryManager.getInstance().addListener(new DiscoveryManagerListener() {
            @Override
            public void onDeviceAdded(DiscoveryManager manager, ConnectableDevice device) {
                //AlertSingleton.getInstance(MainActivity.this,"Device Agregado").dialog.show();
                dispositivos.add(device);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDeviceUpdated(DiscoveryManager manager, ConnectableDevice device) {
                //AlertSingleton.getInstance(MainActivity.this,"Device Actualizado").dialog.show();
            }

            @Override
            public void onDeviceRemoved(DiscoveryManager manager, ConnectableDevice device) {
                AlertSingleton.getInstance(MainActivity.this,"Device Removido").dialog.show();
                dispositivos.remove(device);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDiscoveryFailed(DiscoveryManager manager, ServiceCommandError error) {
                AlertSingleton.getInstance(MainActivity.this,"Error de busqueda: "+error.getMessage()).dialog.show();
            }
        });

    }

    /*
     * Este metodo se encarga de conectar el jugador con una TV inicializando el listener y enviando
     * un mensaje de conexion.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(VariablesGlobales.getInstance().getWebAppSession() != null){
            VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.DisconnectPlayer(), new ResponseListener<Object>() {
                @Override
                public void onSuccess(Object object) {
                    finish();
                }

                @Override
                public void onError(ServiceCommandError error) {
                    ProgressSingleton.getInstance(MainActivity.this).pDialog.cancel();
                    AlertSingleton.getInstance(MainActivity.this,error.getMessage()).dialog.show();

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        SalirDialog salirDialog = new SalirDialog();
        salirDialog.show(getSupportFragmentManager(),null);
    }

    public void NextStep(){
        ProgressSingleton.getInstance(MainActivity.this).pDialog.cancel();
        Intent intent = new Intent(MainActivity.this, SeleccionarPersonaje.class);
        MainActivity.this.startActivity(intent);
    }
}
