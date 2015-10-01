package br.edu.ifpb.recdata.excecao;

public class LoginReCDATAException extends ReCDATAException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public LoginReCDATAException()
	{
		super(01, "Login Ou Senha Invalidos, tentar novamente a Operação??");
		//chamar o alertdialog aqui!!


	}
}
