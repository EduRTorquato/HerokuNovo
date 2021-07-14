package exercicio.example.exercicio1.models;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import exercicio.example.exercicio1.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {
	
	public Usuario usuario;
	
	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("Amanda Boaz", "amandaBo", "123456");
	}

	@Test
	void testValidaAtributosCorreto() {
		Set<ConstraintViolation<Usuario>> validacao = validator.validate(usuario);
		System.out.println(validacao.toString());
		assertTrue(validacao.isEmpty());
	}
	
	@Test
	void testValidaAtributosRetornaErro() {
		Usuario usuarioErro = new Usuario();
		usuarioErro.setNome("Guilherme Boaz");
		Set<ConstraintViolation<Usuario>> validacao = validator.validate(usuarioErro);
		assertFalse(validacao.isEmpty());
	}

}