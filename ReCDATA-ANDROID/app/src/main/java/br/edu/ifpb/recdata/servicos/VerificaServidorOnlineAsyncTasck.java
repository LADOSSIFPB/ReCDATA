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
import br.edu.ifpb.recdata.excecao.HttpServiceException;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.SessionReCDATA;
import br.edu.ifpb.recdata.widgets.AlertsDialogs.SemConexaoAlertDialog;

public class VerificaServidorOnlineAsyncTasck extends
		AsyncTask<Void, Void, JSONObject> {

	private Activity activity;
	private SessionReCDATA session;
	private ProgressDialog progressDialog;
	private boolean existeConexao = true;

	public VerificaServidorOnlineAsyncTasck(Activity activity) {

		this.activity = activity;
		session = new SessionReCDATA(this.activity);
	}

	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(activity, "",	"", true);
		progressDialog.setCancelable(false);

	}

	@Override
	protected JSONObject doInBackground(Void... params) {

		JSONObject serverJsonObject = null;

		try {

			// Enviar a requisi��o HTTP via GET.
			HttpService httpService = new HttpService(this.activity);
			HttpResponse response = httpService
					.sendGETRequest("/servicos/servidorOnline/");

			// Verificar se o servidor respondeu.
			if (response != null) {
				// Convers�o do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA", "Resquest - GET: " + json);

				serverJsonObject = new JSONObject(json);
			}
		} catch (JSONException e) {

			Log.e("ReCDATA", "Parsing data: " + e.getMessage());

		} catch (HttpServiceException e) {

			Log.e("ReCDATA", e.getMessage());

			this.existeConexao = false;
		}

		return serverJsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject serverJsonObject) {

		if (this.existeConexao && serverJsonObject != null) {
			try {
				 boolean online = serverJsonObject.getBoolean("online");

				Log.i("ReCDATA", "Servidor conectado: " + online);

				if (online == true) {
					 if(session.checkLogin() == true) {
						 //chamandado o serviço que traz novamente os dados do usuário do banco!

						 JSONObject idUsuarioJsonObject = new JSONObject();
						 idUsuarioJsonObject.put("id",session.getUserDetails());

						 BuscaUsuarioByIdAsyncTask buscaUsuarioByIdAsyncTask = new
								 BuscaUsuarioByIdAsyncTask(activity);
						 buscaUsuarioByIdAsyncTask.execute(idUsuarioJsonObject);

							 Intent intent = new Intent(activity, TelaListaFuncionalidades.class);
						     activity.startActivity(intent);
						 	 activity.finish();

					 }else{
						 Intent intent = new Intent(activity, TelaLogin.class);
						activity.startActivity(intent);
						activity.finish();
					}
				} else {

					String texto = "N�o foi poss�vel estabelecer a conex�o: Servidor em Manuten��o";
					int duracao = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(
							activity.getApplicationContext(), texto, duracao);
					toast.show();
				}

			} catch (JSONException e) {

				Log.e("ReCDATA", "Error parsing data " + e.toString());
			}
		} else {
			SemConexaoAlertDialog semConexao = new SemConexaoAlertDialog(
					activity);
			semConexao.showAlertDialog();

		}

					progressDialog.dismiss();

	}

	public boolean getIsconnet(){
		return this.existeConexao;
	}
}
