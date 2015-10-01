package br.edu.ifpb.recdata.excecao;

public abstract class ReCDATAException extends Exception{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String messagen;
	private int cdErro;

	public ReCDATAException(){
	
		super();
		//chamar AlertDialog
		//colocar todas essa messagens em um hasmap
	}

	public ReCDATAException(int cdErro, String messagen) {
		setCdErro(cdErro);
		setMessagen(messagen);
	}
	
	public String getMessagen(){
		return messagen;
	}

	public void setMessagen(String novoMessagen){
		messagen = novoMessagen;
	}

	public int getCdErro(){
		return cdErro;
	}

	public void setCdErro(int novoCdErro){
		cdErro = novoCdErro;
	}
	
	
	
}
