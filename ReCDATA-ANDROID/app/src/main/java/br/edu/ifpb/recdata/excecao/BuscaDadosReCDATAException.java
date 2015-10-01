package br.edu.ifpb.recdata.excecao;

public class BuscaDadosReCDATAException extends ReCDATAException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1379348246842029958L;
	
	public BuscaDadosReCDATAException () {
		super(01,"Erro ao tentar buscar informações!");
	}
}
