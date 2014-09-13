package models;

/**
 * Sub-Classe Solicitacao Limitada.
 * Funciona como uma mascara de dados.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
public class SolicitacaoLimitada extends Solicitacao {

	private String codigo;
	
	public SolicitacaoLimitada(Usuario usuario, String codigo) {
		super.usuario = usuario;
		this.codigo = codigo;
	}
	
	@Override
	public Usuario getUsuario() {
		return super.usuario;
	}

	@Override
	public String getCodigo() {
		return this.codigo;
	}
}
