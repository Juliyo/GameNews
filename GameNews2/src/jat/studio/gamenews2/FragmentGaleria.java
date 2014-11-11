package jat.studio.gamenews2;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.abhi.barcode.frag.libv2.BarcodeFragment;

public class FragmentGaleria extends Fragment {
	
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
	private Context mContext;
	public FragmentGaleria(){
		
	}
	//public FragmentGaleria(Context context){
		//mContext = context;
	//}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=null;
        view = inflater.inflate(R.layout.fragment_galeria, container,false);
        View getview = this.getView();
        gridView = (GridView) view.findViewById(R.id.gridView);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                customGridAdapter = new GridViewAdapter(getActivity(), R.layout.row_grid, getData());
                gridView.setAdapter(customGridAdapter);
                gridView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Toast.makeText(getActivity(), position + "#Selected",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        final Timer timador;
        timador = new Timer();
        timador.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
                timador.cancel();
            }
        }, 600, 10000);


		return view;
	} 
	private ArrayList<ImageItem> getData() {
		final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
		// retrieve String drawable array
		TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
		for (int i = 0; i < imgs.length(); i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
					imgs.getResourceId(i, -1));
			imageItems.add(new ImageItem(bitmap, "Image#" + i));
		}

		return imageItems;

	}
}
