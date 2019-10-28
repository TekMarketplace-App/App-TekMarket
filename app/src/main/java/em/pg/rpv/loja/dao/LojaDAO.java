package em.pg.rpv.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import em.pg.rpv.usuario.negocio.DataBaseHelper;
import em.pg.rpv.loja.dominio.Loja;
import em.pg.rpv.usuario.dao.UsuarioDAO;
import em.pg.rpv.usuario.dominio.Usuario;

/**
 *  Classe de persistencia para classe Loja
 */

public class LojaDAO {

    private DataBaseHelper helper;
    private UsuarioDAO usuarioDAO;

    public LojaDAO(Context context) {
        helper = new DataBaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }


    public Loja getLoja(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_USER_LOJA +
                " WHERE " + DataBaseHelper.COLUMN_ID + " LIKE ?";


        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            loja = criaLoja(cursor);
        }
        cursor.close();
        db.close();

        return loja;
    }


    public Loja getLoja(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_USER_LOJA +
                " WHERE " + DataBaseHelper.COLUMN_USUARIO_ID + " LIKE ?";

        long idUsuario = usuario.getID();
        String idUsuarioString = Long.toString(idUsuario);
        String[] argumentos = {idUsuarioString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            loja = criaLoja(cursor);

        }
        cursor.close();
        db.close();


        return loja;
    }


    public long inserir(Loja loja){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values=new ContentValues();

        String nomeColumn= DataBaseHelper.COLUMN_NAME;
        String nome=loja.getNome();

        String cnpjColumn= DataBaseHelper.COLUMN_CNPJ;
        String cnpj=loja.getCnpj();

        String usuarioIdColumn= DataBaseHelper.COLUMN_USUARIO_ID;
        Usuario usuario=loja.getUsuario();
        long idUsario=usuario.getID();

        values.put(nomeColumn,nome);
        values.put(cnpjColumn,cnpj);
        values.put(usuarioIdColumn,idUsario);

        String tabela= DataBaseHelper.TABLE_USER_LOJA;

        long id=db.insert(tabela,null,values);
        db.close();
        return id;

    }


    public Loja criaLoja(Cursor cursor){

        String idColumn= DataBaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String nomeColumn= DataBaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String usuarioIDColumn= DataBaseHelper.COLUMN_USUARIO_ID;
        int indexColumnLojaID= cursor.getColumnIndex(usuarioIDColumn);
        long idUsuario = cursor.getLong(indexColumnLojaID);

        String cnpjColumn= DataBaseHelper.COLUMN_CNPJ;
        int indexColumnCnpj= cursor.getColumnIndex(cnpjColumn);
        String cnpj = cursor.getString(indexColumnCnpj);

        Usuario usuario = usuarioDAO.getUsuario(idUsuario);

        Loja loja = new Loja();
        loja.setId(id);
        loja.setNome(nome);
        loja.setCnpj(cnpj);
        loja.setUsuario(usuario);

        return loja;

    }

}