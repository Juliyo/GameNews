package jat.studio.gamenews2;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

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
		customGridAdapter = new GridViewAdapter(getActivity(), R.layout.row_grid, getData());
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
						Toast.makeText(getActivity(), position + "#Selected",Toast.LENGTH_SHORT).show();
			}

		});
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
