package exemple.akuntsu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 14/02/2016.
 */
public class ListNamesAdapter extends ArrayAdapter<User>{
    private final Context context;
    private User user;
    private List<User> lista;
    private CheckBox ckRem;
    private List<Integer> listaRem;


    public ListNamesAdapter(Context context, List<User> lista){
        super(context, R.layout.item_lista,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        //Inflando a viw com o nosso layout.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View masterView = inflater.inflate(R.layout.item_lista, parent, false);


        TextView tvNome = (TextView) masterView.findViewById(R.id.tv_nome);
        ckRem = (CheckBox) masterView.findViewById(R.id.ckb_user);

        //Vamos supor que o getView esteja desenhando o item position = 3
        //Basta ent�o usar esse n�mero para pegar a Remedio correspondente na lista
        user = this.getItem(position);

        listaRem = new ArrayList<Integer>();

        ckRem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean flag = false;

                for(Integer e:listaRem){
                    if(e.intValue() == position){
                        listaRem.remove(e);
                        flag = true;
                    }
                }
                if(flag == false) {
                    listaRem.add(position);
                }
            }
        });



        //Pegamos cada elemento gr�fico da view (que est� no holder) e atribu�mos os valores
        //do objeto resgatado da lista.
        tvNome.setText(user.getNome());

        return masterView;
    }


    public List<Integer> getListaRem() {
        return listaRem;
    }
}
