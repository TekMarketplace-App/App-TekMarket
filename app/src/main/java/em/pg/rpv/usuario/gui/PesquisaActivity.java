package em.pg.rpv.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import em.pg.rpv.R;
import em.pg.rpv.infra.gui.GuiUtil;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.projeto.negocio.ProjetoService;

public class PesquisaActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
    }

    public void onButtonClickPesquisar(View v){

        if (v.getId() == R.id.botao_pesquisar) {
            EditText busca = (EditText) findViewById(R.id.parametro_busca);
            ListView listView = (ListView) findViewById(R.id.lista_projetos_busca);
            Spinner parametroSpinner = (Spinner) findViewById(R.id.pesquisa_spinner);
            TextView resultadoVazio = (TextView) findViewById(R.id.pesquisa_vazia);

            String buscaString = busca.getText().toString();
            String parametro = parametroSpinner.getSelectedItem().toString();


            }
        }
}
