package u.a.r.g.searchonrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeusRolesActivity extends AppCompatActivity {

    BancoRoles bancoRoles;
    private ListView lista;
    private static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_roles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        bancoRoles = BancoRoles.getInstance(getBaseContext());
        List<Role> roles = bancoRoles.getTodosRoles();

        final List<Map<String, String>> listaRoles = new ArrayList<Map<String, String>>();
        Map<String, String> map;

        for(int i = 0; i < roles.size(); i++) {
            map = new HashMap<String, String>();
            map.put("NOME", roles.get(i).getNome());
            map.put("DATA", "Data: " + roles.get(i).getData());
            map.put("CUSTO", "Custo: R$" + String.valueOf(roles.get(i).getCusto()));
            listaRoles.add(map);
        }
        map = new HashMap<String, String>();
        map.put("NOME", "");
        map.put("DATA", "");
        map.put("CUSTO", "");
        listaRoles.add(map);

        SimpleAdapter adapter = new SimpleAdapter(this, listaRoles, R.layout.content_list_role,
                new String[] { "NOME", "DATA", "CUSTO" },
                new int[] { R.id.nomeRole, R.id.dataRole, R.id.custoRole });
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position != listaRoles.size() - 1) {
                    setPosition(position);
                    startActivity(new Intent(MeusRolesActivity.this, RoleActivity.class));
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeusRolesActivity.this,TodosRolesActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meusroles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout) {
            LoginActivity.mAuth.signOut();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
