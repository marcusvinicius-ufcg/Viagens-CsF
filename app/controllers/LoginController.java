package controllers;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Objects;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class LoginController extends Controller {

	//INICIO DA DECLARACAO DE VARIAVEIS
	private static GenericDAO dao = new GenericDAOImpl();
	private static final String CADASTRO = new String("Cadastro - Viagens CsF");
	private static final String LOGIN = new String("Login - Viagens CsF");
	private static final String PRINCIPAL = new String("Principal - Viagens CsF");
	private static final Form<Cadastro> FORM_CADASTRO = Form.form(Cadastro.class);
	private static final Form<Login> FORM_LOGIN = Form.form(Login.class);
	//FIM DA DECLARACAO DE VARIAVIS
	
	static{
		session().clear();
	}
	/**
	 * Metodo retorna o index
	 * @return pagina web
	 */
	@Transactional
	public static Result index() {
		
		if(session("email") == null){
			return ok(login.render(LOGIN, FORM_LOGIN));
		}else{
			Usuario usuario = getUser(session("email"));
			if(usuario == null){
				return ok(login.render(LOGIN, FORM_LOGIN));
			}
			return ok(index.render(PRINCIPAL, usuario));
		}
	}
	
	/**
	 * Metodo chama a tela de Login
	 * @return pagina web
	 */
	@Transactional
	private static Result login() {
		return ok(login.render(LOGIN, FORM_LOGIN));
	}
	/**
	 * Metodo retorna a pagina de cadastro de usuario
	 * @return pagina web
	 */
	@Transactional
	public static Result paginaDeCadastro() {
		return ok(cadastroDeUsuario.render(CADASTRO, FORM_CADASTRO));
	}

	/**
	 * Metodo e Requisitado pelo sistema apos o usuarios inserir suas informacoes
	 * para se cadastrar
	 * @return pagina web
	 */
	@Transactional
	public static Result cadastrar() {

		Form<Cadastro> cadastroForm = FORM_CADASTRO.bindFromRequest();

		if (cadastroForm.hasErrors()) {
			return badRequest(cadastroDeUsuario.render(CADASTRO, cadastroForm));
		}
		try {
			
			Usuario user = criarUser(cadastroForm);
			salvarUsuario(user);
			
		} catch (Exception e) {
			flash("success", e.getMessage());
			return badRequest(cadastroDeUsuario.render(CADASTRO, cadastroForm));
		}		
		return redirect(routes.LoginController.index());
	}
	
	@Transactional
	public static Result autenticar() {
		Form<Login> loginForm = FORM_LOGIN.bindFromRequest();

		if (loginForm.hasErrors() || autenticacaoFalhou(loginForm)) {
			return badRequest(login.render(LOGIN, loginForm));
		} else {
			session().clear();
			
			Usuario usuario = getUser(loginForm.get().getEmail());
			
			if(usuario == null){
				return ok(login.render(LOGIN, FORM_LOGIN));
			}
			session("email", usuario.getEmail());
			controllers.ParticipantesController.addUsuarioLogado(usuario);
			return redirect(routes.LoginController.index());
		}
	}

	@Transactional
	private static Usuario getUser(String email) {
		List<Usuario> result = dao.findByAttributeName("Usuario", "email", email);
		return result.size() == 0 ? null : result.get(0);
	}

	@Transactional
	private static boolean autenticacaoFalhou(Form<Login> loginForm) {

		Usuario user = getUser(loginForm.get().getEmail());

		if (user == null) {
			flash("success", "Email não cadastrado");
			return true;
		}
		if (!isPasswordValido(loginForm, user)) {
			flash("success", "Senha incorreta");
			return true;
		}
		return false;
	}

	@Transactional
	private static boolean isPasswordValido(Form<Login> loginForm, Usuario user) {
		Integer hash = Objects.hashCode(user.getEmail(), loginForm.get().getPassword());

		String hashString = String.valueOf(hash);

		return hashString.equals(user.getSenha());
	}

	@Transactional
	public static Result logout() {
		if(session("email") != null){
			Usuario usuario = getUser(session("email"));
			controllers.ParticipantesController.removerUsuarioLogado(usuario);
		}
		session().clear();
		return login();
	}
	
	/**
	 * Metodo salva o usuario, passado no parametro, no banco de dados
	 * @param usuario
	 * @throws Exception caso o usuario ja esteja cadastrado no banco de dados
	 */
	@Transactional
	private static void salvarUsuario(Usuario usuario) throws Exception {
		
		if (usuarioCadastrado(usuario)) {
			throw new Exception("Email já cadastrado");
		}
		dao.persist(usuario);
		dao.flush();
	}
	
	/**
	 * Metodo verifica se o usuario passado ja esta cadastrado no sistema
	 * @param usuario
	 * @return true caso o usuario esteja cadastrado e false caso contrario
	 */
	@Transactional
	private static boolean usuarioCadastrado(Usuario usuario) {
		return getAllUsuarios().contains(usuario);
	}
	
	/**
	 * Metodo retorna uma colecao de todos os usuarios cadastrados no sistema
	 * @return
	 */
	@Transactional
	private static Collection<Usuario> getAllUsuarios(){
		return dao.findAllByClassName("Usuario");
	}

	/**
	 * Metodo cria um usuario baseado no dados do parametro
	 * @param cadastro - formulario com dados para o cadastro do usuario
	 * @throws Exception - erro proviniente da classe Usuario
	 * @return Usuario
	 */
	@Transactional
	private static Usuario criarUser(Form<Cadastro> cadastro) throws Exception {
		Usuario user = new Usuario();
		user.setEmail(cadastro.get().getEmail());
		user.setSenha(cadastro.get().getSenha());
		user.setNome(cadastro.get().getNome());
		return user;
	}

	/**
	 * Classe Login
	 */
	public static class Login {
		private String email;
		private String password;

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	/**
	 * Classe Cadastro
	 */
	public static class Cadastro {
		
		private String email;
		private String nome;
		private String senha;

		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
	}
}
