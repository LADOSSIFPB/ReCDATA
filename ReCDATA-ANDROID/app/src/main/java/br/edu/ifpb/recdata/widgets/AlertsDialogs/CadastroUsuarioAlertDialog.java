package br.edu.ifpb.recdata.widgets.AlertsDialogs;

/**
 * Created by wtporto on 21/07/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import br.edu.ifpb.recdata.servicos.CadastraUsuarioAsyncTask;
import br.edu.ifpb.recdata.telas.TelaCadastraUsuario;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;

public class CadastroUsuarioAlertDialog {

    private TelaCadastraUsuario activity;

    public CadastroUsuarioAlertDialog(Activity activity) {

        this.activity = (TelaCadastraUsuario) activity;
    }

    public void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this.activity);

        alertDialog.setTitle("Confirma seu Cadastro");
       // alertDialog.setIcon(R.drawable.icon_cadastro_efetuado);
        alertDialog.setMessage("Deseja confirma seus cadastro?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        JSONObject jsonObject = activity.montarObjetoJSON();
                        Log.i("RecDATA - Objeto Usuário", jsonObject.toString());
                        CadastraUsuarioAsyncTask cadastrar = new CadastraUsuarioAsyncTask(
                                activity);
                        cadastrar.execute(jsonObject);

                    }
                }).setNegativeButton("Nao",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(activity, "Saindo da aplica��o",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                        activity.finish();
                    }
                });


        alertDialog.show();
    }
}
