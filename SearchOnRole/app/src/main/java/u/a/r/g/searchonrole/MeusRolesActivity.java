package u.a.r.g.searchonrole;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MeusRolesActivity extends AppCompatActivity {

    BancoRoles bancoRoles;
    private ListView lista;
    private static int position;
    TextView adicioneRoles;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_roles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        getMeusRoles();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeusRolesActivity.this, TodosRolesActivity.class));
                finish();
            }
        });
    }

    public void getMeusRoles() {
        new MeusRolesTask().execute();
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
            startActivity(new Intent(MeusRolesActivity.this, EditarUsuarioActivity.class));
            return true;
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(MeusRolesActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MeusRolesTask extends AsyncTask<String, Void, String> {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = "http://searchonrole.esy.es/php/json_get_meusRoles.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //Log.d("TESTE123", "1");

                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.getId(), "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            //Log.d("TESTE","+"+resultado+"+");
            if (!resultado.equals("")) {

                final List<Map<String, String>> listaRoles = new ArrayList<Map<String, String>>();
                Map<String, String> map;

                final String[] json_text = resultado.split(";");
                for (int i = 0; i < json_text.length - 1; i++) {
                    map = new HashMap<String, String>();
                    String[] json_item = json_text[i].split(":");
                    map.put("NOME", json_item[1]);
                    map.put("DATA", "Data: " + json_item[2]);
                    map.put("CUSTO", "Custo: R$" + json_item[3]);

                    if (LoginActivity.getId().equals("0") || LoginActivity.getId().equals(json_item[6])) {
                        map.put("ASTERISCO", "*");
                    } else {
                        map.put("ASTERISCO", "");
                    }

                    listaRoles.add(map);
                }
                map = new HashMap<String, String>();
                map.put("NOME", "");
                map.put("DATA", "");
                map.put("CUSTO", "");
                listaRoles.add(map);

                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listaRoles, R.layout.content_list_role,
                        new String[]{"NOME", "DATA", "CUSTO", "ASTERISCO"},
                        new int[]{R.id.nomeRole, R.id.dataRole, R.id.custoRole, R.id.asterisco});
                lista = (ListView) findViewById(R.id.listView);
                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position != listaRoles.size() - 1) {
                            Intent intent = new Intent(MeusRolesActivity.this, RoleActivity.class);
                            intent.putExtra("role", json_text[position]);
                            intent.addFlags(1);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }
    }
}
