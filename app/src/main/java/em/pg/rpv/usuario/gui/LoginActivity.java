package em.pg.rpv.usuario.gui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import em.pg.rpv.infra.gui.GuiUtil;
import em.pg.rpv.R;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.infra.negocio.Validacao;
import em.pg.rpv.loja.gui.CadastroLojaActivity;
import em.pg.rpv.loja.gui.LojaMainActivity;
import em.pg.rpv.usuario.negocio.UsuarioService;

public class LoginActivity extends AppCompatActivity {
    UsuarioService usuarioService = new UsuarioService(this);
    GuiUtil guiUtil = GuiUtil.getGuiUtil();
    Validacao validacaoUtil = Validacao.getValidacaoUtil();
    private Sessao sessao = Sessao.getInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.DarkGray));
    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.botaoSignIn){
            EditText usuarioEmail = (EditText) findViewById(R.id.sign_in_usuario);
            EditText usuarioSenha = (EditText) findViewById(R.id.sign_in_senha);
            String usuarioEmailString = usuarioEmail.getText().toString();
            String usuarioSenhaString = usuarioSenha.getText().toString();

            if (validacaoUtil.isFieldEmpty(usuarioEmail)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.error_email_vazio));
                return;
            }
            if (validacaoUtil.isFieldEmpty(usuarioSenha)){
                usuarioSenha.requestFocus();
                usuarioSenha.setError(getString(R.string.error_senha_vazia));
                return;
            }
            if(!validacaoUtil.isEmailValid(usuarioEmailString)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.email_invalido));
                return;
            }
            try {
                    usuarioService.login(usuarioEmailString, usuarioSenhaString);
                    if (sessao.getPessoaFisica()!=null){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), LojaMainActivity.class);
                        startActivity(intent);
                    }

            } catch (Exception exception) {
                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }

        }

        else if (v.getId() == R.id.linkCadastrar) {
            Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.linkCadastrarLoja){
            Intent intent= new Intent(getApplicationContext(), CadastroLojaActivity.class);
            startActivity(intent);
        }
    }
}
