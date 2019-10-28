package em.pg.rpv.projeto.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import em.pg.rpv.R;
import em.pg.rpv.infra.negocio.Converter;
import em.pg.rpv.infra.gui.GuiUtil;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.infra.negocio.Validacao;
import em.pg.rpv.projeto.dominio.Projeto;
import em.pg.rpv.projeto.negocio.ProjetoService;

public class CadastroProjetoActivity extends AppCompatActivity {

    private Validacao validacaoUtil = Validacao.getValidacaoUtil();
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();
    private Projeto projeto = new Projeto();
    private Converter converter = Converter.getInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_projeto);

    }


}
