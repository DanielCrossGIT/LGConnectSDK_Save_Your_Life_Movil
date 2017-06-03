package guerrero.arango.miguel.block_a_mouse.Singletons;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by MiguelDev on 10/24/2015.
 */
public class ProgressSingleton {

    private static ProgressSingleton singleton;
    private static Context contextsingleton;
    public ProgressDialog pDialog;

    public static ProgressSingleton getInstance(Context context) {




        if(singleton == null){
            singleton = new ProgressSingleton(context);
        }   else{
            if(context != contextsingleton){
                contextsingleton = context;
                singleton = new ProgressSingleton(context);

            }
        }

        return singleton;
    }

    private ProgressSingleton(Context context) {

        this.contextsingleton = context;
        pDialog = new ProgressDialog(this.contextsingleton);
        pDialog.setMessage("Cargando...");
        pDialog.setCancelable(false);
    }
}
