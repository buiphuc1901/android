package com.example.bdp139.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bdp139.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="bdp139.db";
    private final static int DATABSE_VERSION=1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table congviec(" +
                "id integer primary key autoincrement," +
                "ten text," +
                "noidung text," +
                "ngayhoanthanh date," +
                "tinhtrang integer," +
                "congtac text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCV(CongViec congViec) {
        String sql = "INSERT INTO congviec(ten,noidung,ngayhoanthanh,tinhtrang,congtac)"+
                "VALUES(?,?,?,?,?)";
        String[] args = {congViec.getTen(), congViec.getNoidung(), congViec.getNgayHoanThanh(),
                String.valueOf(congViec.getTinhtrang()), String.valueOf(congViec.isCongTac())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }

    public List<CongViec> getCongViecs(){
        List<CongViec> list=new ArrayList<>();
        String sql="select * " +
                "from congviec";
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,null);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngay = rs.getString(3);
            int tinhtrang = rs.getInt(4);
            boolean congtac = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                congtac = true;
            }

            CongViec congViec=new CongViec(ma, ten, noidung, "", tinhtrang, congtac);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

    public int updateCV(CongViec congViec){
        ContentValues values=new ContentValues();
        values.put("ten",congViec.getTen());
        values.put("noidung",congViec.getNoidung());
        values.put("tinhtrang",congViec.getTinhtrang());
        values.put("congtac",String.valueOf(congViec.isCongTac()));
        String where="id=?";
        String[] agrs={Integer.toString(congViec.getMa())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("congviec",values,where,agrs);
    }

    public int deleteCV(int ma){
        String where="id=?";
        String[] agrs={Integer.toString(ma)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("congviec",where,agrs);
    }

    public List<CongViec> searchByKey(String key){
        List<CongViec> list = new ArrayList<>();
        String sql="select * " +
                "from congviec " +
                "where ten like ? or noidung like ? " +
                "order by ngayhoanthanh";
        String[] agrs={"%"+key+"%","%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngay = rs.getString(3);
            int tinhtrang = rs.getInt(4);
            boolean congtac = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                congtac = true;
            }

            CongViec congViec=new CongViec(ma, ten, noidung, ngay, tinhtrang, congtac);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

    public List<CongViec> searchByKey(String key, int tinhtrangKey){
        List<CongViec> list = new ArrayList<>();
        String sql="select * " +
                "from congviec " +
                "where (ten like ? or noidung like ?) and tinhtrang = ? " +
                "order by ngayhoanthanh";
        String[] agrs={"%"+key+"%","%"+key+"%", String.valueOf(tinhtrangKey)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngay = rs.getString(3);
            int tinhtrang = rs.getInt(4);
            boolean congtac = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                congtac = true;
            }

            CongViec congViec=new CongViec(ma, ten, noidung, ngay, tinhtrang, congtac);
            list.add(congViec);
        }
        rs.close();
        return list;
    }

}
