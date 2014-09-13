import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.SolicitacaoAberta;
import models.SolicitacaoLimitada;
import models.TipoDeViagem;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;


public class ViagemTest {
	private List<Usuario> participantesFake;
	private static GenericDAO dao = new GenericDAOImpl();
	
	private String alterDoChao;
	private String alterDoChaoDesc;
	private String monteRoraima;
	private String monteRoraimaDesc;
	private String preikestolen_Noruega;
	private String preikestolenDesc;
	private String Pangong_Tso_Himalaia;
	private String pangongDesc;
	private String ilha_de_Mauritius;
	private String ilha_MauritiusDesc;
	private String codigo;
	private TipoDeViagem tipoDeViagemAberta;
	private TipoDeViagem tipoDeViagemFechada;
	
	
	
	@Before
	public void setUp(){
		participantesFake = new ArrayList<Usuario>();
		alterDoChao = "Alter do chão";
		alterDoChaoDesc = "localizada às margens do tio Tapajós, no oeste do Pará, é conhecida como o ‘Caribe Amazônico’. Formada por areias brancas, águas cristalinas e cercada pela floresta, ela aparece no verão amazônico, de julho a janeiro, quando chove menos na região.";
		monteRoraima = "Monte Roraima";
		monteRoraimaDesc = "Na remota fronteira entre Brasil, Venezuela e Guiana, situa-se o Monte Roraima, com seus 2.700 metros de altitude. O monte, no formato de mesa (tepuy), possui 15 quilômetros de comprimento, e  tem seu topo tomado por vales e formações rochosas esculturais, rios e cachoeiras, fauna e flora próprias, com vistas espetaculares de montanhas, florestas e savanas localizadas ao redor.";
		preikestolen_Noruega = "Preikestolen – Forsand, Noruega";
		preikestolenDesc = "Também conhecida como Pulpit Rock, o precipício tem mais de 604 metros de altura.";
		Pangong_Tso_Himalaia = "Pangong Tso – Himalaia";
		pangongDesc = "O lago Pangong Tso é de água salgada a 4.350 metros de altura, no coração do Himalaia. O ar rarefeito dá cores ainda mais bonitas às águas, criando efeito de pureza e intensidade. O lago é acessível através de uma trilha que começa na cidade indiana de Leh.";
		ilha_de_Mauritius = "Ilha de Mauritius";
		ilha_MauritiusDesc = "A Ilha de Mauritius é tão incrível que temos a ilusão visual de que abaixo das suas águas há uma grande cachoeira. O efeito óptico se dá graças às correntes e vegetação subaquáticas. O resultado é mágico.";
		codigo = "12345";
		tipoDeViagemAberta = new ViagemAberta();
		try {
			tipoDeViagemFechada =  new ViagemLimitada(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveCriarViagem() {
		// Testa a criação de uma viagem ABERTA - usando os 2 construtores possíveis.
		Viagem viagem = null;
		Viagem viagem2 = null;
		
		try {			
			// a primeira viagem usa o construtor que nao define o tipo.
			viagem = new Viagem(preikestolen_Noruega, preikestolenDesc, new Date(), tipoDeViagemAberta, participantesFake);
			// a segunda viagem usa o construtor que define o tipo, nesse caso aberta.
			viagem2 = new Viagem(preikestolen_Noruega, preikestolenDesc, new Date(), tipoDeViagemAberta,  participantesFake);
				
		} catch (Exception e) {
			fail();
		}
		
		// Testa o tipo de estrategia da viagem. Nesse caso é aberta.
		assertTrue(viagem.getEstrategia() instanceof ViagemAberta);
		assertFalse(viagem.getEstrategia() instanceof ViagemLimitada);
		assertTrue(viagem2.getEstrategia() instanceof ViagemAberta);
		assertFalse(viagem2.getEstrategia() instanceof ViagemLimitada);	
		
		// Testa se a viagem é criada com 0 participantes.
		assertEquals(0, viagem.countParticipantes());
		assertEquals(0, viagem2.countParticipantes());
		
		viagem = null;
		viagem2 = null;
		// Testa a criação de uma viagem LIMITADA - usando os 2 construtores possíveis.
		try {
			// a primeira viagem usa o construtor que nao define o tipo.
			viagem = new Viagem(alterDoChao, alterDoChaoDesc, new Date(),tipoDeViagemFechada, participantesFake);
			// a segunda viagem usa o construtor que define o tipo, nesse caso fechada.
			viagem2 = new Viagem(preikestolen_Noruega, preikestolenDesc, new Date(), tipoDeViagemFechada,  participantesFake);
			
		} catch (Exception e) {
			fail();
		}
		
		// Testa o tipo de estrategia da viagem. Nesse caso é limitada.
		assertFalse(viagem.getEstrategia() instanceof ViagemAberta);
		assertTrue(viagem.getEstrategia() instanceof ViagemLimitada);	
		assertFalse(viagem2.getEstrategia() instanceof ViagemAberta);
		assertTrue(viagem2.getEstrategia() instanceof ViagemLimitada);	
		
		// Testa se a viagem é criada com 0 participantes.
		assertEquals(0, viagem.countParticipantes());
		assertEquals(0, viagem2.countParticipantes());		
	}
	
	@Test
	public void deveLancarException() {
		// Nome do local nulo
		try {
			new Viagem(null, Pangong_Tso_Himalaia, new Date(), tipoDeViagemAberta, participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Local nulo ou vazio!", e.getMessage());
		}
		
		// Nome curto
		try {
			new Viagem("aa", Pangong_Tso_Himalaia, new Date(), tipoDeViagemAberta, participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Local deve ter no Minimo 3 Caracteres!", e.getMessage());
		}
		
		// Descrição nula
		try {
			new Viagem(ilha_de_Mauritius, null, new Date(), tipoDeViagemAberta, participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Descrição Nula ou Vazia!", e.getMessage());
		}
		
		// Descrição pequena demais
		try {
			new Viagem(ilha_de_Mauritius, "ej", new Date(), tipoDeViagemAberta,participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Descrição deve ter no Minimo 3 Caracteres!", e.getMessage());
		}
		
		// Data nula
		try {
			new Viagem(monteRoraima, monteRoraimaDesc, null, tipoDeViagemAberta, participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Data Nula ou Vazia!", e.getMessage());
		}
		
		// Data inválida
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, -1);
			
			new Viagem(alterDoChao, alterDoChaoDesc, calendar.getTime(), tipoDeViagemAberta,participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Data já Ultrapassada!", e.getMessage());
		}
		
		// Lista de participantes nula
		try {
			new Viagem(ilha_de_Mauritius, ilha_MauritiusDesc, new Date(), tipoDeViagemAberta, null);
			fail();
		} catch (Exception e) {
			assertEquals("Coleção de Participantes Invalida!", e.getMessage());
		}
		
		// Código nulo
		try {
			new Viagem(preikestolen_Noruega, preikestolenDesc, new Date(), new ViagemLimitada(""), participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Código Nulo ou Vazio!", e.getMessage());
		}
		
		// Código inválido - tamanho menor que o minimo
		try {
			new Viagem(alterDoChao, alterDoChaoDesc, new Date(), new ViagemLimitada("12"), participantesFake);
			fail();
		} catch (Exception e) {
			assertEquals("Código deve ter no Minimo 3 Caracteres!", e.getMessage());
		}	
		
	}
	
	@Test
	public void testaTrocaDeEstrategia(){
		// Crio uma viagem aberta
		Viagem viagem = null;		
		try {
			viagem = new Viagem(Pangong_Tso_Himalaia, pangongDesc, new Date(), tipoDeViagemAberta, participantesFake);
		} catch (Exception e) {
			fail();
		}

		// Tenta setar a estrategia da viagem com um código nulo
		try {
			viagem.setEstrategia(new ViagemLimitada(null));
			fail();
		} catch (Exception e) {
			// testo se a estrategia se mantem aberta.
			assertTrue(viagem.getEstrategia() instanceof ViagemAberta);
		}

		// Tenta setar a estrategia da viagem com um código inválido
		try {
			viagem.setEstrategia(new ViagemLimitada("12"));
			fail();
		} catch (Exception e) {
			// testo se a estrategia se mantem aberta.
			assertTrue(viagem.getEstrategia() instanceof ViagemAberta);
		}		
		
		// Muda a estrategia da viagem para LIMITADA
		try {
			viagem.setEstrategia(new ViagemLimitada(codigo));
		} catch (Exception e) {
			fail();
		}	
		
		assertTrue(viagem.getEstrategia() instanceof ViagemLimitada);
		
		// Muda a estrategia da viagem para LIMITADA novamente
		try {
			viagem.setEstrategia(new ViagemLimitada(codigo));
		} catch (Exception e) {
			fail();
		}
		
		// testa se mantem limitada.
		assertTrue(viagem.getEstrategia() instanceof ViagemLimitada);
		
		// Muda a estrategia da viagem para ABERTA
		viagem.setEstrategia(new ViagemAberta());
		assertTrue(viagem.getEstrategia() instanceof ViagemAberta);
		
		// Muda a estrategia da viagem para ABERTA novamente
		viagem.setEstrategia(new ViagemAberta());
		// testa se mantem aberta.
		assertTrue(viagem.getEstrategia() instanceof ViagemAberta);		
		
	}
	
	@Test
	public void testaParticipacoesEmViagemAberta(){
		// Comportamentos esperados para uma viagem aberta. 
		// - inserir sem a necessidade de um codígo
		// - não inserir um usuario mais de uma vez.
		
		Viagem viagem = null;
		try {
			viagem = new Viagem(ilha_de_Mauritius, ilha_MauritiusDesc, new Date(), tipoDeViagemAberta, new ArrayList<Usuario>());
		} catch (Exception e) {
			fail();
		}
		
		Usuario usuario = null;
		try {
			usuario = new Usuario("fulano", "fulano@gmail.com", "123456");
		} catch (Exception e) {
			fail();
		}
		
		SolicitacaoAberta solicitacaoAberta = new SolicitacaoAberta(usuario);
		
		viagem.adicionarParticipante(solicitacaoAberta);
		assertEquals(1, viagem.countParticipantes());
		
		// Um usuario não deve ser inserido mais de uma vez.
		viagem.adicionarParticipante(solicitacaoAberta);
		viagem.adicionarParticipante(solicitacaoAberta);
		viagem.adicionarParticipante(solicitacaoAberta);
		assertEquals(1, viagem.countParticipantes());
	}
	
	@Test
	public void testaParticipacoesEmViagemLimitada(){
		Viagem viagem = null;
		try {
			viagem = new Viagem(monteRoraima, monteRoraimaDesc, new Date(), new ViagemLimitada("098765"), new ArrayList<Usuario>());
		} catch (Exception e) {
			fail();
		}
				
		Usuario u = null;
		Usuario u1 = null;
		try {
			u1 = new Usuario("deutrano", "deutrano@gmail.com", "878767");
			u = new Usuario("fulano", "fulano@gmail.com", "123456");
		} catch (Exception e) {
			fail();
		}
		SolicitacaoLimitada solicitacaoLimitada = new SolicitacaoLimitada(u, "098765");
		
		viagem.adicionarParticipante(solicitacaoLimitada);
		assertEquals(1, viagem.countParticipantes());
		
		// Um usuario não deve ser inserido mais de uma vez.
		viagem.adicionarParticipante(solicitacaoLimitada);
		viagem.adicionarParticipante(solicitacaoLimitada);
		assertEquals(1, viagem.countParticipantes());
		
		SolicitacaoLimitada outraSolicitacaoLimitada = new SolicitacaoLimitada(u1, "123");
		
		// O código informado deve ser o mesmo da viagem
		assertFalse(viagem.adicionarParticipante(outraSolicitacaoLimitada));
		
		SolicitacaoLimitada solicitacaoLimitadaNull = new SolicitacaoLimitada(u1, null);
		
		// O código não pode ser nulo ou inválido.
		assertFalse(viagem.adicionarParticipante(solicitacaoLimitadaNull));
	}
	
	public void deveFuncionarNoBD(){
		// Testes para o BD:
		Viagem v1 = null;
		Viagem v2 = null;
		Viagem v3 = null;
		Viagem v4 = null;
		
		// INSERÇÃO
		try {
			v1 = new Viagem(alterDoChao, alterDoChaoDesc, new Date(), tipoDeViagemAberta, participantesFake);
			v2 = new Viagem(monteRoraima, monteRoraimaDesc, new Date(),tipoDeViagemAberta, participantesFake);
			v3 = new Viagem(alterDoChao, alterDoChaoDesc, new Date(),new ViagemLimitada("123456"), participantesFake);
			v4 = new Viagem(monteRoraima, monteRoraimaDesc, new Date(), new ViagemLimitada("654321"), participantesFake);
			
			// testa a persistência.
			dao.persist(v1);	dao.persist(v2);
			assertEquals(2, dao.findAllByClassName("Viagem").size());
			
			dao.persist(v3);	dao.persist(v4);
			assertEquals(4, dao.findAllByClassName("Viagem").size());
			
			// nenhum objeto deve ser persistido mais de uma vez.
			dao.persist(v1);	dao.persist(v2);
			dao.persist(v3);	dao.persist(v4);
			assertEquals(4, dao.findAllByClassName("Viagem").size());				
		} catch (Exception e) {
			fail();
		}
		
		// EDIÇÃO/UPDATE
		try {
			v1.setLocal(preikestolen_Noruega);
			
			Calendar calendar = Calendar.getInstance();			
			calendar.add(Calendar.MONTH, 5);		
			v2.setData(calendar.getTime());
			
			v3.setDescricao(ilha_MauritiusDesc);
			
			dao.merge(v1);	dao.merge(v2);	dao.merge(v3);
			
			List<Viagem> viagenDoBD = dao.findAllByClassName("Viagem");
			
			int index = viagenDoBD.indexOf(v1);
			assertEquals(preikestolen_Noruega, viagenDoBD.get(index).getLocal());
			
			index = viagenDoBD.indexOf(v2);
			assertEquals(calendar.getTime(), viagenDoBD.get(index).getData());
			
			index = viagenDoBD.indexOf(v3);
			assertEquals(ilha_MauritiusDesc, viagenDoBD.get(index).getDescricao());
			
			// trocar a estrategia da viagem 4 para Aberta.
			v4.setEstrategia(new ViagemAberta());
			dao.merge(v4);		
			
			// trocar a estrategia da viagem 1 para Fechada.
			v1.setEstrategia(new ViagemLimitada("6797865"));
			dao.merge(v1);
			
			// Recupera todas as viagens do banco.
			viagenDoBD = dao.findAllByClassName("Viagem");
			
			// Verifica se a estrategia da viagem 4 foi alterada.
			index = viagenDoBD.indexOf(v4);
			assertTrue(viagenDoBD.get(index).getEstrategia() instanceof ViagemAberta);
			
			// Verifica se a estrategia da viagem 4 foi alterada.
			index = viagenDoBD.indexOf(v1);
			assertTrue(viagenDoBD.get(index).getEstrategia() instanceof ViagemLimitada);			
		} catch (Exception e) {
			fail();
		}
		
		// REMOÇÃO
		dao.remove(v1);
		assertEquals(3, dao.findAllByClassName("Viagem").size());
		
		dao.remove(v1);
		assertEquals(3, dao.findAllByClassName("Viagem").size());
		
		dao.remove(v2);		dao.remove(v3);			dao.remove(v4);
		assertEquals(3, dao.findAllByClassName("Viagem").size());
	}
	
	// TESTA A ADIÇÃO DE PARTICIPANTES NAS VIAGENS USANDO O BD.
	public void deveAdicionarPartiipanteViagemBD(){
		Viagem v1;
		Viagem v2;
		
		try {
			v1 = new Viagem(alterDoChao, alterDoChaoDesc, new Date(), tipoDeViagemAberta, participantesFake);
			v2 = new Viagem(alterDoChao, alterDoChaoDesc, new Date(), new ViagemLimitada("123456"), participantesFake);
			
			dao.persist(v1);	dao.persist(v2);			
			List<Viagem> viagenDoBD = dao.findAllByClassName("Viagem");
			// para a viagem 1
			int index = viagenDoBD.indexOf(v1);
			assertEquals(0, viagenDoBD.get(index).countParticipantes());
			
			// para a viagem 2
			index = viagenDoBD.indexOf(v2);
			assertEquals(0, viagenDoBD.get(index).countParticipantes());
			
			Usuario u1 = new Usuario("fulano_1", "fulano1@gmail.com", "1234569");
			Usuario u2 = new Usuario("fulano_2", "fulano2@gmail.com", "2132434");
			Usuario u3 = new Usuario("fulano_3", "fulano3@gmail.com", "9866122");
			Usuario u4 = new Usuario("fulano_4", "fulano4@gmail.com", "0873256");
			Usuario u5 = new Usuario("fulano_5", "fulano5@gmail.com", "de2343ef2");
			dao.persist(u1);	dao.persist(u2);	dao.persist(u3);	
			dao.persist(u4);	dao.persist(u5);
			
			SolicitacaoAberta solicitAberta1 = new SolicitacaoAberta(u1);
			v1.adicionarParticipante(solicitAberta1);
			
			SolicitacaoAberta solicitAberta2 = new SolicitacaoAberta(u2);
			v1.adicionarParticipante(solicitAberta2);
			
			SolicitacaoAberta solicitAberta3 = new SolicitacaoAberta(u3);
			v1.adicionarParticipante(solicitAberta3);
			
			SolicitacaoLimitada solicitLimitada1 = new SolicitacaoLimitada(u4, "123456");
			v2.adicionarParticipante(solicitLimitada1);
			
			SolicitacaoLimitada solicitLimitada2 = new SolicitacaoLimitada(u5, "123456");
			v2.adicionarParticipante(solicitLimitada2);	  
			
			dao.merge(v1);		dao.merge(v2);
			
			// Recupera todas as viagens.
			viagenDoBD = dao.findAllByClassName("Viagem");
			
			// para a viagem 1
			index = viagenDoBD.indexOf(v1);
			assertEquals(3, viagenDoBD.get(index).countParticipantes());

			// para a viagem 2
			index = viagenDoBD.indexOf(v2);
			assertEquals(2, viagenDoBD.get(index).countParticipantes());
		} catch (Exception e) {
			fail();
		}
	}
}
