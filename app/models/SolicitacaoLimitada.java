package models;

public class SolicitacaoLimitada extends Solicitacao {

	
	public SolicitacaoLimitada(Usuario usuario, String codigo) {
		super.usuario = usuario;
		super.codigo = codigo;
	}
	
	@Override
	public Usuario getUsuario() {
		return super.usuario;
	}

	@Override
	public String getCodigo() {
		return super.codigo;
	}
}
