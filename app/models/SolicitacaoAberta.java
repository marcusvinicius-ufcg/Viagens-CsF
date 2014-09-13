package models;


public class SolicitacaoAberta extends Solicitacao{
	
	public SolicitacaoAberta(Usuario usuario) {
		super.usuario = usuario;
	}
	
	@Override
	public Usuario getUsuario() {
		return super.usuario;
	}

	@Override
	public String getCodigo() {
		throw new UnsupportedOperationException("Solicitacao Aberta Não Possui Código.");
	}
}