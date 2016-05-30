package u.a.r.g.searchonrole;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarRoleActivity extends AppCompatActivity {

    BancoRoles crud;
    EditText nome;
    EditText data;
    EditText custo;
    EditText descricao;
    EditText local;
    EditText bebidas;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crud = new BancoRoles(getBaseContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        Button btnCadastrar = (Button)findViewById(R.id.button_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    nome = (EditText) findViewById(R.id.EDtituloRole);
                    data = (EditText) findViewById(R.id.EDdata);
                    custo = (EditText) findViewById(R.id.EDcusto);
                    descricao = (EditText) findViewById(R.id.EDdescricao);
                    local = (EditText) findViewById(R.id.EDlocal);
                    bebidas = (EditText) findViewById(R.id.EDbebidas);

                    if(isEmpty(nome) || isEmpty(data) || isEmpty(descricao)) {
                        showDialog("Dados Incompletos!", "Preencha todos os campos obrigatórios");
                    }
                    else {
                    Role role = new Role(nome.getText().toString(),
                            data.getText().toString(),
                            Float.parseFloat(custo.getText().toString()),
                            descricao.getText().toString(),
                            local.getText().toString(),
                            bebidas.getText().toString());

                    resultado = crud.insereRole(role);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                    finish();
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

    private boolean isEmpty(EditText myEditText) {
        return myEditText.getText().toString().trim().length() == 0;
    }

    public void showDialog(String title, String message){
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

}
