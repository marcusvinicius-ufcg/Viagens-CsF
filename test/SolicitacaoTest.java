import static org.junit.Assert.*;

import models.SolicitacaoAberta;
import models.SolicitacaoLimitada;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;


public class SolicitacaoTest {
	
	private Usuario usuario;
	private SolicitacaoAberta aberta;
	private SolicitacaoLimitada limitada;
	
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
	}

}