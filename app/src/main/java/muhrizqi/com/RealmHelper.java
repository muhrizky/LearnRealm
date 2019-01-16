package muhrizqi.com;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    //simpan data
    public void save(final MahasisawaModel mahasiswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm!=null){
                    Log.e("Created","Database was created");
                    Number currentIdNum = realm.where(MahasisawaModel.class).max("id");
                    int nextId;
                    if (currentIdNum==null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue()+1;
                    }
                    mahasiswaModel.setId(nextId);
                    MahasisawaModel model = realm.copyToRealm(mahasiswaModel);
                }else {
                    Log.e("erorr","exceute : database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<MahasisawaModel> getAllMahasiswa(){
        RealmResults<MahasisawaModel> results = realm.where(MahasisawaModel.class).findAll();
        return results;
    }

    //untuk mengupdate data
    public void update(final Integer id, final Integer nim, final String nama){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MahasisawaModel model = realm.where(MahasisawaModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(nim);
                model.setNama(nama);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("errror", "onSuccess: update sucees");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }
    //untuk menghapus data
    public void delete(Integer id){
        final RealmResults<MahasisawaModel> model = realm.where(MahasisawaModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
    }

