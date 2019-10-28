package em.pg.rpv.projeto.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import em.pg.rpv.R;
import em.pg.rpv.infra.dominio.Sessao;

public class ListaImagensProjetoActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagens_projeto);

    }
}
