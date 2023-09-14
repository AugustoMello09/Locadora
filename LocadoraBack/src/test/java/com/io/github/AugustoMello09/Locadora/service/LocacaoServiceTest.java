package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTOPList;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.entity.Locacao;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.LocacaoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class LocacaoServiceTest {

	private static final String SENHA = "123";

	private static final FormaPagamento BOLETO = FormaPagamento.BOLETO;

	private static final String CPF = "cpf";

	private static final String EMAIL = "email";

	private static final double PRESO = 19.0;

	private static final String DESCRIÇAO = "teste";

	private static final String NOME = "teste";

	private static final int QUANTIDADE = 1;

	private static final StatusEstoque UNDEFINED = StatusEstoque.UNDEFINED;

	private static final long ID = 1L;

	@Mock
	private LocacaoRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private FilmeRepository filmeRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private LocacaoService service;

	private Locacao locacao;
	private LocacaoDTO locacaoDTO;
	private Optional<Locacao> optionalLocacao;

	private Filme filme;
	private FilmeDTO filmeDTO;
	private Optional<Filme> optionalFilme;

	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;

	private Estoque estoque;
	private Categoria categoria;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startLocacao();
	}

	@Test
	public void whenFindByIdThenReturnLocacaoDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalLocacao);
		LocacaoDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(LocacaoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtd());
		assertEquals(BOLETO, response.getFormaPagamento());
		assertNotNull(response.getUser());
		assertEquals(ID, response.getUser().getId());
		assertEquals(NOME, response.getUser().getName());
		assertEquals(EMAIL, response.getUser().getEmail());
		assertEquals(CPF, response.getUser().getCpf());
		assertNotNull(response.getFilme());
		assertEquals(ID, response.getFilme().getId());
		assertEquals(NOME, response.getFilme().getNome());
		assertEquals(DESCRIÇAO, response.getFilme().getDescricao());
		assertEquals(NOME, response.getFilme().getDiretor());
		assertEquals(PRESO, response.getFilme().getValorAluguel());

	}

	@Test
	public void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}
	
	@Test
	void whenFindAllThenReturnListOfLocacaoDTO() {
        List<Locacao> lo = new ArrayList<>();
        lo.add(new Locacao(ID, QUANTIDADE, null, user, filme, BOLETO));
        when(repository.findAll()).thenReturn(lo);
        List<LocacaoDTOPList> loDTOs = service.findAll();
        verify(repository, times(1)).findAll();
        List<LocacaoDTOPList> expectedDTOs = lo.stream().map(LocacaoDTOPList::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(loDTOs);	
	}

	@Test
	public void testCreateLocacao() {
		Long idUser = ID;
		Long idFilme = ID;
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(filmeRepository.findById(anyLong())).thenReturn(optionalFilme);
		when(repository.save(any(Locacao.class))).thenReturn(locacao);
		LocacaoDTO response = service.create(locacaoDTO);
		verify(userRepository, times(1)).findById(idUser);
		verify(filmeRepository, times(1)).findById(idFilme);
		verify(repository, times(1)).save(any(Locacao.class));
		Assertions.assertNotNull(response);
		assertEquals(LocacaoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtd());
		assertEquals(BOLETO, response.getFormaPagamento());
		assertNotNull(response.getUser());
		assertEquals(ID, response.getUser().getId());
		assertEquals(NOME, response.getUser().getName());
		assertEquals(EMAIL, response.getUser().getEmail());
		assertEquals(CPF, response.getUser().getCpf());
		assertNotNull(response.getFilme());
		assertEquals(ID, response.getFilme().getId());
		assertEquals(NOME, response.getFilme().getNome());
		assertEquals(DESCRIÇAO, response.getFilme().getDescricao());
		assertEquals(NOME, response.getFilme().getDiretor());
		assertEquals(PRESO, response.getFilme().getValorAluguel());
		Assertions.assertEquals(response.getDataLocacao().plusDays(7), response.getDataMaxDevolucao());
	}

	@Test
	public void testCreateLocacao_FilmeNaoEncontrado_ExceptionThrown() {
		Long idUser = ID;
		Long idFilme = ID;
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(filmeRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(locacaoDTO));
		verify(userRepository, times(1)).findById(idUser);
		verify(filmeRepository, times(1)).findById(idFilme);
		verify(repository, never()).save(any(Locacao.class));
		verify(filmeRepository, never()).save(any(Filme.class));
	}

	@Test
	public void testDevolver_LocacaoEncontrada_DevolucaoRealizada() {
		Long idLocacao = 1L;
		LocacaoDTO dto = new LocacaoDTO();
		Locacao locacao = new Locacao();
		locacao.setDataMaxDevolucao(LocalDateTime.now().minusDays(1)); 
		Filme filme = new Filme();
		Estoque estoque = new Estoque();
		estoque.setQuantidade(5);
		filme.setEstoque(estoque);
		locacao.setFilme(filme);
		when(repository.findById(idLocacao)).thenReturn(Optional.of(locacao));
		service.devolver(idLocacao, dto);
		verify(repository, times(1)).save(locacao);
		verify(estoqueRepository, times(1)).save(estoque);
		assertTrue(locacao.getDataDevolucao().isAfter(LocalDateTime.now()));
		assertEquals(5, estoque.getQuantidade()); 
	}

	@Test
	public void testDevolver_LocacaoNaoEncontrada_ExceptionThrown() {
		Long idLocacao = 1L;
		LocacaoDTO dto = new LocacaoDTO();
		when(repository.findById(idLocacao)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> {
			service.devolver(idLocacao, dto);
		});
	}
	
    @Test
	public void testDevolver_QuantidadeDevolvidaMaiorQueAlugada() {
	        Long idLocacao = 1L;
	        LocacaoDTO dto = new LocacaoDTO();
	        dto.setQtd(5);
	        Locacao locacao = new Locacao();
	        locacao.setId(idLocacao);
	        locacao.setQtd(3); 
	        Filme filme = new Filme();
	        Estoque estoque = new Estoque();
	        estoque.setQuantidade(10);
	        filme.setEstoque(estoque);
	        locacao.setFilme(filme);
	        when(repository.findById(idLocacao)).thenReturn(Optional.of(locacao));
	        assertThrows(DataIntegratyViolationException.class, () -> service.devolver(idLocacao, dto));
	        verify(estoqueRepository, never()).save(any());
	}
	
	private void startLocacao() {
		categoria = new Categoria(ID, NOME);
		estoque = new Estoque(ID, QUANTIDADE, UNDEFINED);
		user = new User(ID, NOME, EMAIL, CPF, SENHA);
		filme = new Filme(ID, NOME, DESCRIÇAO, NOME, PRESO, categoria, estoque);
		locacao = new Locacao(ID, QUANTIDADE, null, user, filme, BOLETO);
		filmeDTO = new FilmeDTO(ID, NOME, DESCRIÇAO, NOME, PRESO, null, null);
		userDTO = new UserDTO(ID, DESCRIÇAO, EMAIL, CPF, null);
		locacaoDTO = new LocacaoDTO(ID, QUANTIDADE, BOLETO, null, null, null, userDTO, filmeDTO, null);
		optionalFilme = Optional.of(filme);
		optionalUser = Optional.of(user);
		optionalLocacao = Optional.of(locacao);
	}
	
	
}
