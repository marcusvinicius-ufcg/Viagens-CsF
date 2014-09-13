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
		
		v.add((Viagem) persist(new Viagem("Tasmânia, Austrália",
				"A Tasmânia é uma ilha que fica a 250 km do sul da Austrália. Famosa pelo seu ‘diabo’"
				+ ", a Tasmânia tem um clima tropical e diferente da árida Austrália continental, parques com florestas"
				+ ", cachoeiras e trilhas que fazem a alegria dos amantes da natureza.",
				d.getTime(), e, u.subList(0, 7))));
		user1.adicionarViagem(v.get(0));
		dao.merge(user1);
		
		Usuario user2 = u.get(2);
		d = new GregorianCalendar(2014, 11, 10 );
		v.add((Viagem) persist(new Viagem( "Domínica, Caribe",
				"A ilha caribenha da Domínica tem poucas praias e muitas florestas tropicais,"
				+ " resguardando-a do turismo habitual do Caribe. Com sete vulcões em atividade,"
				+ " esta ilha é um sonho para aventureiros em busca de trilhas através de lagos, selva, vales e cânions."
				,d.getTime(),  e, u.subList(8, 15))));
		user2.adicionarViagem(v.get(1));
		dao.merge(user2);
		
		Usuario user3 = u.get(3);
		d = new GregorianCalendar(2015, 1, 14 );
		v.add((Viagem) persist(new Viagem( "Namíbia",
				"Com suas paisagens majestosas, a Namíbia é um país do sul da África que representa o melhor da natureza do continente."
				+ " Namíbia é um santuário da vida selvagem,"
				+ " com reservas naturais, onde podem ser avistados elefantes, girafas, leões e flamingos."
				,d.getTime(),  e, u.subList(16, 23))));
		user3.adicionarViagem(v.get(2));
		dao.merge(user3);
		
		Usuario user4 = u.get(4);
		d = new GregorianCalendar(2015, 1, 28 );
		v.add((Viagem) persist(new Viagem( "Highlands, Escócia", "As terras altas da Escócia, chamadas Highlands,"
				+ " evocam guerreiros viquingues, montanhas espetaculares, lagos misteriosos e paisagens deslumbrantes."
				+ " Surpreenda-se com a gentileza da população, extremamente hospitaleira."
				,d.getTime(),  e, u.subList(24, 31))));
		user4.adicionarViagem(v.get(3));
		dao.merge(user4);
		
		e = (TipoDeViagem) persist(new ViagemLimitada("123"));
		
		Usuario user5 = u.get(5);
		d = new GregorianCalendar(2015, 10, 10 );
		v.add((Viagem) persist(new Viagem( "Tunísia", 
				"Com mais de 1.000 km de litoral mediterrâneo, ruínas romanas, "
				+ "fortificações e dunas do Saara, a Tunísia oferece exotismo próximo da Europa e uma cultura rica para aventurar-se."
				,d.getTime(),  e, u.subList(32, 39))));
		user5.adicionarViagem(v.get(4));
		dao.merge(user5);
		
		Usuario user6 = u.get(6);
		d = new GregorianCalendar(2015, 3, 10 );
		v.add((Viagem) persist(new Viagem( "Palawan, Filipinas",
				"A ilha filipina de Palawan é um paraíso terrestre, com águas cristalinas, praias de areia branca, palmeiras e ilhotas."
				+ " Graças à sua biodiversidade terrestre e marinha incrível, Palawan é ideal para a prática do mergulho em suas águas tranquilas."
				,d.getTime(),  e, u.subList(0, 7))));
		user6.adicionarViagem(v.get(5));
		dao.merge(user6);
		
		Usuario user7 = u.get(7);
		d = new GregorianCalendar(2015, 2, 10 );
		v.add((Viagem) persist(new Viagem( "Crimeia, Ucrânia",
				"O litoral ucraniano do Mar Negro, na península da Crimeia, é um local desconhecido para a maioria dos turistas do mundo."
				+ " Talvez por isso mesmo, seja um bom destino para relaxar, em lugares como Yalta ou Simeiz, na que é chamada de Riviera Russa."
				,d.getTime(),  e, u.subList(8, 15))));
		user7.adicionarViagem(v.get(6));
		dao.merge(user7);
		e = (TipoDeViagem) persist(new ViagemAberta());
		
		Usuario user8 = u.get(8);
		d = new GregorianCalendar(2015, 3, 10 );
		v.add((Viagem) persist(new Viagem( "Papua Nova Guiné", 
				"Situada ao norte da Austrália, a Papua Nova Guiné tem uma natureza fantástica, "
				+ "com barreiras de coral, florestas para realizar trilhas e avistar numerosas espécies de pássaros e outros animais. "
				+ "As águas são das mais transparentes do planeta.",
				d.getTime(),  e, u.subList(16, 23))));
		user8.adicionarViagem(v.get(7));
		dao.merge(user8);
		
		Usuario user9 = u.get(9);
		d = new GregorianCalendar(2015, 6, 10 );
		v.add((Viagem) persist(new Viagem( "Grand Canyon - Estados Unidos",
				"O Grand Canyon tem 446 km de formações rochosas e chega a atingir até 1,5 km de profundidade."
				+ " O vale foi moldado ao longo de milhares de anos pela erosão à medida que as água do Rio Colorado percorriam o leito,"
				+ " criando esse contraste maravilhoso de cores.",d.getTime(),  e, u.subList(24, 31))));
		user9.adicionarViagem(v.get(8));
		dao.merge(user9);
		
		Usuario user10 = u.get(10);
		d = new GregorianCalendar(2015, 7, 10 );
		v.add((Viagem) persist(new Viagem( "A Grande barreira de Corais - Austrália",
				"Este é o maior recife de corais do mundo, com mais de 2.300 km de extensão."
				+ " A Grande Barreira de Corais está situada na costa nordeste do Estado de Queensland, na Austrália."
				+ " Ela também é a maior estrutura do mundo feita apenas por organismos vivos.",d.getTime(),  e, u.subList(32, 39))));
		user10.adicionarViagem(v.get(9));
		dao.merge(user10);
		e = (TipoDeViagem) persist(new ViagemAberta());
		
		Usuario user11 = u.get(11);
		d = new GregorianCalendar(2015, 7, 15 );
		
		v.add((Viagem) persist(new Viagem( "Ilhas Phi Phi - Tailândia",
				"Um paraíso para competir com as Maldivas, as Ilhas Phi Phi, na Tailândia,"
				+ " tem águas cristalinas e areias brancas cercadas por falésias calcáreas."
				+ " Você talvez já tenha visto a Maya Beach, uma de suas praias, no filme A Praia, com Leonardo DiCaprio.",
				d.getTime(),  e, u.subList(0, 7))));
		user11.adicionarViagem(v.get(10));
		
		v.add((Viagem) persist(new Viagem( "Melbourne, Austrália",
				"A cada ano, Melbourne vira mais cosmopolita e sofisticada."
				+ "  A cidade está sempre em movimento, seja pelos seus eventos esportivos, ou pelo espírito festivo de seus habitantes."
				+ " Graças à sua diversidade, Melbourne tem uma grande variedade de restaurantes.",d.getTime(),  e, u.subList(8, 15))));
		user11.adicionarViagem(v.get(11));
		
		v.add((Viagem) persist(new Viagem( "Tel Aviv, Israel",
				"Longe da espiritualidade de Jerusalém, Tel Aviv é uma cidade dinâmica e divertida,"
				+ " berço de teatros, música e moda israelense, com belas praias e uma arquitetura moderna.",
				d.getTime(),  e, u.subList(16, 23))));
		user11.adicionarViagem(v.get(12));
		
		v.add((Viagem) persist(new Viagem( "Portugal", "Mais quente e mais barato do que o resto dos países da Europa,"
				+ " Portugal é um excelente destino para quem viaja para a Europa.",d.getTime(),  e, u.subList(24, 31))));
		user11.adicionarViagem(v.get(13));
		v.add((Viagem) persist(new Viagem( "Istambul, Tuquia",
				"Algumas cidades se destacam pela sua gastronomia,"
				+ " outras pela sua vida noturna, e outras pela sua cultura."
				+ " Istambul se destaca por tudo isto.",d.getTime(),  e, u.subList(32, 39))));
		user11.adicionarViagem(v.get(14));
		
		dao.merge(user11);
		
		Usuario user12 = u.get(12);
		d = new GregorianCalendar(2015, 2, 7 );
		e = (TipoDeViagem) persist(new ViagemLimitada("123"));
		
		v.add((Viagem) persist(new Viagem( "Mumbai, Índia", "Maior cidade da Índia, com uma população de cerca de 14 milhões,"
				+ " Mumbai envolve os visitantes em seu caos, seu barulho e seu movimento constante."
				+ " A única forma de encarar e curtir é se deixar levar por esta experiência apaixonante e  única.",
				d.getTime(),  e, u.subList(0, 11))));
		user12.adicionarViagem(v.get(15));
		
		v.add((Viagem) persist(new Viagem( "Sydney, Austrália",
				"Calorosa e ensolarada cidade da Austrália,"
				+ "  Sydney  tem opções para os que querem curtir belas praias durante o dia e muita badalação à noite.",
				d.getTime(),  e, u.subList(11, 21))));
		user12.adicionarViagem(v.get(16));
		
		v.add((Viagem) persist(new Viagem( "Salar de Uyuni, Bolívia",
				"O Salar de Uyuni é o maior deserto de sal do mundo, com 10582 km² de área e altitude de 3.650 m acima do nível do mar."
				+ " No início do verão, quando começa o período de chuvas, o deserto fica coberto de água,"
				+ " tornando-o um imenso espelho que reflete o céu.",d.getTime(),  e, u.subList(11, 21))));
		user12.adicionarViagem(v.get(17));
		
		v.add((Viagem) persist(new Viagem( "Kilkenny, Irlanda",
				"A Irlanda é conhecida por ter um povo muito hospitaleiro e é em Kilkenny, que se encontram as pessoas mais amigáveis do país. "
				+ "Lá as pessoas cumprimentam os visitantes e fazem com que os turistas não saiam da cidade sem provar o que há de melhor na região.",
				d.getTime(),  e, u.subList(21, 31))));
		user12.adicionarViagem(v.get(18));
		
		v.add((Viagem) persist(new Viagem( "Mandalay, Mianmar", 
				"Mandalay é a segunda maior cidade do Mianmar e localiza-se no centro do país,"
				+ " às margens do rio Irauádi. Destaca-se pela sua arquitetura,"
				+ " recursos naturais e por ser um lugar muito hospitaleiro para os visitantes estrangeiros.",d.getTime(),  e, u.subList(31, 39))));
		user12.adicionarViagem(v.get(19));
		
		dao.merge(user12);
		
		Usuario user13 = u.get(15);
		d = new GregorianCalendar(2015, 4, 3 );		
		v.add((Viagem) persist(new Viagem( "Alter do chão, Brasil", 
				"localizada às margens do tio Tapajós, no oeste do Pará, é conhecida como o ‘Caribe Amazônico’."
				+ " Formada por areias brancas, águas cristalinas e cercada pela floresta, ela aparece no verão amazônico,"
				+ " de julho a janeiro, quando chove menos na região.",d.getTime(),  e, u.subList(1, 11))));
		user13.adicionarViagem(v.get(20));
		
		v.add((Viagem) persist(new Viagem( "Preikestolen – Forsand, Noruega",
				"Também conhecida como Pulpit Rock, o precipício tem mais de 604 metros de altura.",d.getTime(),  e, u.subList(11, 21))));
		user13.adicionarViagem(v.get(21));
		
		v.add((Viagem) persist(new Viagem( "Praga, República Tcheca", 
				"Praga, capital da República Tcheca, concentra diversos monumentos históricos, de várias épocas diferentes."
				+ " Até mesmo as pontes sob o rio Vltava, que corta a cidade, são atrações turísticas.",d.getTime(),  e, u.subList(11, 21))));
		user13.adicionarViagem(v.get(22));
		
		v.add((Viagem) persist(new Viagem( "Nova York, Estados unidos",
				"Maior símbolo dos Estados Unidos, Nova York é também o que há de mais cosmopolita no país norte-americano."
				+ " Em mudança constante e permanente, a chamada “Big Apple” impressiona pelos seus imponentes arranha-céus de Manhattan.",
				d.getTime(),  e, u.subList(21, 31))));
		user13.adicionarViagem(v.get(23));
		
		v.add((Viagem) persist(new Viagem( "São Francisco, Estados unidos",
				"A cidade californiana de São Francisco é sinônimo de liberdades individuais no oeste dos Estados Unidos."
				+ " A ponte Golden Gate, suspensa, e as colinas são símbolos da cidade,"
				+ " que tem mais restaurantes per capita do que qualquer outra nos Estados Unidos.",d.getTime(),  e, u.subList(31, 39))));
		user13.adicionarViagem(v.get(24));
		
		dao.merge(user13);
		
		Usuario user14 = u.get(20);
		d = new GregorianCalendar(2015, 5, 22);
		e = (TipoDeViagem) persist(new ViagemAberta());
		
		v.add((Viagem) persist(new Viagem( "Montanhas Rochosas, Canadá", 
				"Montanhas nevadas, lagos de águas cristalinas e florestas fazem das Montanhas Rochosas do Canadá a região mais bonita do país. "
				+ "Trilhas e passeios de canoas são o jeito ideal de aproveitar essa esplêndida natureza.",d.getTime(),  e, u.subList(1, 11))));
		user14.adicionarViagem(v.get(25));
		
		v.add((Viagem) persist(new Viagem( "Galápagos, Equador", 
				"A menos de 1.000 km do litoral equatoriano, o arquipélago das Galápagos é um lugar do planeta impressionante por sua "
				+ "biodiversidade e suas paisagens vulcânicas dão um toque ainda mais especial ao lugar.",d.getTime(),  e, u.subList(11, 21))));
		user14.adicionarViagem(v.get(26));
		
		v.add((Viagem) persist(new Viagem( "Deserto do Saara, África",
				"O Deserto do Saara se estende por 10 países do norte da África, ocupando um espaço semelhante ao tamanho do Brasil. "
				+ "Trata-se de um dos lugares mais selvagens e áridos do planeta.",d.getTime(),  e, u.subList(11, 21))));
		user14.adicionarViagem(v.get(27));
		
		v.add((Viagem) persist(new Viagem( "Boundary Waters, EUA e Canadá",
				"Encravadas entre os Estados Unidos e o Canadá, nos Estados de Minnesota e Ontário, perto do Lago Superior,"
				+ " a região de Boundary Waters consiste em mais de 1000 lagos interconectados,"
				+ " cercados por belas florestas e ideais para a prática da canoagem.",d.getTime(),  e, u.subList(21, 31))));
		user14.adicionarViagem(v.get(28));
		
		v.add((Viagem) persist(new Viagem( "Ilhas Gregas",
				"Algumas das ilhas mais bonitas da Europa estão situadas nos mares Mediterrâneo, Egeu e Jônico."
				+ " São mais de 6.000 Ilhas Gregas, entre as quais encontram-se ilhas famosas como Santorini e Mykonos."
				+ " Belas praias e águas azuis atraem milhares de turistas.",d.getTime(),  e, u.subList(31, 39))));
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