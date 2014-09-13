package controllers;
import static play.data.Form.form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.SolicitacaoAberta;
import models.SolicitacaoLimitada;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class ParticipantesController extends Controller {

	// INICIO DA DECLARACAO DE VARIAVEIS
	private static GenericDAO dao = new GenericDAOImpl();
	private static Set<Usuario> usuariosLogados = new HashSet<Usuario>();
	// FIM DA DECLARACAO DE VARIAVEIS
	
	@Transactional
	public static Result participarDeViagem(String email, Long idViagem){
		
		Usuario usuario = getUser(email);
		
		if (usuario == null) {
			return redirect(controllers.routes.LoginController.index());
		}
		
		Viagem viagem = getViagem(idViagem);
		
		if(viagem.getEstrategia() instanceof ViagemAberta){
			
			SolicitacaoAberta solicitacao = new SolicitacaoAberta(usuario);
			
			if(viagem.adicionarParticipante(solicitacao)){
				dao.merge(viagem);
				dao.flush();
				
				flash("success", "Você agora esta na lista de participantes da Viagem para " + viagem.getLocal() + ".");
			}else{
				flash("erro", "Não foi possivel se Increver na Viagem.\n"
						+ "Vefique se ja esta participando da viagem.");
			}
			return redirect(controllers.routes.LoginController.index());
			
		}else if(viagem.getEstrategia() instanceof ViagemLimitada){
			String codigo = form().bindFromRequest().get("codigo");
			
			SolicitacaoLimitada solicitacao = new SolicitacaoLimitada(usuario, codigo);
			
			if(viagem.adicionarParticipante(solicitacao)){
				
				dao.merge(viagem);
				dao.flush();
				
				flash("success", "Você agora esta na lista de participantes da Viagem para " + viagem.getLocal() + ".");
			}else{
				flash("erro", "Não foi possivel se Increver na Viagem.\n"
						+ "Vefique se ja esta participando ou se código da viagem é valido.");
			}
			return redirect(controllers.routes.LoginController.index());
		}
		
		return redirect(controllers.routes.LoginController.index());		
	}
	
	@Transactional
	private static Usuario getUser(String email) {
		List<Usuario> result = dao.findByAttributeName("Usuario", "email",
				email);
		return result.size() == 0 ? null : result.get(0);
	}
	
	@Transactional
	private static Viagem getViagem(Long id) {
		return dao.findByEntityId(Viagem.class, id);		
	}
	
	public static boolean usuarioOnline(Usuario usuario){
		return usuariosLogados.contains(usuario);
	}
	
	public static void addUsuarioLogado(Usuario usuario){
		usuariosLogados.add(usuario);
	}
	
	public static void removerUsuarioLogado(Usuario usuario){
		usuariosLogados.remove(usuario);
	}	
}
