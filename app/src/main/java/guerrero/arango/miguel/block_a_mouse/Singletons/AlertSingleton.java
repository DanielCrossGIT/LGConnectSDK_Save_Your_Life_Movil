package guerrero.arango.miguel.block_a_mouse.Singletons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by MiguelDev on 10/25/2015.
 */
public class AlertSingleton {

    private static AlertSingleton singleton;
    public AlertDialog dialog;
    AlertDialog.Builder builder;
    private static Context contextsingleton;


    public static AlertSingleton getInstance(Context context, String Message) {

        return singleton= new AlertSingleton(context,Message);
    }

    private AlertSingleton(Context context, String Message) {

        builder = new AlertDialog.Builder(context);

        builder.setTitle("Mensaje")
        .setMessage(Message)
        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.create();


    }


}
