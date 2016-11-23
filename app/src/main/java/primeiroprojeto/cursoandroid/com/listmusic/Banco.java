package primeiroprojeto.cursoandroid.com.listmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Gomes on 08/10/2016.
 */

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 6;
    private static final String NOME_BANCO = "ListMusic.db";

    private static final String TABELA_USUARIO = "usuarios1";
    public static final String KEY_ID_USU = "_id_Usuario";
    public static final String KEY_NOMEUSUARIO = "nomeUsuario";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SENHA = "senha";

    private static final String[] COLUNAS = {KEY_ID_USU, KEY_NOMEUSUARIO, KEY_EMAIL, KEY_SENHA};

    private static final String TABELA_MUSICA = "musicas";
    public static final String KEY_ID_MUSICA = "id";
    public static final String KEY_NOMEMUSICA = "nomeMusica";
    public static final String KEY_GENERO = "genero";

    private static final String[] COLUNASMUSICA = {KEY_ID_MUSICA, KEY_NOMEMUSICA, KEY_GENERO};

    public Banco(Context context) {

        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE musicas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeMusica TEXT," +
                "genero TEXT)";
        db.execSQL(CREATE_TABLE);

        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " ( " +
                KEY_ID_USU + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NOMEUSUARIO + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_SENHA + " TEXT " + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS musicas");
        this.onCreate(db);
    }

    public void addMusica(Musica musica) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMEMUSICA, musica.getNome());
        values.put(KEY_GENERO, musica.getGenero());

        db.insert(TABELA_MUSICA, null, values);
        db.close();
    }

    public Musica getMusica(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_MUSICA, // a. tabela
                COLUNASMUSICA,// b.colunas
                "id = ?", //c.colunas para comparar
                new String[]{String.valueOf(id)}, //d. parâmetros
                null, //e. group by
                null,//f. having
                null, //g. order by
                null); //h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Musica musica = cursorToMusica(cursor);
            return musica;
        }
    }

    private Musica cursorToMusica(Cursor cursor) {

        Musica musica = new Musica();
        musica.setId(Integer.parseInt(cursor.getString(0)));
        musica.setNome(cursor.getString(1));
        musica.setGenero(cursor.getString(2));
        return musica;
    }

    public ArrayList<Musica> getAllMusicas(){
        ArrayList<Musica> listaMusicas = new ArrayList<Musica>();

        String query = "SELECT * FROM " + TABELA_MUSICA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Musica musica = cursorToMusica(cursor);
                listaMusicas.add(musica);
            } while(cursor.moveToNext());
        }
        return listaMusicas;
    }

    public  int updateMusica(Musica musica){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOMEMUSICA, musica.getNome());
        values.put(KEY_GENERO, musica.getGenero());

        int i = db.update(TABELA_MUSICA, //tabela
                values, // valores
                KEY_ID_MUSICA+"=?", //colunas para comparar
                new String[]{String.valueOf(musica.getId())} );//parâmetros
        db.close();
        return i; // número de linhas modificadas
    }

    public int deleteMusica(Musica musica){

        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABELA_MUSICA, //tabela
            KEY_ID_MUSICA+"=?", //colunas para comparar
                new String[]{String.valueOf(musica.getId())} );

        db.close();
        return i; //número de linhas excluídas
    }








}













