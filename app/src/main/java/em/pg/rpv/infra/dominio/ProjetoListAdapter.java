package em.pg.rpv.infra.dominio;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import em.pg.rpv.infra.negocio.Converter;
import em.pg.rpv.projeto.dominio.Projeto;

public class ProjetoListAdapter extends ArrayAdapter<Projeto> {
    private Activity activity;
    private ArrayList<Projeto> listaProjetos;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ProjetoListAdapter (Activity activity, int textViewResourceId, ArrayList<Projeto> _listaProjetos){
        super(activity, textViewResourceId, _listaProjetos);
        try {
            this.activity = activity;
            this.listaProjetos = _listaProjetos;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

        }
    }
    public int getCount() {
        return listaProjetos.size();
    }

    public Projeto getItem(Projeto position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView nome_projeto_listagem;
        public TextView plataforma_projeto_listagem;
        public ImageView imagemProjeto;

    }

}

