package guerrero.arango.miguel.block_a_mouse.Actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class SeleccionarPersonaje extends AppCompatActivity {
    Button bConectar;
    AppCompatEditText etNombre;
    ImageView ivPersonaje1,ivPersonaje2,ivPersonaje3,ivPersonaje4;
    int positionSelected;
    String nombre = "";



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_personaje);

        //getSupportActionBar().setTitle("Bienvenido al Juego");

        positionSelected = 1;

        ivPersonaje1 = (ImageView) findViewById(R.id.ivPersonaje1);
        ivPersonaje1.setImageResource(R.drawable.p1rojo);
        ivPersonaje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionSelected = 1;
                ivPersonaje1.setImageResource(R.drawable.p1rojo);

                ivPersonaje2.setImageResource(R.drawable.p2);
                ivPersonaje3.setImageResource(R.drawable.p3);
                ivPersonaje4.setImageResource(R.drawable.p4);
            }
        });

        ivPersonaje2 = (ImageView) findViewById(R.id.ivPersonaje2);
        ivPersonaje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionSelected = 2;
                ivPersonaje2.setImageResource(R.drawable.p2rojo);

                ivPersonaje1.setImageResource(R.drawable.p1);
                ivPersonaje3.setImageResource(R.drawable.p3);
                ivPersonaje4.setImageResource(R.drawable.p4);

            }
        });

        ivPersonaje3 = (ImageView) findViewById(R.id.ivPersonaje3);
        ivPersonaje3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionSelected = 3;
                ivPersonaje3.setImageResource(R.drawable.p3rojo);

                ivPersonaje1.setImageResource(R.drawable.p1);
                ivPersonaje2.setImageResource(R.drawable.p2);
                ivPersonaje4.setImageResource(R.drawable.p4);

            }
        });

        ivPersonaje4 = (ImageView) findViewById(R.id.ivPersonaje4);
        ivPersonaje4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionSelected = 4;
                ivPersonaje4.setImageResource(R.drawable.p4rojo);

                ivPersonaje1.setImageResource(R.drawable.p1);
                ivPersonaje2.setImageResource(R.drawable.p2);
                ivPersonaje3.setImageResource(R.drawable.p3);

            }
        });




        VariablesGlobales.getInstance().setSeleccionarPersonaje(this);




        etNombre = (AppCompatEditText) findViewById(R.id.etNombre);
        etNombre.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        bConectar = (Button) findViewById(R.id.bConectar);
        bConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentarConectar();
            }
        });
    }

    private void intentarConectar(){
        etNombre.setError(null);
        String nombre = etNombre.getText().toString();
        boolean cancelar = false;
        View focusView = null;

        if(nombre.length() < 3){
            //etNombre.setError("Debe tener almenos 3 caracteres");
            Toast.makeText(SeleccionarPersonaje.this, "Debe contener almenos 3 caracteres",Toast.LENGTH_SHORT).show();
            focusView = etNombre;
            cancelar = true;
        }   else if(nombre.length() > 7){
            //etNombre.setError("Debe tener menos de 7 caracteres");
            Toast.makeText(SeleccionarPersonaje.this, "Debe contener menos de 7 caracteres",Toast.LENGTH_SHORT).show();
            focusView = etNombre;
            cancelar = true;
        }

        if(cancelar){
            focusView.requestFocus();
        }   else{
            conectar(nombre.toUpperCase(), positionSelected);
        }
    }

    private void conectar(String nombre, int avatar){
        ProgressSingleton.getInstance(SeleccionarPersonaje.this).pDialog.show();
        this.nombre = nombre;

        VariablesGlobales.getInstance().getWebAppSession().sendMessage(JsonHelper.ConnectPlayer(nombre, avatar), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object object) {
                //ProgressSingleton.getInstance(SeleccionarPersonaje.this).pDialog.cancel();


            }
            @Override
            public void onError(ServiceCommandError error) {
                ProgressSingleton.getInstance(SeleccionarPersonaje.this).pDialog.cancel();
                AlertSingleton.getInstance(SeleccionarPersonaje.this, error.getMessage()).dialog.show();

            }
        });
        //Toast.makeText(this,"Nombre validado",Toast.LENGTH_SHORT).show();
    }

    public void NextStep(boolean resultado){
        ProgressSingleton.getInstance(SeleccionarPersonaje.this).pDialog.cancel();
        Intent intent = new Intent(this, EsperaJugadores.class);
        intent.putExtra("resultado",resultado);

        VariablesGlobales.getInstance().setAvatar(positionSelected);
        intent.putExtra("nombre",nombre);
        startActivity(intent);

    }

    public void NoNextStep(){
        ProgressSingleton.getInstance(SeleccionarPersonaje.this).pDialog.cancel();
    }



}
