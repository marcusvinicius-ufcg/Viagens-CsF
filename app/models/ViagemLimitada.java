package models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Subclasse representa a estrategia limitada, ao adicionar participante
 * o mesmo devera informar um codigo para se autenticado com o codigo da estrategia.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
@Entity
@DiscriminatorValue("VL")
@Table(name="VIAGEM_STRATEGY_LIMITADA")
public class ViagemLimitada extends TipoDeViagem{

	@Column(name="COD_VIAGEM_LIMITADA")
	private String codigo;
	
	@Transient
	private static final Integer LENGTH_MINIMO = 3;

	public ViagemLimitada() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Construtor da Subclasse, o mesmo recebe o codigo da estrategia.
	 * Onde cada participante antes de se participar do evento tera que informar.
	 * @param codigo - codigo da estrategia.
	 * @throws Exception - caso o codigo seja null ou menor que 3 caracteres.
	 */
	public ViagemLimitada(String codigo) throws Exception {
		isCodigoValido(codigo);
	}
	
	@Override
	public boolean adicionarParticipante(Collection<Usuario> participantes, String codigo, Usuario usuario) {
		//verificando se codigo sao iguais
		if(this.codigo.equals(codigo) && !participantes.contains(usuario)){
			return participantes.add(usuario);
		}else{
			return false;	
		}
	}
	
	/**
	 * Metodo retorna o codigo da estrategia
	 * @return String representando o codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo define o codigo para estrategia
	 * @param codigo
 	 * @throws Exception - caso o parametro se null ou menor que 3 caracteres
	 */
	public void setCodigo(String codigo) throws Exception {
		isCodigoValido(codigo);
	}
	
	/**
	 * Metodo verifica se o paramentro e valido para ser um codigo dessa estrategia.
	 * @param codigo
	 * @throws Exception - caso o parametro se null ou menor que 3 caracteres
	 */
	private void isCodigoValido(String codigo) throws Exception {
		if (codigo == null || codigo.trim().isEmpty()) {
			throw new Exception("Código Nulo ou Vazio!");
		} else if (codigo.length() < LENGTH_MINIMO) {
			throw new Exception("Código deve ter no Minimo 3 Caracteres!");
		} else {
			this.codigo = codigo;
		}
	}

	@Override
	public String toString() {
		return new String("LIMITADA");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof ViagemLimitada)) {
			return false;
		}
		ViagemLimitada other = (ViagemLimitada) obj;
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}
}
