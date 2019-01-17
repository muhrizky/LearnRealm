package muhrizqi.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etnim, etnama;
    String nim, nama;
    Integer id;
    Button btn_ubah, btn_hapus, btn_kembali;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // setup
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        //inisialisasi
        etnim = findViewById(R.id.etnim);
        etnama = findViewById(R.id.etnama);
        btn_ubah = findViewById(R.id.btnupdate);
        btn_hapus = findViewById(R.id.btnhapus);
        btn_kembali = findViewById(R.id.btncancle);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        nim = getIntent().getStringExtra("nim");
        nama = getIntent().getStringExtra("nama");

        etnim.setText(nim);
        etnama.setText(nama);

        btn_kembali.setOnClickListener(this);
        btn_hapus.setOnClickListener(this);
        btn_ubah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_ubah){
            realmHelper.update(id, Integer.parseInt(etnim.getText().toString()),etnama.getText().toString());
            Toast.makeText(DetailActivity.this,"update succes", Toast.LENGTH_SHORT).show();
            etnim.setText("");
            etnama.setText("");
            finish();
        }else if (v == btn_hapus){
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this,"Delete succes",Toast.LENGTH_SHORT).show();
        }else if (v == btn_kembali){
            startActivity(new Intent(DetailActivity.this, MahasiswaActivity.class));
            finish();
        }

    }
}
