package br.edu.ifpb.recdata.widgets.AlertsDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import br.edu.ifpb.R;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaReservar;


/**
 * Created by Wemerso n on 09/08/2015.
 */
public class ReservaComSucessoAlertDialog {

    private TelaReservar activity;


    public ReservaComSucessoAlertDialog(Activity activity){
        this.activity = (TelaReservar) activity;
    }

    public void showAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this.activity);

        alertDialog.setTitle("Reserva feita com Sucesso!");//TODO: troca este valor por constantes.
        alertDialog.setIcon(R.drawable.icon_pergunta);
        alertDialog.setMessage("Deseja reserva mais?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(activity, TelaListaFuncionalidades.class);
                       activity.startActivity(intent);
                       activity.finish();
                    }
                });


        alertDialog.setNegativeButton("Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(activity, "Saindo da Aplicação!!",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();


                    }
                });

        alertDialog.show();
    }

}
