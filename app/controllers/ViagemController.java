package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.TipoDeViagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;
import play.*;
import views.html.*;

@SuppressWarnings("unused")
public class ViagemController extends Controller {

	// INICIO DA DECLARACAO DE VARIAVEIS
	private static GenericDAO dao = new GenericDAOImpl();
	private static final String MINHAS_VIAGENS = new String("Minhas Viagens - Viagens CsF");
	private static final String CADASTRO_VIAGEM = new String("Cadastro de Viagem - Viagens CsF");
	private static final String AGENDA_VIAGEM = new String("Agenda de Viagens - Viagens CsF");
	private final static Form<Viagem> VIAGEM_FORM = form(Viagem.class);

	// FIM DA DECLARACAO DE VARIAVEIS

	@Transactional
	public static Result salvarAlteracao(Long id, String email){
		
		Usuario usuario = getUser(email);
		
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}
		
		Viagem viagem;
		try {
			String tipo = form().bindFromRequest().get("TIPO_VIAGEM");
			String local = form().bindFromRequest().get("LOCAL_VIAGEM");
			String codigo = form().bindFromRequest().get("CODIGO_VIAGEM");
			Date data = getDataFormatada(form().bindFromRequest().get("DATA_VIAGEM"));
			
			viagem = new Viagem();
			viagem.setData(data);
			viagem.setLocal(local);

			if (tipo.equals("LIMITADA")) {
				viagem.setEstrategia((ViagemLimitada) persist(new ViagemLimitada(codigo)));
			} else if (tipo.equals("ABERTA")) {
				viagem.setEstrategia((ViagemAberta) persist(new ViagemAberta()));
			} else {
				throw new Exception("Selecione o tipo de Viagem");
			}
			
			Viagem newViagem;
			
			if(viagem.equals(dao.findByEntityId(Viagem.class, id))){
				return redirect(controllers.routes.ViagemController.getMinhasViagens(email));
			}else{
				newViagem = dao.findByEntityId(Viagem.class, id);
				newViagem.setEstrategia(viagem.getEstrategia());
				newViagem.setData(viagem.getData());
			}
			
			dao.merge(newViagem);
			dao.merge(usuario);
			dao.flush();
			
		} catch (Exception e) {
			flash("erro", e.getMessage());
			return redirect(controllers.routes.ViagemController.getMinhasViagens(email));
		}
		flash("success", "Mudanca efetuada com sucesso.");
		return redirect(controllers.routes.ViagemController.getMinhasViagens(email));
	}
	
	@Transactional
	private static Usuario getUser(String email) {
		return  dao.findByEntityId(Usuario.class, email);
	}

	@Transactional
	public static Result getMinhasViagens(String email) {
		Usuario usuario = getUser(email);
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}
		return ok(minhasViagens.render(MINHAS_VIAGENS, usuario));
	}

	@Transactional
	public static Result cadastrarViagem(String email) {
		Usuario usuario = getUser(email);
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}

		return ok(cadastroDeViagem.render(CADASTRO_VIAGEM, usuario, VIAGEM_FORM));
	}
	
	@Transactional
	public static Result usuarios(String email) {
		Usuario usuario = getUser(email);
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}

		return ok(usuarios.render("Usuarios", usuario));
	}
	
	@Transactional
	public static Result agenda(String email) {
		Usuario usuario = getUser(email);
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}

		return ok(agenda.render("Agenda", usuario));
	}

	@Transactional
	public static List<TipoDeViagem> getEstados() {
		return dao.findAllByClassName("TipoDeViagem");
	}

	@Transactional
	public static List<Viagem> getViagens() {
		List<Viagem> viagens = dao.findAllByClassName("Viagem");
		Collections.sort(viagens);
		
		return viagens;
	}
	
	@Transactional
	public static List<Usuario> getUsuarios() {
		return dao.findAllByClassName("Usuario");
	}
	
	@Transactional
	public static List<Viagem> viagensQueParticipa(String email) {
		Usuario usuario = getUser(email);
		if (usuario == null) {
			controllers.routes.LoginController.index();
		}
		int aux = 0;
		
		List<Viagem> viagens = new ArrayList<Viagem>();
		
		for(Viagem v : getViagens()){
			if(v.getParticipantes().contains(usuario) && !viagens.contains(v)){
				viagens.add(v);
			}
		}
		return viagens;
	}
	
	@Transactional
	public static List<Viagem> getAgenda(String email) {
		verificaUsuario();
		
		List<Viagem> viagens = viagensQueParticipa(email);
		
		Collections.sort(viagens);
		
		return viagens;
	}
	
	private static void verificaUsuario(){
		Usuario usuario = getUser(session("email"));
		if (usuario == null) {
			controllers.routes.LoginController.index();
		}
	}

	@Transactional
	public static Result cadastrarNovaViagem(String email) {
		Usuario usuario = getUser(email);

		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}
		Viagem viagem;
		try {
			String tipo = form().bindFromRequest().get("TIPO_VIAGEM");
			String codigo = form().bindFromRequest().get("CODIGO_VIAGEM");
			Date data = getDataFormatada(form().bindFromRequest().get("DATA_VIAGEM"));
			String descricao = form().bindFromRequest().get("DESCRICAO_VIAGEM");
			String local = form().bindFromRequest().get("LOCAL_VIAGEM");

			viagem = new Viagem();
			viagem.setLocal(local);
			viagem.setDescricao(descricao);
			viagem.setData(data);

			if (tipo.equals("LIMITADA")) {
				viagem.setEstrategia((ViagemLimitada) persist(new ViagemLimitada(codigo)));
			} else if (tipo.equals("ABERTA")) {
				viagem.setEstrategia((ViagemAberta) persist(new ViagemAberta()));
			} else {
				throw new Exception("Selecione o tipo de Viagem");
			}

			
			if (getObjectBD(viagem) != null) {
				throw new Exception(
						"Ja existe uma viagem cadastrada no sisteme para esse local, com  dia, descricao e tipo iguais."
						+ "\n Insira os dados novamente.");
			}else{
				usuario.adicionarViagem((Viagem) persist(viagem));
				dao.merge(usuario);
				dao.flush();
			}
		} catch (Exception e) {
			flash("erro", e.getMessage());
			return badRequest(cadastroDeViagem.render(CADASTRO_VIAGEM, usuario, VIAGEM_FORM));
		}
		flash("success", "Viagem cadastrada com sucesso.");
		return redirect(controllers.routes.LoginController.index());

	}

	@Transactional
	private static <T> Object persist(Object object) {
		List<T> result = dao.findAllByClassName(object.getClass()
				.getSimpleName());
		if (!result.contains(object)) {
			dao.persist(object);
			dao.flush();
		}
		return getObjectBD(object);
	}

	@Transactional
	private static <T> Object getObjectBD(Object object) {
		List<T> result = dao.findAllByClassName(object.getClass().getSimpleName());
		for (Object o : result) {
			if(o.equals(object)){
				return o;
			}
		}
		return null;
	}

	private static Date getDataFormatada(String data) throws Exception {
		try {
			String[] splitData = data.split("-");
			Integer dia = Integer.parseInt(splitData[2]);
			Integer mes = Integer.parseInt(splitData[1]);
			Integer ano = Integer.parseInt(splitData[0]);
			return new GregorianCalendar(ano, mes-1, dia).getTime();
		} catch (Exception e) {
			throw new Exception("Data Inválida");
		}
	}
}
