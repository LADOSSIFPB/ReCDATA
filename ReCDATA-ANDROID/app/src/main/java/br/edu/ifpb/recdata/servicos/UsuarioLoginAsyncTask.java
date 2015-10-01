package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.entity.Usuario;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.SessionReCDATA;

public class UsuarioLoginAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	private TelaLogin telaLogin;
	private JSONObject jsonObject;
	GlobalState gs;
	private SessionReCDATA sessionRecdata;
	private ProgressDialog progressDialog;

	public UsuarioLoginAsyncTask(Activity activity) {
		this.telaLogin = (TelaLogin) activity;
		sessionRecdata = new SessionReCDATA(this.telaLogin);
	}

	public void setResult(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getResult() {
		return this.jsonObject;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = ProgressDialog.show(telaLogin,"Por Favor, espere...","Fazendo Login...",true);
		progressDialog.setCancelable(false);


	}

	@Override
	protected HttpResponse doInBackground(JSONObject... jsonObjects) {

		// Enviar a requisi��o HTTP via GET.
		HttpResponse response = HttpService.sendJsonPostRequest(
				"/usuario/verificar", jsonObjects[0]);
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

		try {
			// Convers�o do response ( resposta HTTP) para String.
			String json = HttpUtil.entityToString(response);
			Log.i("ReCDATA", "Resquest - POST: " + json);

			JSONObject jsonObject = new JSONObject(json);

			if (httpCode > 200 && httpCode < 400) {

				Usuario usuario = new Usuario();
				usuario.setId(jsonObject.getInt("id"));
				usuario.setNome(jsonObject.getString("nome"));
				gs = (GlobalState) telaLogin.getApplication();
				gs.setUsuario(usuario);

				if (gs.getUsuario() != null) {

					sessionRecdata.createLoginSession(gs.getUsuario().getId());
					Log.i("Sesion:",sessionRecdata.toString());
					Intent intent = new Intent(telaLogin,
							TelaListaFuncionalidades.class);
					telaLogin.startActivity(intent);
					telaLogin.finish();
				}

				//TODO: trocar este toast por um dialog
				Toast.makeText(telaLogin.getApplicationContext(),
						Constantes.USUARIO_EXISTE + usuario.getNome(),
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(telaLogin,
						TelaListaFuncionalidades.class);
				telaLogin.startActivity(intent);
				telaLogin.finish();

			} else {
				if (httpCode >= 403) {
					Toast.makeText(telaLogin.getApplicationContext(),
							Constantes.ERRO_LOGAR, Toast.LENGTH_SHORT).show();
				}

			}

		} catch (JSONException e) {
			Log.e("ReCDATA", "Error parsing data " + e.toString());
		}
		progressDialog.dismiss();
	}
}
