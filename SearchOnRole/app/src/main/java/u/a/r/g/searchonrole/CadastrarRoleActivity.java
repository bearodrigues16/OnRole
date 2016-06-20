package u.a.r.g.searchonrole;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class CadastrarRoleActivity extends AppCompatActivity {

    //private BancoRoles bancoRoles;
    private EditText ETnome;
    private EditText ETdata;
    private EditText ETcusto;
    private EditText ETdescricao;
    private EditText ETlocal;

    private String nome;
    private String data;
    private String custo;
    private String descricao;
    private String local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        Button btnCadastrar = (Button) findViewById(R.id.button_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETnome = (EditText) findViewById(R.id.EDtituloRole);
                ETdata = (EditText) findViewById(R.id.EDdata);
                ETcusto = (EditText) findViewById(R.id.EDcusto);
                ETdescricao = (EditText) findViewById(R.id.EDdescricao);
                ETlocal = (EditText) findViewById(R.id.EDlocal);

                if (isEmpty(ETnome) || isEmpty(ETdata) || isEmpty(ETcusto) || isEmpty(ETdescricao)) {
                    showDialog("Dados Incompletos!", "Preencha todos os campos obrigatórios");
                } else {
                    roleReg(v);
                }
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editarrole, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home) {
            startActivity(new Intent(CadastrarRoleActivity.this, TodosRolesActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_settings) {
            startActivity(new Intent(CadastrarRoleActivity.this, EditarUsuarioActivity.class));
            return true;
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(CadastrarRoleActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isEmpty(EditText myEditText) {
        return myEditText.getText().toString().trim().length() == 0;
    }

    public void showDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        // onClick button code here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();
    }

    public void roleReg(View view) {
        nome = ETnome.getText().toString();
        data = ETdata.getText().toString();
        custo = ETcusto.getText().toString();
        descricao = ETdescricao.getText().toString();
        local = ETlocal.getText().toString();

        //Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_LONG).show();

        new CadastrarRoleTask(this).execute((String) null);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastrarRoleActivity.this, TodosRolesActivity.class));
        finish();
    }

    class CadastrarRoleTask extends AsyncTask<String, Void, String> {
        Context ctx;

        CadastrarRoleTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Informação para Registro....");
        }

        @Override
        protected String doInBackground(String... params) {

            String registro_url = "http://searchonrole.esy.es/php/registroRole.php";

            try {
                URL url = new URL(registro_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String dados = URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nome, "UTF-8") + "&" +
                        URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8") + "&" +
                        URLEncoder.encode("custo", "UTF-8") + "=" + URLEncoder.encode(custo, "UTF-8") + "&" +
                        URLEncoder.encode("local", "UTF-8") + "=" + URLEncoder.encode(local, "UTF-8") + "&" +
                        URLEncoder.encode("descricao", "UTF-8") + "=" + URLEncoder.encode(descricao, "UTF-8") + "&" +
                        URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.getId(), "UTF-8");

                bufferedWriter.write(dados);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();

                return "Rolê Cadastrado com sucesso";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Erro ao cadastrar rolê";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            Toast.makeText(ctx, resultado, Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastrarRoleActivity.this, MeusRolesActivity.class));
            finish();
        }
    }
}
