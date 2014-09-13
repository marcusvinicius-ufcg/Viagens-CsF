import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Solicitacao;
import models.SolicitacaoAberta;
import models.SolicitacaoLimitada;
import models.Usuario;
import models.ViagemAberta;
import models.ViagemLimitada;


public class TipoDeViagemTest {

	private Usuario usuario;
	private Solicitacao solicitacaoAberta;
	private Solicitacao solicitacaoLimitada;
	private Solicitacao outraSolicitacaoLimitada;
	
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario("Teste", "teste@teste.com", "123456");
		solicitacaoAberta = new SolicitacaoAberta(usuario);
		solicitacaoLimitada = new SolicitacaoLimitada(usuario, "123");
		outraSolicitacaoLimitada = new SolicitacaoLimitada(usuario, "ASDASD");
	}

	@Test
	public void testaAdcionarEmTipoDeViagemAberta() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		ViagemAberta aberta = new ViagemAberta();
		
		assertTrue(lista.size() == 0);
		
		assertTrue(aberta.adicionarParticipante(lista, solicitacaoAberta));
		
		//NAO DEVE ADICIONAR USUARIOS JA ADICIONADOS
		assertFalse(aberta.adicionarParticipante(lista, solicitacaoAberta));
		
		assertTrue(lista.contains(usuario));
		
		assertTrue(lista.size() == 1);
	}
	
	@Test
	public void testaAdcionarEmTipoDeViagemLimitada() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		ViagemLimitada limitada = new ViagemLimitada();
		
		//DEVE SER NULA QUANDO INSTANCIADA COM CONTRUTOR VAZIO
		assertNull(limitada.getCodigo());
		
		try {
			limitada = new ViagemLimitada(null);
			fail();
			
		} catch (Exception e) {
			assertEquals("Código Nulo ou Vazio!", e.getMessage());
		}
		
		try {
			limitada = new ViagemLimitada("");
			fail();
			
		} catch (Exception e) {
			assertEquals("Código Nulo ou Vazio!", e.getMessage());
		}

		try {
			limitada = new ViagemLimitada("12");
			fail();
			
		} catch (Exception e) {
			assertEquals("Código deve ter no Minimo 3 Caracteres!", e.getMessage());
		}
		
		try {
			limitada = new ViagemLimitada("123");
			assertNotNull(limitada.getCodigo());
			
		} catch (Exception e) {
			fail();
		}
		
		//NAO DEVE SER NULA QUANDO INSTANCIADA POR CONTRUTOR DEFAULT
		assertNotNull(limitada.getCodigo());
		
		assertTrue(lista.size() == 0);
		
		//CODIGO DA SOLICITACAO DIFERENTE DA ESTRATEGIA
		assertFalse(limitada.adicionarParticipante(lista, outraSolicitacaoLimitada));
		
		assertTrue(lista.size() == 0);
		
		//DEVE ADICIONAR
		assertTrue(limitada.adicionarParticipante(lista, solicitacaoLimitada));
		
		//NAO DEVE ADICIONAR USUARIOS JA ADICIONADOS
		assertFalse(limitada.adicionarParticipante(lista, solicitacaoLimitada));
		
		assertTrue(lista.contains(usuario));
		
		assertTrue(lista.size() == 1);
	}
}
