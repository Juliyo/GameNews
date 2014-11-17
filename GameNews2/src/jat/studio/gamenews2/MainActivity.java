package jat.studio.gamenews2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abhi.barcode.frag.libv2.BarcodeFragment;
import com.abhi.barcode.frag.libv2.IScanResultHandler;
import com.abhi.barcode.frag.libv2.ScanResult;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, IScanResultHandler {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Button menuGaleria;
    private Button menuNoticias;
    private Button menuRevista;
    private Button menuQR;
    private ActionBar decorView;
    private BarcodeFragment fragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FragmentNoticias frgNoticias = new FragmentNoticias(MainActivity.this);
        
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        decorView =  this.getSupportActionBar();
        
        // Set up the drawer. HAHAHAHAH
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),MainActivity.this);
        menuGaleria = (Button)findViewById(R.id.menuGaleria);
        menuNoticias = (Button)findViewById(R.id.menuNoticias);
        menuRevista = (Button)findViewById(R.id.menuRevista);
        menuQR = (Button)findViewById(R.id.menuQR);
        
        menuNoticias.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape2));
        menuNoticias.setTextColor(getResources().getColor(R.color.White));
        decorView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape2));
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentNoticias frg = new FragmentNoticias();
        ft.replace(R.id.fragment_container, frg);
        ft.commit();

        
        //Listener de Galeria
        menuGaleria.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
            	menuGaleria.setTextColor(getResources().getColor(R.color.White));
            	
            	android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            	FragmentGaleria frg = new FragmentGaleria();
            	ft.replace(R.id.fragment_container, frg);
            	ft.commit();
            	cambiaColor("menuGaleria");	
            	
            }
           
        });
        //Listener de Noticias
        menuNoticias.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
            	menuNoticias.setTextColor(getResources().getColor(R.color.White));
            	android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            	FragmentNoticias frg = new FragmentNoticias();
            	ft.replace(R.id.fragment_container, frg);
            	ft.commit();
            	cambiaColor("menuNoticias");
            	
            }
           
        });
        //Listener de Revista
        menuRevista.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
            	menuRevista.setTextColor(getResources().getColor(R.color.White));
            	android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            	/*FragmentGaleria frg = new FragmentGaleria(MainActivity.this);
            	ft.replace(R.id.fragment_container, frg);
            	ft.commit();*/
            	cambiaColor("menuRevista");
            	
            }
           
        });
        //Listener de QR
        menuQR.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        fragment = new BarcodeFragment();
                        ft.replace(R.id.fragment_container, fragment);
                        ft.commit();
                        fragment.setScanResultHandler(MainActivity.this);
                    }
                };
                final  Timer timador;
                timador = new Timer();
                timador.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                        timador.cancel();
                    }
                }, 600, 10000);

            	menuQR.setTextColor(getResources().getColor(R.color.White));
            	cambiaColor("menuQR");
            }
           
        });

    }
    //Resultados del escaneo QR
    @Override
    public void scanResult(ScanResult result) {
        //Toast.makeText(MainActivity.this, result.getRawResult().getText(), Toast.LENGTH_LONG).show();
        if(result != null){
            Intent i = new Intent(MainActivity.this,Visor360.class);
            i.putExtra("URL",result.getRawResult().getText());
            ((MainActivity) MainActivity.this).startActivity(i);
        }
    }

    public void scanAgain(View v){
        //fragment.restart();
    }
    public void cambiaColor(String boton){
    	mNavigationDrawerFragment.cierraDrawer();
    	switch(boton){
    	case "menuNoticias":
    		menuNoticias.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape2));
    		menuGaleria.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuRevista.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuQR.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuGaleria.setTextColor(getResources().getColor(R.color.Black));
    		menuRevista.setTextColor(getResources().getColor(R.color.Black));
    		menuQR.setTextColor(getResources().getColor(R.color.Black));
    		
    		
    		decorView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape2));
    		
    	break;
    	case "menuGaleria":
    		menuGaleria.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape1));
    		menuNoticias.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuRevista.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuQR.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		decorView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape1));
    		menuNoticias.setTextColor(getResources().getColor(R.color.Black));
    		menuRevista.setTextColor(getResources().getColor(R.color.Black));
    		menuQR.setTextColor(getResources().getColor(R.color.Black));
    	break;
    		
    	
    	case "menuQR":
    		menuQR.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape3));
    		menuNoticias.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuGaleria.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuRevista.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		decorView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape3));
    		menuGaleria.setTextColor(getResources().getColor(R.color.Black));
    		menuRevista.setTextColor(getResources().getColor(R.color.Black));
    		menuNoticias.setTextColor(getResources().getColor(R.color.Black));
    	break;
    	
    	case "menuRevista":
    		menuRevista.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape4));
    		menuQR.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuNoticias.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		menuGaleria.setBackgroundDrawable(getResources().getDrawable(R.color.White));
    		decorView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape4));
    		menuGaleria.setTextColor(getResources().getColor(R.color.Black));
    		menuNoticias.setTextColor(getResources().getColor(R.color.Black));
    		menuQR.setTextColor(getResources().getColor(R.color.Black));
    	break;
    	
    	}
    	
    	
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
