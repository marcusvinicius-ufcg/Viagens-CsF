import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Usuario;
import models.ViagemAberta;
import models.ViagemLimitada;


public class TipoDeViagemTest {

	private Usuario usuario;
	
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario("Teste", "teste@teste.com", "123456");
	}

	@Test
	public void testaAdcionarEmTipoDeViagemAberta() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		ViagemAberta aberta = new ViagemAberta();
		
		assertTrue(lista.size() == 0);
		
		assertTrue(aberta.adicionarParticipante(lista, null, usuario));
		
		//NAO DEVE ADICIONAR USUARIOS JA ADICIONADOS
		assertFalse(aberta.adicionarParticipante(lista, null, usuario));
		
		assertTrue(lista.contains(usuario));
		
		assertTrue(lista.size() == 1);
		
	}
	
	@Test
	public void testaAdcionarEmTipoDeViagemLimitada() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		ViagemLimitada limitada = new ViagemLimitada();
		
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
		
		assertTrue(lista.size() == 0);
		
		//CODIGO INVALIDO
		assertFalse(limitada.adicionarParticipante(lista, "ASD", usuario));
		
		assertTrue(lista.size() == 0);
		
		assertFalse(limitada.adicionarParticipante(lista, "ASDAD", usuario));
		
		assertTrue(lista.size() == 0);
		
		assertFalse(limitada.adicionarParticipante(lista, "", usuario));
		
		assertTrue(lista.size() == 0);
		
		//DEVE ADICIONAR
		assertTrue(limitada.adicionarParticipante(lista, "123", usuario));
		
		//NAO DEVE ADICIONAR USUARIOS JA ADICIONADOS
		assertFalse(limitada.adicionarParticipante(lista, "123", usuario));
		
		assertTrue(lista.contains(usuario));
		
		assertTrue(lista.size() == 1);
		
	}
}
