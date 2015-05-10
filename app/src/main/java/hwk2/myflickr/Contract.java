package hwk2.myflickr;

import android.provider.BaseColumns;
/**
 * Created by mark on 5/7/15.
 */
//String id, owner, secret, server, farm, title;
//        Boolean isPublic, isFriend, isFamily;

public class Contract{
    public static final String DATABASE_NAME = "myflickr.db";

    public static final class PhotoEntry implements BaseColumns{

        public static final String TABLE_NAME = "photo_entry";
        public static final String OWNER = "owner";
        public static final String SECRET = "secret";
        public static final String SERVER = "server";
        public static final String FARM = "farm";
        public static final String TITLE = "title";
        public static final String PUBLIC = "public";
        public static final String FRIEND = "friend";
        public static final String FAMILY = "family";

    }

}