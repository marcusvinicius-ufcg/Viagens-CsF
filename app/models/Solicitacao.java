package models;

/**
 * Classe abstrata Solicitacao. Funciona como uma mascara de dados.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
public abstract class Solicitacao {
	
	protected Usuario usuario;
	
	/**
	 * Metodo retorna o Usuario da Solicitacao.
	 * @return
	 */
	public abstract Usuario getUsuario();
	
	/**
	 * Metodo retorna o Codigo da Solicitacao.
	 * Cada subclasse definira a visibilidade do metodo.
	 * 
	 * @return
	 */
	public abstract String  getCodigo();
}
