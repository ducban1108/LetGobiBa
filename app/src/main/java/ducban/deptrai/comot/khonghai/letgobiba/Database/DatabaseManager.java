package ducban.deptrai.comot.khonghai.letgobiba.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    public final String DB_NAME = "QuanLySinhVien";
    public final String TB_LOP = "Lop";
    public final String TB_SINH_VIEN = "SinhVien";
    public final int DB_VERSION = 1;

    private SQLiteDatabase database;

    public DatabaseManager(Context context){
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }
    public void insertLop(String maLop, String tenLop) {
        ContentValues values = new ContentValues();
        values.put("MaLop", maLop);
        values.put("TenLop", tenLop);
        database.insert(TB_LOP, null, values);
    }
    public void insertSinhVien(String ten, String diaChi, String lop) {
        ContentValues values = new ContentValues();
        values.put("Ten", ten);
        values.put("DiaChi", diaChi);
        values.put("Lop", lop);
        database.insert(TB_SINH_VIEN, null, values);
    }
    public void updateLop(String maLop, String tenLop, int id){
        ContentValues values = new ContentValues();
        values.put("MaLop", maLop);
        values.put("TenLop", tenLop);
        database.update(TB_LOP, values, "_id = " + id, null);
    }

    public void updateSinhVien(String ten, String diaChi, String lop, int id) {
        ContentValues values = new ContentValues();
        values.put("Ten", ten);
        values.put("DiaChi", diaChi);
        values.put("Lop", lop);
        database.update(TB_SINH_VIEN, values, "_id = "+ id, null);
    }

    public void deleteLop(int id){
        database.delete(TB_LOP,"_id = " + id, null);
    }
    public void deleteSinhVien(int id){database.delete(TB_SINH_VIEN,"_id"+ id,null);}
    public Cursor getSinhVien(){
        return database.query(TB_SINH_VIEN,null,null,null,null,null,null);
    }
    public Cursor getLops(){
        return database.query(TB_LOP, null, null,
                null, null, null, null);
    }

    public class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //tạo bảng
            String lenhTaoBangLop = "CREATE TABLE IF NOT EXISTS Lop(_id INTEGER PRIMARY KEY AUTOINCREMENT, MaLop TEXT, TenLop TEXT)";
            String lenhTaoBangSinhVien = "create table if not exists SinhVien(_id integer primary key autoincrement, Ten text, DiaChi text, Lop text )";
            db.execSQL(lenhTaoBangLop);
            db.execSQL(lenhTaoBangSinhVien);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists Lop");
            db.execSQL("drop table if exists SinhVien");
            onCreate(db);
        }
    }
}
