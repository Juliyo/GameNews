package jat.studio.gamenews2;

/**
 * Created by Toni on 17/11/2014.
 */
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.panoramagl.PLIPanorama;
import com.panoramagl.PLImage;
import com.panoramagl.PLSpherical2Panorama;
import com.panoramagl.PLView;
import com.panoramagl.transitions.PLTransitionBlend;
import com.panoramagl.utils.PLUtils;

public class Visor360 extends PLView {
        private View decorView;
        /**init methods*/

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        }

        @Override
        protected View onContentViewCreated(View contentView)
        {
            //Load layout
            ViewGroup mainView = (ViewGroup)this.getLayoutInflater().inflate(R.layout.visor_pantalla_completa, null);
            //Add 360 view
            mainView.addView(contentView, 0);
            //Inmersive option
            decorView = getActivity().getWindow().getDecorView();
            //Load panorama

            PLIPanorama panorama = null;
            panorama = new PLSpherical2Panorama();
            ((PLSpherical2Panorama)panorama).setImage(new PLImage(PLUtils.getBitmap(Visor360.this, R.raw.bioshock_pano5), false));
            if(panorama != null)
            {
                //Set camera rotation
                panorama.getCamera().lookAt(0.0f, 170.0f);

                //Reset view
                this.reset();
                //Load panorama
                this.startTransition(new PLTransitionBlend(2.0f), panorama); //or use this.setPanorama(panorama);
                //Cargar el sensorialRotation
                this.startSensorialRotation();

            }
            //Return root content view
            return super.onContentViewCreated(mainView);
        }

        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
            if (hasFocus) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
        }

}
