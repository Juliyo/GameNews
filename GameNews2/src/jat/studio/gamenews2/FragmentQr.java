package jat.studio.gamenews2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Toni on 11/11/2014.
 */
public class FragmentQr extends Fragment{
    private View view;
    public FragmentQr(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qr, container,false);
        return view;
    }


}
