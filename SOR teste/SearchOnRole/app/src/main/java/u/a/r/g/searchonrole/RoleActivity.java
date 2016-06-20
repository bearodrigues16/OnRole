package u.a.r.g.searchonrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RoleActivity extends AppCompatActivity {

    BancoRoles bancoRoles;
    List<Role> roles;
    private TextView nome;
    private TextView data;
    private TextView custo;
    private TextView local;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        bancoRoles = BancoRoles.getInstance(getBaseContext());

        nome = (TextView) findViewById(R.id.EDtituloRole);
        data = (TextView) findViewById(R.id.PRData);
        custo = (TextView) findViewById(R.id.PRCusto);
        local = (TextView) findViewById(R.id.PRLocal);
        descricao = (TextView) findViewById(R.id.PRDescricao);

        roles = bancoRoles.getTodosRoles();

        nome.setText(roles.get(MeusRolesActivity.getPosition()).getNome());

        setTitle(nome.getText().toString());//Seta o título da activity

        data.setText(roles.get(MeusRolesActivity.getPosition()).getData());
        custo.setText(String.valueOf(roles.get(MeusRolesActivity.getPosition()).getCusto()));
        local.setText(roles.get(MeusRolesActivity.getPosition()).getLocal());
        descricao.setText(roles.get(MeusRolesActivity.getPosition()).getDescricao());

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_role, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            startActivity(new Intent(RoleActivity.this, EditarRoleActivity.class));
            return true;
        }
        if (id == R.id.action_delete) {
            String resultado;
            resultado = bancoRoles.excluirRole(roles.get(MeusRolesActivity.getPosition()));
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
