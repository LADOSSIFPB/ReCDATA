package br.edu.ifpb.recdata.servicos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recdata.telas.TelaReservar;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.widgets.AlertsDialogs.ConfirmaReservaAlertDialog;
import br.edu.ifpb.recdata.widgets.AlertsDialogs.IsReservaAlertDialog;

/**
 * Created by Wemerso n on 18/08/2015.
 */
public class IsExisteReservaAsyncTask  extends
        AsyncTask<JSONObject, Void, HttpResponse> {

    private Activity activity;
    private ProgressDialog progressDialog;

    public IsExisteReservaAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity,"Por Favor...","Verificando se n�o h� reserva nesse mesmo Hor�rio...",true);
        progressDialog.setCancelable(true);
    }

    @Override
    protected HttpResponse doInBackground(JSONObject... jsonObjects) {

        // Enviar a requisi��o HTTP via GET.
        HttpResponse response = HttpService.sendJsonPostRequest("/reserva/isexitereserva",
                jsonObjects[0]);
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

                boolean retorno = Boolean.valueOf(json);

                     if (retorno){
                         //Chamar isso aqui dentro da IsEXISTERESERVA
                        ConfirmaReservaAlertDialog confirmaReserva = new ConfirmaReservaAlertDialog(activity);
                        confirmaReserva.showAlertDialog();
                     }else{
                         IsReservaAlertDialog isReservaAlertDialog = new IsReservaAlertDialog(activity);
                         isReservaAlertDialog.showAlertDialog();
                     }
            } catch (Exception e) {
                Log.e("RecDATA", "Erro na listagem do item: " + e.getMessage());
            }
            progressDialog.dismiss();
        }
    }
}