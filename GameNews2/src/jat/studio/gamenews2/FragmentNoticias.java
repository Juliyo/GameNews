package jat.studio.gamenews2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FragmentNoticias extends Fragment{
	public static ArrayList<String> list_titles;

	public FragmentNoticias(){
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view=null;
		view = inflater.inflate(R.layout.fragment_noticias, container,false);
        list_titles=new ArrayList<String>();
        AsyncTask task=new ProgressTask(this.getActivity().getApplicationContext(),"http://game-news.url.ph/feed/",this.getView()).execute();

		return view;
	} 
}
