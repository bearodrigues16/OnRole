package u.a.r.g.searchonrole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hugo on 26/05/2016.
 */
public class BancoRoles extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "roles";
    private static final String ID = "_id";
    private static final String NOME = "nome";
    private static final String DATA = "data";
    private static final String CUSTO = "custo";
    private static final String DESCRICAO = "descricao";
    private static final String LOCAL = "local";
    private static final String BEBIDAS = "bebidas";
    private static final int VERSAO = 1;

    public BancoRoles(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "
                + TABELA + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " nome,"
                + DATA + " data,"
                + CUSTO + " custo,"
                + DESCRICAO + " descricao,"
                + LOCAL + " local,"
                + BEBIDAS + " bebidas"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    public String insereRole(Role role){
        long resultado;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOME, role.getNome());
        valores.put(DATA, role.getData());
        valores.put(CUSTO, role.getCusto());
        valores.put(DESCRICAO, role.getDescricao());
        valores.put(LOCAL, role.getLocal());
        //for(String bebida : role.getBebidas()) {
            valores.put(BEBIDAS, role.getBebidas());
        //}

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado ==-1) {
            return "Erro ao cadastrar rolê";
        } else {
            return "Rolê Cadastrado com sucesso";
        }
    }

    public List<Role> getTodosRoles() {
        List<Role> roleList = new LinkedList();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABELA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Role role = new Role();
                role.setId(Integer.parseInt(cursor.getString(0)));
                role.setNome(cursor.getString(1));
                role.setData(cursor.getString(2));
                role.setCusto(Float.parseFloat(cursor.getString(3)));
                role.setDescricao(cursor.getString(4));
                role.setLocal(cursor.getString(5));
                role.setBebidas(cursor.getString(6));
                // Adding contact to list
                roleList.add(role);
            } while (cursor.moveToNext());
        }
        // return contact list
        return roleList;
    }

    public int getRolesCount() {
        String countQuery = "SELECT * FROM " + TABELA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }


}
