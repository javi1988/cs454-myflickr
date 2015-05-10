package hwk2.myflickr;

        import android.content.Context;
        import android.database.Cursor;
        import android.support.v4.widget.CursorAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

/**
 * Created by mark on 5/7/15.
 */
public class FlickrPhotoAdapter extends CursorAdapter {

    public FlickrPhotoAdapter(Context c, Cursor cursor){
        super(c, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.title_text);
        String titleText = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PhotoEntry.TITLE));
        title.setText(titleText);
    }
}