package br.edu.ifpb.recdata.excecao;

public class ReservaItemReCDATAException extends ReCDATAException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public ReservaItemReCDATAException()
	{
		super(03,"Erro ao tentar fazer Reserva do item!");
	}
}
