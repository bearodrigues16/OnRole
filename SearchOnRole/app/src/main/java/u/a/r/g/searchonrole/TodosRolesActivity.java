package u.a.r.g.searchonrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TodosRolesActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_roles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        BancoRoles banco = new BancoRoles(getBaseContext());
        List<Role> roles = banco.getTodosRoles();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;

        for(int i = 0; i < roles.size(); i++) {
            map = new HashMap<String, String>();
            map.put("NOME", roles.get(i).getNome());
            map.put("DATA", "Data: " + roles.get(i).getData());
            map.put("CUSTO", "Custo: R$" + String.valueOf(roles.get(i).getCusto()));
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.content_role,
                new String[] { "NOME", "DATA", "CUSTO" },
                new int[] { R.id.nomeRole, R.id.dataRole, R.id.custoRole });
        lista = (ListView)findViewById(R.id.listViewTodosRoles);
        lista.setAdapter(adapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
