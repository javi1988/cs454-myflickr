package hwk2.myflickr;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.List;

/**
 * Created by mark on 5/7/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    private String[] projection = {
            Contract.PhotoEntry._ID,
            Contract.PhotoEntry.FAMILY,
            Contract.PhotoEntry.FRIEND,
            Contract.PhotoEntry.FARM,
            Contract.PhotoEntry.TITLE,
            Contract.PhotoEntry.SECRET,
            Contract.PhotoEntry.OWNER,
            Contract.PhotoEntry.PUBLIC,
            Contract.PhotoEntry.SERVER,};


    private static final String DATABASE_CREATE =
            "CREATE TABLE " +
                    Contract.PhotoEntry.TABLE_NAME + " (" +
                    Contract.PhotoEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.PhotoEntry.OWNER + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.SECRET + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.SERVER + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.FARM + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.TITLE + " TEXT NOT NULL, " +
                    Contract.PhotoEntry.PUBLIC + " INTEGER NOT NULL, " +
                    Contract.PhotoEntry.FRIEND + " INTEGER NOT NULL, " +
                    Contract.PhotoEntry.FAMILY + " INTEGER NOT NULL " + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.PhotoEntry.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constants.TAG, "Create table command: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void insertPhotoEntry(FlickrPhoto photo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.PhotoEntry._ID, photo.getId());
        cv.put(Contract.PhotoEntry.OWNER, photo.getOwner());
        cv.put(Contract.PhotoEntry.SECRET, photo.getSecret());
        cv.put(Contract.PhotoEntry.SERVER, photo.getServer());
        cv.put(Contract.PhotoEntry.FARM, photo.getFarm());
        cv.put(Contract.PhotoEntry.TITLE, photo.getTitle());
        cv.put(Contract.PhotoEntry.FAMILY, photo.getIsFamily() ? 1 : 0);
        cv.put(Contract.PhotoEntry.PUBLIC, photo.getIsPublic() ? 1 : 0);
        cv.put(Contract.PhotoEntry.FRIEND, photo.getIsFriend() ? 1 : 0);

        db.insert(Contract.PhotoEntry.TABLE_NAME, null, cv);
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Contract.PhotoEntry.TABLE_NAME, projection, null, null, null, null, null);

//        Here's the method with arguments:
//        public Cursor query (String table, String[] columns, String selection, String[]
//        selectionArgs, String groupBy, String orderBy, String limit)

    }

    public Cursor getRowByID(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] ids = {String.valueOf(id)};
        return db.query(Contract.PhotoEntry.TABLE_NAME, projection, Contract.PhotoEntry._ID + "==?", ids, null, null, null);
    }

    public void deleteRow(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] ids = {String.valueOf(id)};
        db.delete(Contract.PhotoEntry.TABLE_NAME, Contract.PhotoEntry._ID + "==?", ids);
    }

    public void addRows(List<FlickrPhoto> photos) {
        for (FlickrPhoto photo : photos) {
            insertPhotoEntry(photo);
        }
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + Contract.PhotoEntry.TABLE_NAME);
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}