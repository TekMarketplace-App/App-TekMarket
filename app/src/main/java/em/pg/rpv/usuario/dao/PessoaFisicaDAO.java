package em.pg.rpv.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import em.pg.rpv.usuario.negocio.DataBaseHelper;
import em.pg.rpv.usuario.dominio.PessoaFisica;
import em.pg.rpv.usuario.dominio.Usuario;

/**
 * Classe de persistencia de dados da classe PessoaFisica.
 */

public class PessoaFisicaDAO {
    private DataBaseHelper helper;
    private UsuarioDAO usuarioDAO;

    public PessoaFisicaDAO(Context context) {
        helper = new DataBaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public PessoaFisica getPessoaFisica(long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_PESSOA_FISICA +
                " WHERE " + DataBaseHelper.COLUMN_ID + " LIKE ?";


        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        PessoaFisica pessoaFisica = null;

        if (cursor.moveToNext()) {

            pessoaFisica = criaPessoaFisica(cursor);

        }
        cursor.close();
        db.close();

        return pessoaFisica;
    }

    public PessoaFisica getPessoaFisica(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_PESSOA_FISICA +
                " WHERE " + DataBaseHelper.COLUMN_USUARIO_ID + " LIKE ?";

        long idUsuario = usuario.getID();
        String idUsuarioString = Long.toString(idUsuario);
        String[] argumentos = {idUsuarioString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        PessoaFisica pessoaFisica = null;

        if (cursor.moveToNext()) {

            pessoaFisica = criaPessoaFisica(cursor);
        }
        cursor.close();
        db.close();

        return pessoaFisica;
    }

    public long inserir(PessoaFisica pessoaFisica){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String nameColumn = DataBaseHelper.COLUMN_NAME;

        String tabela = DataBaseHelper.TABLE_PESSOA_FISICA;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;

    }


    public PessoaFisica criaPessoaFisica(Cursor cursor){

        String idColumn= DataBaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String nomeColumn= DataBaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String usuarioIDColumn= DataBaseHelper.COLUMN_USUARIO_ID;
        int indexColumnUsuarioID= cursor.getColumnIndex(usuarioIDColumn);
        long idUsuario = cursor.getLong(indexColumnUsuarioID);

        Usuario usuario = usuarioDAO.getUsuario(idUsuario);

        PessoaFisica pessoaFisica = new PessoaFisica();

        return pessoaFisica;

    }
}
