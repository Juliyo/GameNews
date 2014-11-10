package jat.studio.gamenews2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNoticias extends Fragment{
	private Context mContext;
	public FragmentNoticias(){
		
	}
	public FragmentNoticias(Context context){
		mContext = context;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view=null;
		view = inflater.inflate(R.layout.fragment_noticias, container,false);
		return view;
	} 
}
