package em.pg.rpv.loja.negocio;

import android.content.Context;

import em.pg.rpv.infra.negocio.Criptografia;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.loja.dao.LojaDAO;
import em.pg.rpv.loja.dominio.Loja;
import em.pg.rpv.usuario.dao.UsuarioDAO;
import em.pg.rpv.usuario.dominio.Usuario;

/**
 * Classe de Serviço para comunicação com a classe LojaDAO e validação.
 */

public class LojaService {
    private Sessao sessao = Sessao.getInstancia();
    private LojaDAO lojaDAO;
    private UsuarioDAO usuarioDAO;
    private Criptografia criptografia=new Criptografia();

    public LojaService(Context context){
        lojaDAO= new LojaDAO(context);
        usuarioDAO=new UsuarioDAO(context);
    }


    public void login(Usuario usuario){
        sessao.reset();

        Loja loja = lojaDAO.getLoja(usuario);

        sessao.setLoja(loja);

    }

    public void cadastrar(Loja loja) throws Exception {

        Usuario loja_usuario =loja.getUsuario();
        String email=loja_usuario.getEmail();
        String senha=loja_usuario.getPass();

        Usuario usuario= usuarioDAO.getUsuario(email);

        if (usuario != null){
            throw new Exception("Email já cadastrado");
        }

        String senhaMascarada=criptografia.mascararSenha(senha);

        loja_usuario.setPass(senhaMascarada);

        long idUsuario = usuarioDAO.inserir(loja_usuario);

        loja_usuario.setID(idUsuario);

        long idLoja = lojaDAO.inserir(loja);
        loja.setId(idLoja);

        sessao.setLoja(loja);

    }
}
