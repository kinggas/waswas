package com.TugasPAPBL.tes.sqlite;

import android.provider.BaseColumns;

public class User {

    private User() {
    }

    public static final class UserDetails implements BaseColumns {
        public static final String TABLE_NAME = "activity_biodata";
        public static final String COL_ID = "id";
        public static final String COL_NAME = "nama";
        public static final String COL_JK = "jk";
        public static final String COL_TGL = "tgl";
        public static final String COL_BB = "bb";
        public static final String COL_TB = "tb";
        public static final String COL_LVL = "lvlakt";
    }

    static final class KonsumsiUser implements BaseColumns {
        static String TABLE_NAME = "table_konsumsi";
        static String COL_FOOD_ID = "food_id";
    }
}
