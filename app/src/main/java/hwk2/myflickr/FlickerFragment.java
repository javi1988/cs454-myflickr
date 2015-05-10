package hwk2.myflickr;

        import android.app.ListFragment;
        import android.database.Cursor;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.Toast;
        import java.util.ArrayList;

/**
 * Created by mark on 4/29/15.
 */
public class FlickerFragment extends Fragment implements AdapterView.OnItemClickListener{
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        MainActivity activity = (MainActivity)this.getActivity();

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        cursor = dbHelper.getAllRows();
        FlickrPhotoAdapter adapter = new FlickrPhotoAdapter(getActivity(), cursor);

        ListView lv =(ListView)view.findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(), mTitles[i], Toast.LENGTH_LONG).show();
        cursor.moveToFirst();
        cursor.move(i);
        String id = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PhotoEntry._ID));
        String farm = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PhotoEntry.FARM));
        String server = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PhotoEntry.SERVER));
        String secret = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PhotoEntry.SECRET));

        // public static String getURL(String farm, String server, String id, String secret, boolean big)
        String url = FlickrPhoto.getURL(farm, server, id, secret, true);
        PhotoFragment pf = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        pf.setArguments(args);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, pf);
        ft.addToBackStack("Image");
        ft.commit();
    }
}