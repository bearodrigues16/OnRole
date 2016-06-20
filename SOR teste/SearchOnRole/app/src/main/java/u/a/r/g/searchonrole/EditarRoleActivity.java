package u.a.r.g.searchonrole;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditarRoleActivity extends AppCompatActivity {

    BancoRoles bancoRoles;
    private EditText nome;
    private EditText data;
    private EditText custo;
    private EditText local;
    private EditText descricao;
    private Button  botaorSalvar;
    private String resultado;
    private int roleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        bancoRoles = BancoRoles.getInstance(getBaseContext());

        List<Role> roles = bancoRoles.getTodosRoles();

        nome = (EditText)findViewById(R.id.EDtituloRole);
        data = (EditText)findViewById(R.id.PRData);
        custo = (EditText)findViewById(R.id.PRCusto);
        local = (EditText)findViewById(R.id.PRLocal);
        descricao = (EditText)findViewById(R.id.PRDescricao);

        botaorSalvar = (Button)findViewById(R.id.button_salvar);

        nome.setText(roles.get(MeusRolesActivity.getPosition()).getNome());
        data.setText(roles.get(MeusRolesActivity.getPosition()).getData());
        custo.setText(String.valueOf(roles.get(MeusRolesActivity.getPosition()).getCusto()));
        local.setText(roles.get(MeusRolesActivity.getPosition()).getLocal());
        descricao.setText(roles.get(MeusRolesActivity.getPosition()).getDescricao());
        roleId = roles.get(MeusRolesActivity.getPosition()).getId();

        botaorSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = (EditText) findViewById(R.id.EDtituloRole);
                data = (EditText) findViewById(R.id.PRData);
                custo = (EditText) findViewById(R.id.PRCusto);
                descricao = (EditText) findViewById(R.id.PRDescricao);
                local = (EditText) findViewById(R.id.PRLocal);

                if(isEmpty(nome) || isEmpty(data) || isEmpty(custo) || isEmpty(descricao)) {
                    showDialog("Dados Incompletos!", "Preencha todos os campos obrigatórios");
                }

                else {
                    Role role = new Role(nome.getText().toString(),
                            data.getText().toString(),
                            Float.parseFloat(custo.getText().toString()),
                            descricao.getText().toString(),
                            local.getText().toString());
                    role.setId(roleId);

                    resultado = bancoRoles.editarRole(role);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                    finish();
            }}
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
