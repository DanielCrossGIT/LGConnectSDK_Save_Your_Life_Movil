package guerrero.arango.miguel.block_a_mouse;

import android.app.Application;

import com.connectsdk.discovery.DiscoveryManager;

import guerrero.arango.miguel.block_a_mouse.Singletons.VariablesGlobales;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Miguel on 29/09/2016.
 */

public class ClassApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DiscoveryManager.init(getApplicationContext());
        DiscoveryManager.getInstance().start();



        VariablesGlobales.getInstance().setWEBAPP("desaplg");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Crackvetica.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }
}
