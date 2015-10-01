package br.edu.ifpb.recdata.widgets.AlertsDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import br.edu.ifpb.R.drawable;
import br.edu.ifpb.recdata.servicos.ReservarAsyncTask;
import br.edu.ifpb.recdata.telas.TelaReservar;


/**
 * Created by Wemerso n on 02/08/2015.
 */
public class ConfirmaReservaAlertDialog {


        private TelaReservar activity;


        public ConfirmaReservaAlertDialog(Activity activity){
            this.activity = (TelaReservar) activity;
        }

    public void showAlertDialog() {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    this.activity);

            alertDialog.setTitle("Reserva do Item");//TODO: troca este valor por constantes.
            alertDialog.setIcon(drawable.icon_pergunta);
            alertDialog.setMessage("Deseja Confirma sua reserva neste Horário?");
            alertDialog.setPositiveButton("Sim",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ReservarAsyncTask reservarAsyncTask = new ReservarAsyncTask(activity);
                            JSONObject jsonObject = activity.montarJsonReserva();
                            Log.i("Reserva:", jsonObject.toString());
                            reservarAsyncTask.execute(jsonObject);

                        }
                    });


            alertDialog.setNegativeButton("Não",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(activity, "Informe Um novo horário!",
                                    Toast.LENGTH_SHORT).show();
                            dialog.cancel();


                        }
                    });

            alertDialog.show();
        }






}
