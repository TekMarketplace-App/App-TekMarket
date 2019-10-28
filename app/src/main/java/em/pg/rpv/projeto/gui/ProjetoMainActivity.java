package em.pg.rpv.projeto.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import em.pg.rpv.R;
import em.pg.rpv.infra.negocio.Converter;
import em.pg.rpv.infra.gui.GuiUtil;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.projeto.dominio.Projeto;
import em.pg.rpv.projeto.negocio.ProjetoService;
import em.pg.rpv.usuario.dominio.PessoaFisica;

public class ProjetoMainActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();
    private Projeto projeto;
    private Converter converter = Converter.getInstancia();
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_main);

        PessoaFisica criador = sessao.getPessoaFisica();
        long idCriador = criador.getID();

        projeto = sessao.getProjeto();

        ImageView imageView = (ImageView) findViewById(R.id.projeto_imagem_principal);
        TextView textViewNome = (TextView) findViewById(R.id.nome_projeto_main);
        TextView textViewDescricao = (TextView) findViewById(R.id.descricao_projeto_main);
        TextView textViewPlataforma = (TextView) findViewById(R.id.plataforma_projeto_main);
        TextView textViewAplicacao = (TextView) findViewById(R.id.aplicacao_projeto_main);
        TextView textViewPrecoTotal = (TextView) findViewById(R.id.preco_total_projeto);
        ListView listViewComponentes = (ListView) findViewById(R.id.lista_componentes_projeto);

        textViewNome.setText(projeto.getNome());
        textViewDescricao.setText(projeto.getDescricao());
        textViewPlataforma.setText(projeto.getPlataforma());
        textViewAplicacao.setText(projeto.getAplicacao());

    }

    public void onButtonClickProjeto(View v){

        if (v.getId() == R.id.projeto_imagem_principal){
            Intent intent= new Intent(this, ListaImagensProjetoActivity.class);
            sessao.setProjeto(projeto);
            startActivity(intent);
        }
    }

}
