package models;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Subclasse representa a estrategia aberta, qualquer usuario podera
 * ser adicionado a colecao de participantes.
 *  
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
@Entity
@DiscriminatorValue("VA")
@Table(name="VIAGEM_STRATEGY_ABERTA")
public class ViagemAberta extends TipoDeViagem{
	
	/**
	 * Contrutor da subclasse
	 */
	public ViagemAberta() {
		super();
	}
	
	@Override
	public boolean adicionarParticipante(Collection<Usuario> participantes, Solicitacao solicitacao){
		//verificando se usuario ja esta na colecao de participantes
		if(participantes.contains(solicitacao.getUsuario())){
			return false;
		//caso nao esteja adiciona a colecao
		}else{
			return participantes.add(solicitacao.getUsuario());
		}
	}
	
	@Override
	public String toString() {
		return new String("ABERTA");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof ViagemAberta)) {
			return false;
		}
		ViagemAberta other = (ViagemAberta) obj;
		if (obj.toString() == null) {
			if (other.toString() != null) {
				return false;
			}
		} else if (!toString().equals(other.toString())) {
			return false;
		}
		return true;
	}

	@Override
	public String getCodigo() {
		throw new UnsupportedOperationException("Viagem Aberta Não Possui Código.");
	}
}
