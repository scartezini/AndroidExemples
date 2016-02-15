package exemple.akuntsu.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eduardo on 14/02/2016.
 */
public class MainActivity extends Activity {

    private ListNamesAdapter listNamesAdapter;
    private EditText etInsertNome;
    private List<User> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<User>();

        listNamesAdapter = new ListNamesAdapter(this,lista);

        etInsertNome = (EditText) findViewById(R.id.et_inserirNome);
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        Button btnRem = (Button) findViewById(R.id.btn_rem);


        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> listaRem = listNamesAdapter.getListaRem();
                Collections.sort(listaRem, new Comparator<Integer>() {
                    /*  Metodo para ordenar a lista
                     *  em ordem decrescente, se
                     *  não fizer isso ele pode da erro
                     *  Exemplo
                     *  lista = 1,2,3,4
                     *  listaRem = 1,2
                     *  ele vai remover o 1 nova lista
                     *  lista = 2,3,4
                     *  listaRem = 2
                     *  quando for remover o elemento que ta na posicão 2
                     *  vai remover o 3 não mais o 2
                     *
                     */
                    @Override
                    public int compare(Integer lhs, Integer rhs) {
                        if (lhs == rhs) {
                            return 0;
                        } else if (lhs < rhs) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });

                for(Integer e : listaRem){
                    lista.remove(e.intValue());
                }

                listNamesAdapter.notifyDataSetChanged();
            }
        });



        btnOk.setOnClickListener(new View.OnClickListener() {
            private String nome;

            @Override
            public void onClick(View v) {
                nome = etInsertNome.getText().toString();
                lista.add(new User(nome));
                listNamesAdapter.notifyDataSetChanged();
            }
        });



        ListView lvUser = (ListView) findViewById(R.id.listView);
        lvUser.setAdapter(listNamesAdapter);

    }



}
