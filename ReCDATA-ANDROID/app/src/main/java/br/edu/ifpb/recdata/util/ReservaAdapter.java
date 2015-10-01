package br.edu.ifpb.recdata.util;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.ReservaItem;

public class ReservaAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<ReservaItem> lista;
	
	
	public ReservaAdapter(Context busca, ArrayList<ReservaItem> itens) {
		this.context=busca;
		this.lista=  itens;
		
	}


	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		ReservaItem reserva =lista.get(position);
		View layout;
		
		if (convertView == null){
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.listactivity_reserva_user,null);
		}
		else{
			layout = convertView;
		}
	
		String dataHoraInicial = Metodos.converteTomillInDate(reserva.getHoraDataInicio().toString());
		String dataHoraFinal = Metodos.converteTomillInDate(reserva.getHoraDataFim().toString());
		
		TextView descricao = (TextView) layout.findViewById(R.id.textView_descricaoItem);
		descricao.setText("Descriçãoo: "+String.valueOf(reserva.getItem().getDescricao()));
	
		TextView horaDataInicio = (TextView) layout.findViewById(R.id.text_view_horaDataInicio);
		horaDataInicio.setText("Data e Hora Inicio: "+dataHoraInicial);
	
		TextView horaDataFim = (TextView) layout.findViewById(R.id.text_view_horaDataFim);
		horaDataFim.setText("Data e Hora Fim: "+dataHoraFinal);
		Log.i("Onservação:",reserva.getObservacao());
		TextView observacao = (TextView) layout.findViewById(R.id.text_view_observacao);
		//TODO: verificar está validação!
		if((reserva.getObservacao().equals(null)) || ( reserva.getObservacao().toString() == "" ) ){
			observacao.setText("Observação: Nada especificado na reseva!");
			Log.i(">>>>>> 1�Cheei no if: ",observacao.getText().toString());
		}else{
			observacao.setText("Observação: "+String.valueOf(reserva.getObservacao().toString().trim()));
			Log.i(">>>>>> 2�Cheei no if: ", observacao.getText().toString());
		}


		ImageView imagem = (ImageView) layout.findViewById(R.id.imageView_iconReserva);
		imagem.setImageResource(reserva.getImagem(1));
		
		return layout;
	}

}