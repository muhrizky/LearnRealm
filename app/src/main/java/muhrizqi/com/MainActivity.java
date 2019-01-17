package muhrizqi.com;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnsimpan, btntampil;
    EditText nim,nama;
    String snama;
    Integer snim;
    Realm realm;
    RealmHelper realmHelper;
    MahasisawaModel mahasisawaModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi
        btnsimpan = findViewById(R.id.btnsimpan);
        btntampil = findViewById(R.id.btntampil);
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);

        //setup realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnsimpan.setOnClickListener(this);
        btntampil.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onClick(View v) {
        if (v == btnsimpan) {
            snim = Integer.parseInt(nim.getText().toString());
            snama = nama.getText().toString();

            if (!snim.equals("") && !snama.isEmpty()) {
                mahasisawaModel = new MahasisawaModel();
                mahasisawaModel.setNim(snim);
                mahasisawaModel.setNama(snama);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(mahasisawaModel);

                Toast.makeText(MainActivity.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                nim.setText("");
                nama.setText("");

            } else {
                Toast.makeText(MainActivity.this, "Terdapat inputan kosong", Toast.LENGTH_SHORT).show();
            }
        }else  if (v== btntampil){
                startActivity(new Intent(MainActivity.this, MahasiswaActivity.class));
            }

        }
    }

