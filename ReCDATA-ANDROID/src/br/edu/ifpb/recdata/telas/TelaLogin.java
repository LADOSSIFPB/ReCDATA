package br.edu.ifpb.recdata.telas;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.UsuarioLoginAsyncTask;
import br.edu.ifpb.recdata.util.*;

public class TelaLogin extends Activity implements OnClickListener {

	private EditText login;
	private EditText senha;
	private Button logarbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_login);

		login = (EditText) findViewById(R.id.LoginApp);
		senha = (EditText) findViewById(R.id.SenhaApp);
		
		// Login usuário
		logarbutton = (Button) findViewById(R.id.LoginButton);
		logarbutton.setOnClickListener(this);
		logarbutton.setEnabled(true);
		
		boolean ativo = ativaButton();
		Log.i("Ativo: ",String.valueOf(ativo) );
		TextView txtCadastrar = (TextView) findViewById(R.id.textview_cadastrar);

		// Cadastrar usuário
		txtCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent chamarCadastro = new Intent(TelaLogin.this,
						TelaCadastraUsuario.class);
				startActivity(chamarCadastro);
				finish();
			}
		});

	}

	private JSONObject logarUsuario() {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("login", login.getText().toString().trim());
			jsonObject.put("senha", senha.getText().toString().trim());

		} catch (JSONException e) {
			Log.e("ReCDATA", e.getMessage());
			// Toast para o usuário com erro mais suave.
		}
		return jsonObject;
	}

	public boolean ativaButton() {
		boolean isValido = false;
		String log = login.getText().toString().trim();
		String sen = senha.getText().toString().trim();
		Log.i("Senha : Login",String.valueOf(log)+String.valueOf(sen) );
		
		if (( log.length() > 1) && ( sen.length()> 1)) {
			isValido= true;
		} else {
			isValido = false;
		}
		return isValido;
	}

	@Override
	public void onClick(View arg0) {

		if ((Validacao.validaCampo(login) == true)
				&& (Validacao.validaCampo(senha) == true)) {

			JSONObject jsonObject = logarUsuario();

			UsuarioLoginAsyncTask usuarioLogar = new UsuarioLoginAsyncTask(this);
			usuarioLogar.execute(jsonObject);

		} else {
			
			if (Validacao.validaCampo(login) == false) {

				EditText login = (EditText) findViewById(R.id.LoginApp);
				login.setText("");

			} else {

				EditText senha = (EditText) findViewById(R.id.SenhaApp);
				senha.setText("");
			}

		}
	}
}
