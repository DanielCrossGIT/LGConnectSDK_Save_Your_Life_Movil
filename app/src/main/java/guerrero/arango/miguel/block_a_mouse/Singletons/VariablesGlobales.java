package guerrero.arango.miguel.block_a_mouse.Singletons;


import android.widget.Button;
import android.widget.ImageButton;

import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import guerrero.arango.miguel.block_a_mouse.Actividades.EsperaJugadores;
import guerrero.arango.miguel.block_a_mouse.Actividades.Juego;
import guerrero.arango.miguel.block_a_mouse.Actividades.MainActivity;
import guerrero.arango.miguel.block_a_mouse.Actividades.SeleccionarPersonaje;
import guerrero.arango.miguel.block_a_mouse.Listeners.DesaplgListener;

/**
 * Created by MiguelDev on 10/23/2015.
 */
public class VariablesGlobales {

    private static VariablesGlobales singleton;

    String WEBAPP;
    WebAppSession webAppSession;
    DesaplgListener desaplgListener;

    MainActivity mainActivity;
    SeleccionarPersonaje seleccionarPersonaje;
    EsperaJugadores esperaJugadores;
    Juego juego;

    int avatar;

    public static VariablesGlobales getInstance() {
        if(singleton == null){
            singleton = new VariablesGlobales();
        }
        return singleton;
    }

    private VariablesGlobales() {
    }



    public static VariablesGlobales getSingleton() {
        return singleton;
    }

    public static void setSingleton(VariablesGlobales singleton) {
        VariablesGlobales.singleton = singleton;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getWEBAPP() {
        return WEBAPP;
    }

    public void setWEBAPP(String WEBAPP) {
        this.WEBAPP = WEBAPP;
    }

    public WebAppSession getWebAppSession() {
        return webAppSession;
    }

    public void setWebAppSession(WebAppSession webAppSession) {
        this.webAppSession = webAppSession;

    }

    public EsperaJugadores getEsperaJugadores() {
        return esperaJugadores;
    }

    public void setEsperaJugadores(EsperaJugadores esperaJugadores) {
        this.esperaJugadores = esperaJugadores;
    }

    public DesaplgListener getDesaplgListener() {
        return desaplgListener;
    }

    public void setDesaplgListener(DesaplgListener desaplgListener) {
        this.desaplgListener = desaplgListener;
    }


    public SeleccionarPersonaje getSeleccionarPersonaje() {
        return seleccionarPersonaje;
    }

    public void setSeleccionarPersonaje(SeleccionarPersonaje seleccionarPersonaje) {
        this.seleccionarPersonaje = seleccionarPersonaje;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
