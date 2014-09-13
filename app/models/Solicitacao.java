package models;

/**
 * Classe Solicitacao
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
public abstract class Solicitacao {
	
	protected Usuario usuario;
	protected String codigo;
	
	/**
	 * Metodo retorna o Usuario da Solicitacao
	 * @return
	 */
	public abstract Usuario getUsuario();
	
	/**
	 * Metodo retorna o Codigo da Solicitacao
	 * @return
	 */
	public abstract String  getCodigo();
}
