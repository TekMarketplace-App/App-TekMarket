package em.pg.rpv.usuario.negocio;

import android.content.Context;

import em.pg.rpv.infra.negocio.Criptografia;
import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.loja.negocio.LojaService;
import em.pg.rpv.usuario.dao.PessoaFisicaDAO;
import em.pg.rpv.usuario.dao.UsuarioDAO;
import em.pg.rpv.usuario.dominio.PessoaFisica;
import em.pg.rpv.usuario.dominio.Usuario;

/**
 * Classe de Serviço para comunicação com a classe UsuarioDAO e validação.
 */

public class UsuarioService  {
    private Sessao sessao = Sessao.getInstancia();
    private UsuarioDAO usuarioDAO;
    private PessoaFisicaDAO pessoaFisicaDAO;
    private Criptografia criptografia = new Criptografia();
    private LojaService lojaService;


    public UsuarioService(Context context){
        usuarioDAO = new UsuarioDAO(context);
        pessoaFisicaDAO = new PessoaFisicaDAO(context);
        lojaService = new LojaService(context);
    }


   public void login(String email, String senha) throws Exception {
        sessao.reset();

        String senhaMascarada = criptografia.mascararSenha(senha);

        Usuario usuario= usuarioDAO.getUsuario(email, senhaMascarada);

        if(usuario==null) {
            throw new Exception("Usuário ou senha inválidos");
        }

        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoaFisica(usuario);

       if (pessoaFisica != null){
           sessao.setPessoaFisica(pessoaFisica);
       }

       else {
           lojaService.login(usuario);
       }

   }

    public void cadastrar(String nome, String email, String senha) throws Exception {
        sessao.reset();

        Usuario usuario = usuarioDAO.getUsuario(email);

        if (usuario!=null){
            throw new Exception("Email já cadastrado");
        }

        String senhaMascarada = criptografia.mascararSenha(senha);

        usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPass(senhaMascarada);

        long idUsuario = usuarioDAO.inserir(usuario);

        usuario.setID(idUsuario);

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(nome);
        pessoaFisica.setUsuario(usuario);

        long idPessoaFisica = pessoaFisicaDAO.inserir(pessoaFisica);
        pessoaFisica.setID(idPessoaFisica);

        sessao.setPessoaFisica(pessoaFisica);

    }


}
