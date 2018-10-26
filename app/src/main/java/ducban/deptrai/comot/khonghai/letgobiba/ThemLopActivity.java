package ducban.deptrai.comot.khonghai.letgobiba;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ducban.deptrai.comot.khonghai.letgobiba.Database.DatabaseManager;

public class ThemLopActivity extends AppCompatActivity {
    //Khai báo
    private EditText edMaLop;
    private EditText edTenLop;

    private ListView lvLop;
    private SimpleCursorAdapter adapter;
    private Cursor cursor; //chua du lieu bang
    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);


        manager = new DatabaseManager(this);

        edMaLop= findViewById(R.id.edMaLop);
        edTenLop = findViewById(R.id.edTenLop);
        lvLop = findViewById(R.id.lvLop);
        getLop();
    }

    public void themLop(View view) {
        String maLop = edMaLop.getText().toString();
        String tenLop = edTenLop.getText().toString();
        if (maLop.equals("") || tenLop.equals("")){
            Toast.makeText(this, "Nhập dữ liệu!Không được để trống ", Toast.LENGTH_SHORT).show();
        }
        else{
            manager.insertLop(maLop, tenLop);

            getLop();
        }
    }

    private void getLop() {
        cursor = manager.getLops();
        if (cursor != null) {
            adapter = new SimpleCursorAdapter(
                    this, R.layout.item_lop, cursor, new String[]{"_id", "MaLop", "TenLop"},
                    new int[]{R.id.tvID, R.id.tvMaLop, R.id.tvTenLop}
            );
            lvLop.setAdapter(adapter);
        }
    }

    public void suaLop(View view) {
        
    }

    public void xoaLop(View view) {
    }
}
