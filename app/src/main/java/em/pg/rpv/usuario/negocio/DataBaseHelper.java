package em.pg.rpv.usuario.negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "makerprice.db";

    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";

    public static final String TABLE_USER_LOJA = "usuarioLoja";
    public static final String COLUMN_CNPJ = "cnpj";
    public static final String COLUMN_AJSON = "ajson";
    public static final String COLUMN_LINKIMAGEM = "linkImagem";

    public static final String TABLE_PROJETO = "projeto";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_PLATAFORMA = "plataforma";
    public static final String COLUMN_APLICACAO = "aplicacao";
    public static final String COLUMN_PESSOAFISICA_ID = "criador";


    public static final String TABLE_PESSOA_FISICA = "pessoa_fisica";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USUARIO_ID = "usuario_id";

    public static final String TABLE_IMAGEM = "imagem";
    public static final String COLUMN_CAMINHO = "caminho";
    public static final String COLUMN_PROJETO_ID = "projeto_id";

    public static final String TABLE_COMPONENTE = "componente";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_COR = "cor";
    public static final String COLUMN_CAPACITANCIA = "capacitancia";
    public static final String COLUMN_RESISTENCIA = "resistencia";

    public static final String[] COLUMNS_COMPONENTE_SPEC = {
            COLUMN_TIPO, COLUMN_COR, COLUMN_CAPACITANCIA, COLUMN_RESISTENCIA
    };

    public static final String TABLE_COMPONENTE_PROJETO = "componente_projeto";
    public static final String COLUMN_COMPONENTE_ID = "componente_id";
    public static final String COLUMN_QUANTIDADE = "quantidade";

    public static final String TABLE_COMPONENTE_LOJA = "componente_loja";
    public static final String COLUMN_LOJA_ID = "loja_id";
    public static final String COLUMN_PRECO = "preco";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EMAIL + " TEXT NOT NULL, " +
                        COLUMN_PASS + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER_LOJA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_CNPJ + " TEXT NOT NULL, "+
                COLUMN_USUARIO_ID + " INTEGER);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_PESSOA_FISICA + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_USUARIO_ID + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(query);
        this.onCreate(db);
    }

    public static String getColumnEmail() {
        return COLUMN_EMAIL;
    }

    public static String getColumnNome() {
        return COLUMN_NAME;
    }

    public static String getColumnSenha() {
        return COLUMN_PASS;
    }

    public static String getColumnCnpj(){ return COLUMN_CNPJ;}

    public static String getColumnLinkimagem() {return  COLUMN_LINKIMAGEM;}

    public static String getColumnAjson() {return COLUMN_AJSON;}

}