package em.pg.rpv.projeto.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import em.pg.rpv.infra.dominio.Sessao;
import em.pg.rpv.projeto.dao.ProjetoDAO;
import em.pg.rpv.projeto.dominio.Projeto;


public class ProjetoService {
    private Sessao sessao = Sessao.getInstancia();
    private ProjetoDAO projetoDAO;

    public ProjetoService(Context context){
        projetoDAO = new ProjetoDAO(context);
    }

    public void cadastrar(Projeto projeto) {
        projetoDAO.inserir(projeto);

    }

    public ArrayList<Projeto> getTodosProjetosUnicoCriador(long idCriador){
        return projetoDAO.getTodosProjetosUnicoCriador(idCriador);
    }

    public List<Projeto> buscaProjetos(String busca) {
        return projetoDAO.buscaProjetos(busca);
    }

}
