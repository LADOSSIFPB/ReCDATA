package br.edu.ifpb.recdata.widgets.AlertsDialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wtporto on 29/07/2015.
 */
public class BuscaDadosReCDATAExceptionAlertDialog {

    private Activity activity;
    public BuscaDadosReCDATAExceptionAlertDialog(Activity activity) {

        this.activity = activity;
    }

    public void showAlertDialog() {
        android.app.AlertDialog.Builder alertDialog;
        alertDialog = new android.app.AlertDialog.Builder(
                this.activity);

        alertDialog.setTitle("Erro ao tentar busca as Informações!");
      //  alertDialog.setIcon(R.drawable.icon_user_login);//mudar icone
        alertDialog.setMessage("Deseja tentar novamente!");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       //seta a activy proxima
                      //  activity.startActivity(intent);
                       // activity.finish();
                    }
                });


        alertDialog.setNegativeButton("Nao",
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
