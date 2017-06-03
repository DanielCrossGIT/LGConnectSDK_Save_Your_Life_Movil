package guerrero.arango.miguel.block_a_mouse.Actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import guerrero.arango.miguel.block_a_mouse.JsonHelper;
import guerrero.arango.miguel.block_a_mouse.R;
import guerrero.arango.miguel.block_a_mouse.Singletons.AlertSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.ProgressSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Miguel on 25/09/2016.
 */

public class EsperaJugadores extends AppCompatActivity {

    ImageView ivIniciar;
    TextView tvNombre;
    ImageView ivCreditos;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_jugadores);
        VariablesGlobales.getInstance().setEsperaJugadores(this);

        ivCreditos = (ImageView) findViewById(R.id.ivCreditos);
        ivCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.showAbout(), new ResponseListener<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        //finish();
                    }

                    @Override
                    public void onError(ServiceCommandError error) {

                    }
                });
            }
        });


        tvNombre= (TextView) findViewById(R.id.tvNombre);
        ivIniciar = (ImageView) findViewById(R.id.ivIniciar);
        boolean resultado = getIntent().getBooleanExtra("resultado",false);
        if(resultado){
            ivIniciar.setEnabled(true);
            ivIniciar.setImageResource(R.drawable.boton_inicio);
        }   else{
            ivIniciar.setEnabled(false);
            ivIniciar.setImageResource(R.drawable.boton_inicio_unabled);
        }

        //bIniciar.setVisibility(View.GONE);
        ivIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressSingleton.getInstance(EsperaJugadores.this).pDialog.show();

                VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.requestGameStart(), new ResponseListener<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        ProgressSingleton.getInstance(EsperaJugadores.this).pDialog.cancel();
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        ProgressSingleton.getInstance(EsperaJugadores.this).pDialog.cancel();
                        AlertSingleton.getInstance(EsperaJugadores.this,error.getMessage()).dialog.show();
                    }
                });
            }
        });

        String nombre = getIntent().getExtras().getString("nombre");
        tvNombre.setText(nombre);
        //getSupportActionBar().setTitle(nombre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_info){

            VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.showAbout(), new ResponseListener<Object>() {
                @Override
                public void onSuccess(Object object) {
                    //finish();
                }

                @Override
                public void onError(ServiceCommandError error) {

                }
            });

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VariablesGlobales.getInstance().setEsperaJugadores(null);
    }

    public void NextStep(boolean turno){
        //ProgressSingleton.getInstance(EsperaJugadores.this).pDialog.cancel();
        Intent intent = new Intent(this, Juego.class);
        intent.putExtra("turno",turno);
        startActivity(intent);
    }
    public void desactivarBoton(){
        if(ivIniciar.getVisibility() == View.GONE){
            ivIniciar.setVisibility(View.VISIBLE);
        }
        ivIniciar.setEnabled(false);
        ivIniciar.setImageResource(R.drawable.boton_inicio_unabled);
    }

    public void activarBoton(){
        if(ivIniciar.getVisibility() == View.GONE){
            ivIniciar.setVisibility(View.VISIBLE);
        }
        ivIniciar.setEnabled(true);
        ivIniciar.setImageResource(R.drawable.boton_inicio);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.DisconnectPlayer(), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object object) {
                finish();
            }

            @Override
            public void onError(ServiceCommandError error) {
                ProgressSingleton.getInstance(EsperaJugadores.this).pDialog.cancel();
                AlertSingleton.getInstance(EsperaJugadores.this,error.getMessage()).dialog.show();
            }
        });

    }

    public void JuegoCancelado(){
        finish();
    }
}
