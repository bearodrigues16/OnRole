package u.a.r.g.searchonrole;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btn1 = (Button) findViewById(R.id.button_meus_roles);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the "Meus Rolês" button */
    public void meusRoles(View view) {
        Intent intent = new Intent(this, MeusRolesActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the "Organização" button */
    public void organizacao(View view) {
        Intent intent = new Intent(this, OrganizacaoActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the "Serviços" button */
    public void servicos(View view) {
        Intent intent = new Intent(this, ServicosActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the "Carteira" button */
    public void minhaCarteira(View view) {
        Intent intent = new Intent(this, MinhaCarteiraActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the "Configurações" button */
    public void configuracoes(View view) {
        Intent intent = new Intent(this, ConfiguracoesActivity.class);
        startActivity(intent);
    }
}