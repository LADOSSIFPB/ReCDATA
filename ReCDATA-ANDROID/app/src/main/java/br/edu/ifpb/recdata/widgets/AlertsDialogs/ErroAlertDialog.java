package br.edu.ifpb.recdata.widgets.AlertsDialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wtporto on 22/07/2015.
 */
public class ErroAlertDialog  {

    private Activity activity;

    public ErroAlertDialog(Activity activity) {

        this.activity = activity;
    }

    public void showAlertDialog() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(
                this.activity);

        alertDialog.setTitle("Desculpe Erro Inesperado na aplicação");
       // alertDialog.setIcon(R.drawable.icon_erro_homer_doh);//trocar icone
        alertDialog.setMessage("Deseja realizar novamente a aperação?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(activity,
                       //  /       MainActivity2Activity.class);//seta a activy proxima
                       // activity.startActivity(intent);
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
