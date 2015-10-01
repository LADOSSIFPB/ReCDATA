package br.edu.ifpb.recdata.telas;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;

public class TelaQrCode extends Activity implements OnClickListener {
	public static final int REQUEST_CODE = 0;

	private TextView txResult;
	private Button reservaButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ler_qr);

		txResult = (TextView) findViewById(R.id.txResult);
		reservaButton = (Button) findViewById(R.id.reservaQr);
		reservaButton.setOnClickListener(this);
		reservaButton.setEnabled(false);
	}

	public boolean ativaButton() {
		boolean isValido = false;
		String result = txResult.getText().toString().trim();

		if (( result.length() > 1) && (result != "Resultado:")) {
			isValido= true;
		} else {
			isValido = false;
		}
		return isValido;
	}

	public void callZXing(View view) {
		Intent it = new Intent(TelaQrCode.this,
				com.google.zxing.client.android.CaptureActivity.class);
		startActivityForResult(it, REQUEST_CODE);
		reservaButton.setEnabled(ativaButton());

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
			txResult.setText(" Item(Código): " + data.getStringExtra("SCAN_RESULT"));
		}
	}

	public int capturaId() {
		int id;
		String valorText = (String) txResult.getText();
		String vet[] = valorText.split(":");
		valorText = vet[1];
		valorText = valorText.trim();
		id = Integer.parseInt(valorText);

		return id;
	}

	@Override
	public void onClick(View arg0) {

		// monta objeto e envia por parametro para o bundle
		Item item = new Item(0,null);
		int id = capturaId();
		item.setId(id);

		// monta bundle para envia para tela de reserva;
		Bundle params = new Bundle();
		params.putSerializable("Item", (Serializable) item);

		// criar uma novo objeto Intent que chama a outra activity
		Intent chamaTelaReserva = new Intent(TelaQrCode.this,
				TelaReservar.class);
		chamaTelaReserva.putExtras(params);
		startActivity(chamaTelaReserva);

	}

}

/*
 
 
 
 */
