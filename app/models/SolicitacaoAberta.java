package models;

/**
 * Sub-Classe Solicitacao Aberta.
 * Funciona como uma mascara de dados.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
public class SolicitacaoAberta extends Solicitacao{
	
	public SolicitacaoAberta(Usuario usuario) {
		super.usuario = usuario;
	}
	
	@Override
	public Usuario getUsuario() {
		return super.usuario;
	}

	/**
	 * Herança Negada. Solicitacao Aberta nao possui codigo.
	 * Metodo visivel apenas no package.
	 */
	@Override
	public String getCodigo() {
		throw new UnsupportedOperationException("Solicitacao Aberta Não Possui Código.");
	}
}