package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.dto.EnderecoDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Endereco;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EnderecoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class EnderecoServiceTest {

	private static final String SENHA = "123";

	private static final String CEP = "38777012";

	private static final String BAIRRO = "Centro";

	private static final String COMPLEMENTO = "Sala 800";

	private static final String NUMERO = "105";

	private static final String RUA = "Avenida Matos";

	private static final String CIDADE = "Assis";

	private static final String ESTADO = "São Paulo";

	private static final String CPF = "123.123.123-78";

	private static final String EMAIL = "Teste@gmail.com";

	private static final String NOME = "José";

	private static final long ID = 1L;

	@Mock
	private EnderecoRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CidadeRepository cidadeRepository;

	@InjectMocks
	private EnderecoService service;

	private Optional<Endereco> optionalEndereco;
	private Endereco endereco;

	private EnderecoDTO enderecoDTO;

	private Optional<Cidade> optionalCidade;
	private CidadeDTO cidadeDTO;

	private Cidade cidade;

	private EstadoDTO estadoDTO;
	private Estado estado;

	private Optional<User> optionalUser;
	private User user;
	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEndereco();
	}

	@Test
	void whenFindByIdThenReturnEnderecoDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalEndereco);
		EnderecoDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(EnderecoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(RUA, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertNotNull(response.getCidade());
		assertEquals(ID, response.getCidade().getId());
		assertEquals(CIDADE, response.getCidade().getName());
		assertNotNull(response.getCidade().getEstadoId());
		assertEquals(ID, response.getCidade().getEstadoId().getId());
		assertEquals(ESTADO, response.getCidade().getEstadoId().getName());
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenCreateEnderecoThenReturnEnderecoDTO() {
		Long cidadeId = ID;
		Long userId = ID;
		when(cidadeRepository.findById(anyLong())).thenReturn(optionalCidade);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(repository.save(any(Endereco.class))).thenReturn(endereco);
		EnderecoDTO response = service.create(enderecoDTO);
		assertNotNull(response);
		assertEquals(EnderecoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(RUA, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertEquals(CEP, response.getCep());
		assertNotNull(response.getCidade());
		assertNotNull(response.getUser());
		verify(cidadeRepository).findById(cidadeId);
		verify(userRepository).findById(userId);
		verify(repository).save(any(Endereco.class));
	}

	private void startEndereco() {
		estadoDTO = new EstadoDTO(ID, NOME);
		cidadeDTO = new CidadeDTO(ID, BAIRRO, estadoDTO);
		user = new User(ID, NOME, EMAIL, CPF, SENHA);
		estado = new Estado(ID, ESTADO);
		cidade = new Cidade(ID, CIDADE, estado);
		endereco = new Endereco(ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, cidade, user);
		userDTO = new UserDTO(ID, BAIRRO, EMAIL, CPF, null);
		enderecoDTO = new EnderecoDTO(ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, cidadeDTO, userDTO);
		optionalEndereco = Optional.of(endereco);
		optionalCidade = Optional.of(cidade);
		optionalUser = Optional.of(user);
	}
}
