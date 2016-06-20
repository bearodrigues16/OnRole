package u.a.r.g.searchonrole;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RoleActivity extends AppCompatActivity {

    String json_text;
    String[] json_item;
    private String idRole;
    private TextView asterisco;
    private TextView nome;
    private TextView data;
    private TextView custo;
    private TextView local;
    private TextView descricao;
    private String origID;

    private Intent intent;

    private Spinner spPresenca;
    String[] arrayPresenca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        asterisco = (TextView) findViewById(R.id.asterisco);

        nome = (TextView) findViewById(R.id.EDtituloRole);
        data = (TextView) findViewById(R.id.PRData);
        custo = (TextView) findViewById(R.id.PRCusto);
        local = (TextView) findViewById(R.id.PRLocal);
        descricao = (TextView) findViewById(R.id.PRDescricao);

        json_text = getIntent().getExtras().getString("role");
        json_item = json_text.split(":");

        idRole = json_item[0];
        nome.setText(json_item[1]);
        data.setText(json_item[2]);
        custo.setText("R$ " + json_item[3]);
        local.setText(json_item[4]);
        descricao.setText(json_item[5]);
        origID = json_item[6];

        setTitle(nome.getText().toString());//Seta o título da activity

        if (intent.getFlags() == 1) {
            arrayPresenca = new String[]{"Confirmado", "Não Confirmado"};
        } else {
            arrayPresenca = new String[]{"Não Confirmado", "Confirmado"};
        }
        spPresenca = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapterPresenca = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arrayPresenca);
        adapterPresenca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPresenca.setAdapter(adapterPresenca);

        if (LoginActivity.getId().equals("0") || LoginActivity.getId().equals(origID)) {
            spPresenca.setVisibility(View.GONE);
        }
        if (LoginActivity.getId().equals("0") || !LoginActivity.getId().equals(origID)) {
            asterisco.setVisibility(View.GONE);
        }

        spPresenca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    new PresencaRoleTask().execute(String.valueOf(intent.getFlags()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        if (LoginActivity.getId().equals(origID)) {
            getMenuInflater().inflate(R.menu.menu_role, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(RoleActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_settings) {
            startActivity(new Intent(RoleActivity.this, EditarUsuarioActivity.class));
            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(RoleActivity.this, EditarRoleActivity.class);
            intent.putExtra("role", json_text);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_delete) {
            new ExcluirRoleTask().execute((String) null);
            return true;
        }
        if (id == android.R.id.home) {
            if (intent.getFlags() == 1) {
                startActivity(new Intent(RoleActivity.this, MeusRolesActivity.class));
            } else {
                startActivity(new Intent(RoleActivity.this, TodosRolesActivity.class));
            }
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (intent.getFlags() == 1) {
            startActivity(new Intent(RoleActivity.this, MeusRolesActivity.class));
        } else {
            startActivity(new Intent(RoleActivity.this, TodosRolesActivity.class));
        }
        finish();
    }

    class ExcluirRoleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            String registro_url = "http://searchonrole.esy.es/php/excluirRole.php";

            try {
                URL url = new URL(registro_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String dados = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(idRole, "UTF-8");

                bufferedWriter.write(dados);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

                return "Rolê excluído com sucesso";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Erro ao excluir rolê";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            startActivity(new Intent(RoleActivity.this, MeusRolesActivity.class));
            finish();
        }
    }

    class PresencaRoleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            String presenca_url;
            if (params[0].equals("1")) {
                presenca_url = "http://searchonrole.esy.es/php/desconfirmarRole.php";
            } else {
                presenca_url = "http://searchonrole.esy.es/php/confirmarRole.php";
            }

            try {
                URL url = new URL(presenca_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String dados = URLEncoder.encode("idRole", "UTF-8") + "=" + URLEncoder.encode(idRole, "UTF-8") + "&" +
                        URLEncoder.encode("idUsuario", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.getId(), "UTF-8");

                bufferedWriter.write(dados);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

                return "Presença armazenada com sucesso";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Erro ao armazenar presença no rolê";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }
    }
}
