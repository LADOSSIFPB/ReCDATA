package br.edu.ifpb.recdata.servicos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recdata.entity.Usuario;
import br.edu.ifpb.recdata.telas.TelaAbertura;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.SessionReCDATA;

/**
 * Created by Wemerso n on 02/08/2015.
 */
public class BuscaUsuarioByIdAsyncTask extends AsyncTask<JSONObject, Void, HttpResponse> {

    private TelaAbertura telaAbertura;
    GlobalState gs;
    public BuscaUsuarioByIdAsyncTask (Activity activity) {
        this.telaAbertura = (TelaAbertura) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HttpResponse doInBackground(JSONObject... jsonObjects) {


        // Enviar a requisi??o HTTP via GET.
        HttpResponse response = HttpService.sendJsonPostRequest(
                "/usuario/buscaidusuario", jsonObjects[0]);

        int httpCode = response.getStatusLine().getStatusCode();

        try {
            // Convers?o do response ( resposta HTTP) para String.
            String json = HttpUtil.entityToString(response);
            Log.i("ReCDATA Usuario>>>>>", "Resquest - POST: " + json);

            JSONArray jsonObjectUsuario = new JSONArray(json);
            if (jsonObjectUsuario.length() > 0) {
                if (httpCode > 200 || httpCode < 400) {

                        Usuario usuario = new Usuario();
                        usuario.setId(jsonObjectUsuario.getJSONObject(0).getInt("id"));
                        usuario.setNome(jsonObjectUsuario.getJSONObject(0).getString("nome"));
                        gs = (GlobalState) telaAbertura.getApplicationContext();
                        gs.setUsuario(usuario);
                }
            } else {
                if (httpCode >= 403) {
                    Toast.makeText(telaAbertura.getApplicationContext(),
                            Constantes.ERRO_LOGAR, Toast.LENGTH_SHORT).show();//TODO: trocar mensagem aqui!
                }

            }

        } catch (JSONException e) {
            Log.e("ReCDATA", "Error parsing data " + e.toString());
        }


        return response;
    }


}





