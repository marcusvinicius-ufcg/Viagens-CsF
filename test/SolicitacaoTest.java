import static org.junit.Assert.*;
import models.Solicitacao;
import models.SolicitacaoAberta;
import models.SolicitacaoLimitada;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;


public class SolicitacaoTest {
	
	private Usuario usuario;
	private Solicitacao aberta;
	private Solicitacao limitada;
	
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario("Teste", "teste@teste.com", "123456");
		aberta = new SolicitacaoAberta(usuario);
		limitada = new SolicitacaoLimitada(usuario, "123");
	}

	@Test
	public void testAberta() {
		
		assertNotNull(aberta);
		
		assertNotNull(aberta.getUsuario());
		
		assertTrue(aberta.getUsuario().equals(usuario));
	}
	
	@Test
	public void testLimitada() {
		
		assertNotNull(limitada);
		
		assertNotNull(limitada.getUsuario());
		assertNotNull(limitada.getCodigo());
		
		assertTrue(limitada.getUsuario().equals(usuario));
		assertTrue(limitada.getCodigo().equals("123"));
	}
	
	@Test
	public void casosLimites() {
		
		try {
			aberta = new SolicitacaoAberta(usuario);
			//DEVE LANCAR EXCEPTION, POIS SOLICITACAO ABERTA NAO POSSUI CODIGO
			aberta.getCodigo();
			fail();
		} catch (Exception e) {
			assertEquals("Solicitacao Aberta Não Possui Código.", e.getMessage());;
		}
	}
}
