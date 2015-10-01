package br.edu.ifpb.recdata.widgets.AlertsDialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import br.edu.ifpb.R;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaReservar;

/**
 * Created by wtporto on 22/07/2015.
 */
public class IsReservaAlertDialog  {

    private Activity activity;

    public IsReservaAlertDialog(Activity activity) {

        this.activity = activity;
    }

    public void showAlertDialog() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(
                this.activity);

        alertDialog.setTitle("Desculpe mas exite uma reserva desse item neste periodo.");
        alertDialog.setIcon(R.drawable.icon_pergunta);//trocar icone
        alertDialog.setMessage("Deseja realizar novamente a aperação?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity,
                               TelaReservar.class);//seta a activy proxima
                        activity.startActivity(intent);
                        activity.finish();
                    }
                });


        alertDialog.setNegativeButton("Nao",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity,
                                TelaListaFuncionalidades.class);//seta a activy proxima
                        activity.startActivity(intent);
                        activity.finish();
                    }
                });

        alertDialog.show();

    }
}