package em.pg.rpv.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import em.pg.rpv.usuario.negocio.DataBaseHelper;
import em.pg.rpv.usuario.dominio.Usuario;

/***
 * Classe de Persistencia para Usuario
 *
 * @author Equipe TM place
 *
 */

public class UsuarioDAO {
    private DataBaseHelper helper;

    public UsuarioDAO(Context context) {

        helper = new DataBaseHelper(context);
    }

    public Usuario getUsuario(String email, String senha) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_USER +
                " WHERE " + DataBaseHelper.COLUMN_EMAIL + " LIKE ? AND " +
                DataBaseHelper.COLUMN_PASS + " LIKE ?";

        String[] argumentos = {email, senha};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {
            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;
    }

    public Usuario getUsuario(String email){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_USER +
                " WHERE " + DataBaseHelper.COLUMN_EMAIL + " LIKE ?";

        String[] argumentos = {email};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {

            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;
    }

    public Usuario getUsuario(long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_USER +
                " WHERE " + DataBaseHelper.COLUMN_ID + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {

            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;

    }

    public long inserir(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String emailColumn = DataBaseHelper.COLUMN_EMAIL;
        String email = usuario.getEmail();

        String senhaColumn = DataBaseHelper.COLUMN_PASS;
        String senha = usuario.getPass();

        values.put(emailColumn, email);
        values.put(senhaColumn, senha);

        String tabela = DataBaseHelper.TABLE_USER;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;

    }


    public Usuario criaUsuario(Cursor cursor){

        String idColumn= DataBaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String emailColumn= DataBaseHelper.COLUMN_EMAIL;
        int indexColumnEmail= cursor.getColumnIndex(emailColumn);
        String email = cursor.getString(indexColumnEmail);

        String senhaColumn= DataBaseHelper.COLUMN_PASS;
        int indexColumnSenha= cursor.getColumnIndex(senhaColumn);
        String senha = cursor.getString(indexColumnSenha);

        Usuario usuario = new Usuario();
        usuario.setID(id);
        usuario.setEmail(email);
        usuario.setPass(senha);

        return usuario;

    }
}
