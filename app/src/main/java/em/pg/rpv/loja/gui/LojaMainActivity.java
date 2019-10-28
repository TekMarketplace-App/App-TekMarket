package em.pg.rpv.loja.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import em.pg.rpv.R;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.loja.dominio.Loja;
import em.pg.rpv.usuario.gui.LoginActivity;

public class LojaMainActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_main);

        TextView tv = (TextView) findViewById(R.id.nome_loja);
        Loja loja = sessao.getLoja();
        String nome = loja.getNome();
        tv.setText(nome);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_loja, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.botao_logout) {
            sessao.reset();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
