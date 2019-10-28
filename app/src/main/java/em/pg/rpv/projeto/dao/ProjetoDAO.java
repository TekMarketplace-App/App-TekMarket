package em.pg.rpv.projeto.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import em.pg.rpv.usuario.negocio.DataBaseHelper;
import em.pg.rpv.projeto.dominio.Projeto;
import em.pg.rpv.usuario.dao.PessoaFisicaDAO;
import em.pg.rpv.usuario.dominio.PessoaFisica;


public class ProjetoDAO {
    private DataBaseHelper helper;
    private PessoaFisicaDAO pessoaFisicaDAO;

    public ProjetoDAO(Context context) {
        helper = new DataBaseHelper(context);
        pessoaFisicaDAO = new PessoaFisicaDAO(context);
    }

    public long inserir(Projeto projeto){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        ContentValues valuesImagens = new ContentValues();

        String nameColumn = DataBaseHelper.COLUMN_NAME;
        String nome = projeto.getNome();

        String descricaoColumn = DataBaseHelper.COLUMN_DESCRICAO;
        String descricao = projeto.getDescricao();

        String plataformaColumn = DataBaseHelper.COLUMN_PLATAFORMA;
        String plataforma = projeto.getPlataforma();

        String aplicacaoColumn = DataBaseHelper.COLUMN_APLICACAO;
        String aplicacao = projeto.getAplicacao();

        PessoaFisica criador = projeto.getCriador();
        long idCriador = criador.getID();

        String criadorColumn = DataBaseHelper.COLUMN_PESSOAFISICA_ID;
        String idCriadorString = Long.toString(idCriador);

        values.put(nameColumn, nome);
        values.put(descricaoColumn, descricao);
        values.put(plataformaColumn, plataforma);
        values.put(aplicacaoColumn, aplicacao);
        values.put(criadorColumn, idCriadorString);

        String tabela = DataBaseHelper.TABLE_PROJETO;
        String tabelaImagem = DataBaseHelper.TABLE_IMAGEM;

        long id = db.insert(tabela, null, values);

        projeto.setId(id);

        String caminhoColumn = DataBaseHelper.COLUMN_CAMINHO;
        String projetoId = DataBaseHelper.COLUMN_PROJETO_ID;

        db.close();
        return id;
    }

    public ArrayList<Projeto> getTodosProjetosUnicoCriador(long idCriador){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_PROJETO +
                " WHERE " + DataBaseHelper.COLUMN_PESSOAFISICA_ID + " LIKE ?";

        String idCriadorString = Long.toString(idCriador);

        String[] argumentos = {idCriadorString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Projeto> listaProjetos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Projeto projeto = criaProjeto(cursor);;

            listaProjetos.add(projeto);

        }
        cursor.close();
        db.close();

        return listaProjetos;
    }

    public ArrayList<String> getImagensUnicoProjeto (long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_IMAGEM +
                " WHERE " + DataBaseHelper.COLUMN_PROJETO_ID + " LIKE ?";

        String idString = Long.toString(id);

        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<String> listaImagensProjeto = new ArrayList<>();

        String caminhoColumn = DataBaseHelper.COLUMN_CAMINHO;
        int indexColumnCaminho = cursor.getColumnIndex(caminhoColumn);

        while (cursor.moveToNext()) {

            String caminho = cursor.getString(indexColumnCaminho);

            listaImagensProjeto.add(caminho);

        }
        cursor.close();
        db.close();

        return listaImagensProjeto;


    }
    public List<Projeto> buscaProjetos(String busca) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DataBaseHelper.TABLE_PROJETO +
                " WHERE " + DataBaseHelper.COLUMN_NAME + " LIKE ? " +
                "OR " + DataBaseHelper.COLUMN_DESCRICAO + " LIKE ?";

        String argumento = "%" + busca + "%";

        String[] argumentos = {argumento, argumento};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Projeto> listaProjetos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Projeto projeto = criaProjeto(cursor);

            listaProjetos.add(projeto);
        }
        cursor.close();
        db.close();

        return listaProjetos;
    }

    public Projeto criaProjeto(Cursor cursor){

        String idColumn = DataBaseHelper.COLUMN_ID;
        int indexColumnId = cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnId);

        String nomeColumn= DataBaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String descricaoColumn= DataBaseHelper.COLUMN_DESCRICAO;
        int indexColumnDescricao= cursor.getColumnIndex(descricaoColumn);
        String descricao = cursor.getString(indexColumnDescricao);

        String plataformaColumn= DataBaseHelper.COLUMN_PLATAFORMA;
        int indexColumnPlataforma= cursor.getColumnIndex(plataformaColumn);
        String plataforma = cursor.getString(indexColumnPlataforma);

        String aplicacaoColumn= DataBaseHelper.COLUMN_APLICACAO;
        int indexColumnAplicacao= cursor.getColumnIndex(aplicacaoColumn);
        String aplicacao = cursor.getString(indexColumnAplicacao);

        String idCriadorColumn = DataBaseHelper.COLUMN_PESSOAFISICA_ID;
        int indexColumnCriadorId = cursor.getColumnIndex(idCriadorColumn);
        long idCriador = cursor.getLong(indexColumnCriadorId);

        ArrayList<String> listaImagensProjeto = getImagensUnicoProjeto(id);

        PessoaFisica criador = pessoaFisicaDAO.getPessoaFisica(idCriador);

        Projeto projeto = new Projeto();
        projeto.setId(id);
        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setPlataforma(plataforma);
        projeto.setAplicacao(aplicacao);
        projeto.setCriador(criador);

        return projeto;
    }
}
