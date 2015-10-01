package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.widgets.AlertsDialogs.ReservaComSucessoAlertDialog;
//import br.edu.ifpb.recdata.widgets.AlertsDialogs.ConfirmaReservaAlertDialog;

public class ReservarAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	private Activity activity;
	private ProgressDialog progressDialog;

	public ReservarAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = ProgressDialog.show(activity,"Por Favor, espere...","Enviando dados para o servidor...",true);
		progressDialog.setCancelable(false);


	}

	@Override
	protected HttpResponse doInBackground(JSONObject... jsonObjects) {

		// Enviar a requisi��o HTTP via GET.
		HttpResponse response = HttpService.sendJsonPostRequest(
				"/reserva/cadastrar", jsonObjects[0]);
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

	  if (httpCode > 200 && httpCode < 400) {
			try {
				// Convers�o do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA ", "Resquest - POST: " + json);

				new JSONObject(json);

				if (httpCode == HttpStatus.SC_CREATED) {

					ReservaComSucessoAlertDialog reservaComSucessoAlertDialog = new ReservaComSucessoAlertDialog(activity);
					reservaComSucessoAlertDialog.showAlertDialog();
				} else {
					Toast.makeText(activity.getApplicationContext(),Constantes.RESERVA_NAO_CONCLUIDA, Toast.LENGTH_SHORT)
							.show();
				}

			} catch (JSONException e) {
				Log.e("RecDATA", "Erro na reserva do item: " + e.getMessage());
			}
	
		}
			progressDialog.dismiss();
		}
}
