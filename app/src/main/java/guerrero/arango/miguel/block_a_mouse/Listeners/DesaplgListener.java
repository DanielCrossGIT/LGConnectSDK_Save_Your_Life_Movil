package guerrero.arango.miguel.block_a_mouse.Listeners;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import org.json.JSONObject;

import guerrero.arango.miguel.block_a_mouse.Actividades.SeleccionarPersonaje;
import guerrero.arango.miguel.block_a_mouse.Singletons.ProgressSingleton;
import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;
import guerrero.arango.miguel.block_a_mouse.StringsHelper;

/**
 * Created by Miguel on 30/09/2016.
 */

public class DesaplgListener implements WebAppSessionListener {

    Context context;

    public DesaplgListener(Context context) {
        this.context = context;
    }

    @Override
    public void onReceiveMessage(WebAppSession webAppSession, Object message) {
        try{
            JSONObject json = new JSONObject(message.toString());
            String accion = json.getString(StringsHelper.ACTION);
            System.out.println(accion);

            switch (accion){
                case StringsHelper.LOAD_INPUT:
                    VariablesGlobales.getInstance().getMainActivity().NextStep();
                    Toast.makeText(context,"mensaje: cargarInicio",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.ENABLE_START:
                    VariablesGlobales.getInstance().getEsperaJugadores().activarBoton();
                    Toast.makeText(context,"mensaje: puedeIniciar",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.UNABLE_START:
                    VariablesGlobales.getInstance().getEsperaJugadores().desactivarBoton();
                    Toast.makeText(context,"mensaje: bloquearIniciar",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.JUEGO_FINALIZADO:
                    if(VariablesGlobales.getInstance().getJuego() != null){
                        VariablesGlobales.getInstance().getJuego().JuegoFinalizado();
                    }
                    break;
                case StringsHelper.REINICIAR_JUEGO:
                    if(VariablesGlobales.getInstance().getJuego() != null){
                        VariablesGlobales.getInstance().getJuego().reiniciarJuego();
                    }

                    break;
                case StringsHelper.START_TURN:
                    boolean resultado = json.getBoolean("resultado");
                    if(VariablesGlobales.getInstance().getJuego() == null){
                        VariablesGlobales.getInstance().getEsperaJugadores().NextStep(resultado);
                    }   else if(resultado){
                        VariablesGlobales.getInstance().getJuego().desbloquearMando();
                    }   else{
                        VariablesGlobales.getInstance().getJuego().bloquearMando();
                    }

                    break;
                case StringsHelper.PERDER_VIDA:
                    //VariablesGlobales.getInstance().getSeleccionarPersonaje().NextStep();
                    if(VariablesGlobales.getInstance().getJuego() != null){
                        VariablesGlobales.getInstance().getJuego().disminuirVida();
                    }
                    break;
                case StringsHelper.JUEGO_CANCELADO:
                    //VariablesGlobales.getInstance().getSeleccionarPersonaje().NextStep();
                    if(VariablesGlobales.getInstance().getJuego() != null){
                        VariablesGlobales.getInstance().getJuego().juegoCancelado();
                        VariablesGlobales.getInstance().getEsperaJugadores().JuegoCancelado();
                    }
                    break;
                case StringsHelper.BLOQUEO_EXITOSO:
                    //VariablesGlobales.getInstance().getSeleccionarPersonaje().NextStep();
                    if(VariablesGlobales.getInstance().getJuego() != null){
                        VariablesGlobales.getInstance().getJuego().disminuirBloque();
                    }
                    break;
                case StringsHelper.SUCCESS_CONNECTION:
                    boolean res = json.getBoolean("resultado");
                    VariablesGlobales.getInstance().getSeleccionarPersonaje().NextStep(res);
                    Toast.makeText(context,"mensaje: conexionExitosa",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.LIMIT_PLAYERS:
                    VariablesGlobales.getInstance().getSeleccionarPersonaje().NoNextStep();
                    //No se conecto por limite de jugadores
                    Toast.makeText(context,"No pudo conectarse por limite de jugadores",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.LOADING_GAME:
                    VariablesGlobales.getInstance().getSeleccionarPersonaje().NoNextStep();
                    //No se ha cargado el juego aun y no se puede conectar el jugador
                    Toast.makeText(context,"No se ha cargado el juego aun, no puede conectarse",Toast.LENGTH_SHORT).show();
                    break;
                case StringsHelper.INVALID_MOVEMENT:
                    Toast.makeText(context,"Movimiento invalido",Toast.LENGTH_SHORT).show();
                    break;

            }

        }catch (Exception e){

        }

    }

    @Override
    public void onWebAppSessionDisconnect(WebAppSession webAppSession) {

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
