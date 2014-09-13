package models;

import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Classe abstrata representa a estrategia para
 * adicionar participantes a uma viagem.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="VIAGEM_STRATEGY_TYPE")
@Table(name="VIAGEM_STRATEGY")
public abstract class TipoDeViagem {
	
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * Metodo retorna o ID
	 * @return Long representando o ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Metodo define um ID para o Objeto
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo adiciona participante a colecao passada como parametro
	 * 
	 * @param participantes - colecao de participantes.
	 * @param codigo - caso a viagem seja limitado.
	 * @param usuario - usuario a ser adicionado na colecao de participante.
	 * @return true - caso usuario seja adicionado na colecao, false caso contrario.
	 */
	public abstract boolean adicionarParticipante(Collection<Usuario> participantes, Solicitacao solicitacao);
	
	abstract String getCodigo();
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object obj);
	
}
