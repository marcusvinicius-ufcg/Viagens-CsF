import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import models.Usuario;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void deveCriarUmUsuario() {
		try {
			new Usuario("João José da Silva", "joao_jose@mail.com", "123456");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		
		// TESTE PARA NOME MUITO LONGO
		try {
			new Usuario(
					"João José da Silva Maria da Penha do Ultimo Socorro Pereira Lima Roberto",
					"joao_jose@mail.com", "123456");
			fail();
		} catch (Exception e) {
			assertEquals("Nome muito longo!", e.getMessage());
		}

		// TESTE PARA E-MAIL MUITO LONGO
		try {
			new Usuario(
					"João José da Silva",
					"joao_jose_da_silva_maria_da_penha_do_ultimo_socorro_pereira_lima@mail.com",
					"123456");
			fail();
		} catch (Exception e) {
			assertEquals("E-mail muito longo!", e.getMessage());
		}

		// TESTE PARA NOME NULO
		try {
			new Usuario(null, "joao_jose@mail.com", "123456");
			fail();
		} catch (Exception e) {
			assertEquals("Nome nulo!", e.getMessage());
		}
		// TESTE PARA E-MAIL NULO
		try {
			new Usuario("João José da Silva", null, "123456");
			fail();
		} catch (Exception e) {
			assertEquals("E-mail nulo!", e.getMessage());
		}

		// TESTE PARA E-MAIL NAO VALIDO
		try {
			new Usuario("João José da Silva", "joao_jose_mail.com", "123456");
			fail();
		} catch (Exception e) {
			assertEquals("E-mail não é válido!", e.getMessage());
		}

		// TESTE PARA SENHA MENOR QUE 6
		try {
			new Usuario("João José da Silva", "joao_jose@mail.com", "12345");
			fail();
		} catch (Exception e) {
			assertEquals("Senha deve ter 6 à 8 caracteres!", e.getMessage());
		}
		
		// TESTE PARA SENHA MAIOR QUE 8
		try {
			new Usuario("João José da Silva", "joao_jose@mail.com", "123456789");
			fail();
		} catch (Exception e) {
			assertEquals("Senha deve ter 6 à 8 caracteres!", e.getMessage());
		}
	}
}
