package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Classe Representa uma viagem no sistema.
 * 
 * @author Marcus Vinicius, Gleyser Guimaraes e Silvano Albuquerque
 * @since 2014.2
 * @version 1.0
 */
@Entity
@Table(name = "TABELA_VIAGEM")
public class Viagem implements Comparable<Viagem>{

	@Id
	@GeneratedValue
	@Column(name = "ID_VIAGEM")
	private Long id;

	@Column(name = "DATA_VIAGEM")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name = "LOCAL_VIAGEM")
	private String local;

	@Column(name = "DESC_VIAGEM")
	private String descricao;

	@OneToOne
	private TipoDeViagem estrategia;

	@ManyToMany
	private List<Usuario> participantes = new ArrayList<Usuario>();

	@Transient
	private static final Integer LENGTH_MINIMO = 3;

	/**
	 * Contrutor vazio
	 */
	public Viagem() {
		super();
		participantes = new ArrayList<Usuario>();
	}

	/**
	 * Contrutor para viagem do tipo ABERTA.
	 * @param local - local da viagem
	 * @param descricao - breve descricao da viagem
	 * @param data - data da viagem
	 * @param participantes - colecao de participantes inscritos na viagem.
	 * @throws Exception - 	cada parametro sao verificados independentemente,
	 * 						contrutor apenas relanca as exception
	 */
	public Viagem(String local, String descricao, Date data, List<Usuario> participantes) throws Exception {
		super();
		isLocalValido(local);
		isDataValida(data);
		isDescricaoValida(descricao);
		this.estrategia = new ViagemAberta();
		isParticipantesValido(participantes);
	}

	/**
	 * Contrutor para viagem do tipo LIMITADA
	 * @param local - local da viagem.
	 * @param codigo - codigo que que torna a viagem limitada aos portadores do codigo.
	 * @param descricao - breve descricao da viagem.
	 * @param data - data da viagem
	 * @param participantes - colecao de participantes inscritos na viagem.
	 * @throws Exception - 	cada parametro sao verificados independentemente,
	 * 						contrutor apenas relanca as exception
	 */
	public Viagem(String local, String descricao, String codigo, Date data,
			List<Usuario> participantes) throws Exception {
		super();
		isLocalValido(local);
		isDataValida(data);
		isDescricaoValida(descricao);
		this.estrategia = new ViagemLimitada(codigo);
		isParticipantesValido(participantes);
	}

	/**
	 * Contrutor para viagem do tipo LIMITADA
	 * @param local - local da viagem.
	 * @param codigo - codigo que que torna a viagem limitada aos portadores do codigo.
	 * @param descricao - breve descricao da viagem.
	 * @param data - data da viagem
	 * @param participantes - colecao de participantes inscritos na viagem.
	 * @throws Exception - 	cada parametro sao verificados independentemente,
	 * 						contrutor apenas relanca as exception
	 */
	public Viagem(String local, String descricao, Date data, TipoDeViagem tipo,
			List<Usuario> participantes) throws Exception {
		super();
		isLocalValido(local);
		isDataValida(data);
		isDescricaoValida(descricao);
		this.estrategia = tipo;
		isParticipantesValido(participantes);
	}
	
	/**
	 * Metodo verifica se colecao de participantes e valida.
	 * @param participantes
	 * @throws Exception - caso seja null
	 */
	private void isParticipantesValido(List<Usuario> participantes)
			throws Exception {
		if (participantes == null) {
			throw new Exception("Coleção de Participantes Invalida!");
		} else {
			this.participantes = participantes;
		}
	}

	/**
	 * Metodo verifica se nome do local e valido.
	 * @param local - nome do local.
	 * @throws Exception - caso seja null ou menor do que 3 caracteres.
	 */
	private void isLocalValido(String local) throws Exception {
		if (local == null || local.trim().isEmpty()) {
			throw new Exception("Local nulo ou vazio!");
		} else if (local.length() < LENGTH_MINIMO) {
			throw new Exception("Local deve ter no Minimo 3 Caracteres!");
		} else {
			this.local = local;
		}
	}

	/**
	 * Metodo verifica se descricao da viagem e valida.
	 * @param descricao - descricao da viagem
	 * @throws Exception - caso seja null ou menor do que 3 caracteres.
	 */
	private void isDescricaoValida(String descricao) throws Exception {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new Exception("Descrição Nula ou Vazia!");
		} else if (descricao.length() < LENGTH_MINIMO) {
			throw new Exception("Descrição deve ter no Minimo 3 Caracteres!");
		} else {
			this.descricao = descricao;
		}
	}

	/**
	 * Metodo verifica se data e valida.
	 * @param data - data da viagem
	 * @throws Exception - caso seja null ou passada.
	 */
	private void isDataValida(Date data) throws Exception {
		if (data == null) {
			throw new Exception("Data Nula ou Vazia!");
		}
		// representam as datas, ignorando as horas, minutos e segundos
		long timeDataAtual = Calendar.getInstance().getTimeInMillis()/ (1000 * 60 * 60 * 24);
		long timeData = data.getTime()/ (1000 * 60 * 60 * 24);
		
		//} else if (data.compareTo(Calendar.getInstance().getTime()) < 0) {
		if(timeData < timeDataAtual){
			throw new Exception("Data já Ultrapassada!");
		} else {
			this.data = data;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) throws Exception {
		isDataValida(data);
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) throws Exception{
		isLocalValido(local);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws Exception{
		isDescricaoValida(descricao);
	}

	public TipoDeViagem getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(TipoDeViagem estrategia) {
		this.estrategia = estrategia;
	}

	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) throws Exception{
		isParticipantesValido(participantes);
	}

	/**
	 * Metodo adiciona participante a viagem
	 * @param codigo - caso a viagem seja limitada
	 * @param usuario - usuario canditado a viagem
	 * @return true - caso usuario seja adicionado e false caso contrario.
	 */
	public boolean adicionarParticipante(String codigo, Usuario usuario){
		return estrategia.adicionarParticipante(participantes, codigo, usuario);
	}

	/**
	 * Muda o tipo da Viagem para Aberta
	 * @throws Exception 
	 */
	public void mudarParaAberta() {
		estrategia = new ViagemAberta();
	}

	/**
	 * Muda tipo da viagem para Limitada.
	 * @param codigo - codigo que torna limitada
	 * @throws Exception - caso codigo seja null ou menor que 3 caracteres
	 */
	public void mudarParaLimitada(String codigo) throws Exception {
		estrategia = new ViagemLimitada(codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, estrategia, local);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Viagem)) {
			return false;
		}
		Viagem other = (Viagem) obj;
		return other.hashCode() == hashCode();
	}

	@Override
	public String toString() {
		return "Viagem [Data: " + data + ", Local: " + local + ", Estado: "
				+ estrategia + "]";
	}

	@Override
	public int compareTo(Viagem o) {
		return this.data.compareTo(o.getData());
	}
	
	public int countParticipantes(){
		return participantes.size();
	}
}