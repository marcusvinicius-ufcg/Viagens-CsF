import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import models.TipoDeViagem;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class Global extends GlobalSettings {

	private GenericDAO dao = new GenericDAOImpl();
	
	@Override
	public void onStart(Application arg0) {

		JPA.withTransaction(new play.libs.F.Callback0() {

			@Override
			public void invoke() throws Throwable {
				try {
					criarViagens();
				} catch (Exception e) {
					Logger.info("Erro Global: " + e.getMessage());
				}
			}
		});
	}
	
	private void criarViagens() throws Exception{
		
		List<Viagem> v = new ArrayList<Viagem>();
		List<Usuario> u = criarUsuarios();
		
		Usuario user1 = u.get(1);
		Calendar d = new GregorianCalendar(2014, 10, 28 );
		TipoDeViagem e = (TipoDeViagem) persist(new ViagemAberta());
		
		v.add((Viagem) persist(new Viagem( "Bolivia", "Passeio",d.getTime(),  e, u.subList(0, 7))));
		user1.adicionarViagem(v.get(0));
		dao.merge(user1);
		
		Usuario user2 = u.get(2);
		d = new GregorianCalendar(2014, 11, 10 );
		v.add((Viagem) persist(new Viagem( "Peru", "Passeio",d.getTime(),  e, u.subList(8, 15))));
		user2.adicionarViagem(v.get(1));
		dao.merge(user2);
		
		Usuario user3 = u.get(3);
		d = new GregorianCalendar(2015, 1, 14 );
		v.add((Viagem) persist(new Viagem( "Paraguai", "Passeio",d.getTime(),  e, u.subList(16, 23))));
		user3.adicionarViagem(v.get(2));
		dao.merge(user3);
		
		Usuario user4 = u.get(4);
		d = new GregorianCalendar(2015, 1, 28 );
		v.add((Viagem) persist(new Viagem( "Argentina", "Passeio",d.getTime(),  e, u.subList(24, 31))));
		user4.adicionarViagem(v.get(3));
		dao.merge(user4);
		
		e = (TipoDeViagem) persist(new ViagemLimitada("123"));
		
		Usuario user5 = u.get(5);
		d = new GregorianCalendar(2015, 10, 10 );
		v.add((Viagem) persist(new Viagem( "Venezuela", "Passeio",d.getTime(),  e, u.subList(32, 39))));
		user5.adicionarViagem(v.get(4));
		dao.merge(user5);
		
		Usuario user6 = u.get(6);
		d = new GregorianCalendar(2015, 3, 10 );
		v.add((Viagem) persist(new Viagem( "Australia", "Passeio",d.getTime(),  e, u.subList(0, 7))));
		user6.adicionarViagem(v.get(5));
		dao.merge(user6);
		
		Usuario user7 = u.get(7);
		d = new GregorianCalendar(2015, 2, 10 );
		v.add((Viagem) persist(new Viagem( "Canada", "Passeio",d.getTime(),  e, u.subList(8, 15))));
		user7.adicionarViagem(v.get(6));
		dao.merge(user7);
		e = (TipoDeViagem) persist(new ViagemAberta());
		Usuario user8 = u.get(8);
		d = new GregorianCalendar(2015, 3, 10 );
		v.add((Viagem) persist(new Viagem( "Suiça", "Passeio",d.getTime(),  e, u.subList(16, 23))));
		user8.adicionarViagem(v.get(7));
		dao.merge(user8);
		
		Usuario user9 = u.get(9);
		d = new GregorianCalendar(2015, 6, 10 );
		v.add((Viagem) persist(new Viagem( "Italia", "Passeio",d.getTime(),  e, u.subList(24, 31))));
		user9.adicionarViagem(v.get(8));
		dao.merge(user9);
		
		Usuario user10 = u.get(10);
		d = new GregorianCalendar(2015, 7, 10 );
		v.add((Viagem) persist(new Viagem( "França", "Passeio",d.getTime(),  e, u.subList(32, 39))));
		user10.adicionarViagem(v.get(9));
		dao.merge(user10);
		e = (TipoDeViagem) persist(new ViagemAberta());
		Usuario user11 = u.get(11);
		d = new GregorianCalendar(2015, 7, 15 );
		
		v.add((Viagem) persist(new Viagem( "Grecia", "Passeio",d.getTime(),  e, u.subList(0, 7))));
		user11.adicionarViagem(v.get(10));
		v.add((Viagem) persist(new Viagem( "Roma", "Passeio",d.getTime(),  e, u.subList(8, 15))));
		user11.adicionarViagem(v.get(11));
		v.add((Viagem) persist(new Viagem( "Egito", "Passeio",d.getTime(),  e, u.subList(16, 23))));
		user11.adicionarViagem(v.get(12));
		v.add((Viagem) persist(new Viagem( "Romenia", "Passeio",d.getTime(),  e, u.subList(24, 31))));
		user11.adicionarViagem(v.get(13));
		v.add((Viagem) persist(new Viagem( "Chile", "Passeio",d.getTime(),  e, u.subList(32, 39))));
		user11.adicionarViagem(v.get(14));
		
		dao.merge(user11);
		
		Usuario user12 = u.get(12);
		d = new GregorianCalendar(2015, 2, 7 );
		e = (TipoDeViagem) persist(new ViagemLimitada("123"));
		
		v.add((Viagem) persist(new Viagem( "Mexico", "Passeio", d.getTime(),  e, u.subList(0, 11))));
		user12.adicionarViagem(v.get(15));
		v.add((Viagem) persist(new Viagem( "Portugal", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user12.adicionarViagem(v.get(16));
		v.add((Viagem) persist(new Viagem( "China", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user12.adicionarViagem(v.get(17));
		v.add((Viagem) persist(new Viagem( "Japão", "Passeio",d.getTime(),  e, u.subList(21, 31))));
		user12.adicionarViagem(v.get(18));
		v.add((Viagem) persist(new Viagem( "Estados Unidos", "Passeio",d.getTime(),  e, u.subList(31, 39))));
		user12.adicionarViagem(v.get(19));
		
		dao.merge(user12);
		
		Usuario user13 = u.get(15);
		d = new GregorianCalendar(2015, 4, 3 );
		
		
		v.add((Viagem) persist(new Viagem( "Inglaterra", "Passeio",d.getTime(),  e, u.subList(1, 11))));
		user13.adicionarViagem(v.get(20));
		v.add((Viagem) persist(new Viagem( "Holanda", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user13.adicionarViagem(v.get(21));
		v.add((Viagem) persist(new Viagem( "China", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user13.adicionarViagem(v.get(22));
		v.add((Viagem) persist(new Viagem( "Irlanda", "Passeio",d.getTime(),  e, u.subList(21, 31))));
		user13.adicionarViagem(v.get(23));
		v.add((Viagem) persist(new Viagem( "Uruguai", "Passeio",d.getTime(),  e, u.subList(31, 39))));
		user13.adicionarViagem(v.get(24));
		
		dao.merge(user13);
		
		Usuario user14 = u.get(20);
		d = new GregorianCalendar(2015, 5, 22);
		e = (TipoDeViagem) persist(new ViagemAberta());
		
		v.add((Viagem) persist(new Viagem( "Mesopotâmia", "Passeio",d.getTime(),  e, u.subList(1, 11))));
		user14.adicionarViagem(v.get(25));
		v.add((Viagem) persist(new Viagem( "Paris", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user14.adicionarViagem(v.get(26));
		v.add((Viagem) persist(new Viagem( "Londres", "Passeio",d.getTime(),  e, u.subList(11, 21))));
		user14.adicionarViagem(v.get(27));
		v.add((Viagem) persist(new Viagem( "New York", "Passeio",d.getTime(),  e, u.subList(21, 31))));
		user14.adicionarViagem(v.get(28));
		v.add((Viagem) persist(new Viagem( "Sudão", "Passeio",d.getTime(),  e, u.subList(31, 39))));
		user14.adicionarViagem(v.get(29));
		
		dao.merge(user14);
		
		dao.flush();
	}
	
	private List<Usuario> criarUsuarios(){
		List<Usuario> u = new ArrayList<Usuario>();
		try {
			u.add((Usuario) persist(new Usuario("Celia", "celia@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Maria", "maria@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Raquel", "raquel@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Priscila", "priscila@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Tereza", "tereza@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Conceicao", "conceicao@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Francisca", "francisca@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Lurdes", "luders@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Raissa", "raissa@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Rayane", "rayane@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Janaina", "janaina@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Mateus", "mateus@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Marcos", "marcos@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Francisco", "francisco@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Tiago", "tiago@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Barbosa", "barbosa@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Marques", "marques@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Fernanda", "fernanda@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Jaqueline", "jaqueline@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Joao", "joao@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Lucas", "lucas@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Rodrigo", "rodrigo@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Junior", "junior@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Ricardo", "ricardo@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Cassio", "cassio@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Pedro", "pedro@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Eduarda", "eduarda@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Isabele", "isalbele@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Ana", "ana@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Julia", "julia@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Beatriz", "beatriz@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Laura", "laura@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Leticia", "leticia@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Larissa", "Larissa@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Gabriel", "gabriel@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Artur", "artur@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Rafael", "rafael@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Leonardo", "leonardo@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Diego", "diego@mail.com", "123456", new ArrayList<Viagem>())));
			u.add((Usuario) persist(new Usuario("Carlos", "carlos@mail.com", "123456", new ArrayList<Viagem>())));
		} catch (Exception e) {
			Logger.info("Erro Global: " + e.getMessage());
		}
		return u;		
	}
	
	@Transactional
	private <T> Object persist(Object object) {
		List<T> result = dao.findAllByClassName(object.getClass().getSimpleName());
		if (!result.contains(object)) {
			dao.persist(object);
			dao.flush();
		}
		return getObjectBD(object);
	}

	@Transactional
	private <T> Object getObjectBD(Object object) {
		List<T> result = dao.findAllByClassName(object.getClass().getSimpleName());
		for (Object obj : result) {
			if (obj.equals(object)) {
				return obj;
			}
		}
		return null;
	}
}