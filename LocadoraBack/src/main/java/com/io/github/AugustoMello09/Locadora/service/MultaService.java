package com.io.github.AugustoMello09.Locadora.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.MultaDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComBoletoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComCartaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComPixDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Multa;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComBoleto;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComCartao;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComPix;
import com.io.github.AugustoMello09.Locadora.repositories.MultaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.PagamentoRepository;

@Service
public class MultaService {

	@Autowired
	private MultaRepository repository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public MultaDTO findById(Long id) {
		Optional<Multa> obj = repository.findById(id);
		Multa entity = obj.orElseThrow(() -> new ObjectNotFoundException("Multa não encontrada"));
		return new MultaDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<MultaDTO> findAll() {
		List<Multa> list = repository.findAll();
		List<MultaDTO> listDto = list.stream().map(x -> new MultaDTO(x)).collect(Collectors.toList());
		return listDto;
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void pagarMultaCartao(PagamentoComCartaoDTO pagamentoDTO) {
		MultaDTO multaDTO = pagamentoDTO.getMulta();
		Long multaId = multaDTO.getId();
		if (multaId != null) {
			Multa multa = repository.findById(multaId).orElseThrow(
					() -> new ObjectNotFoundException("Multa não encontrada"));
			if (multa != null && multa.getValor() > 0) {
				PagamentoComCartao pagamento = new PagamentoComCartao();
				pagamento.setNumeroParcelas(pagamentoDTO.getNumeroParcelas());
				pagamento.setformaPagamento(FormaPagamento.CARTAO);
				pagamento.setValor(pagamentoDTO.getValor());
				pagamento.setMulta(multa);
				pagamentoRepository.save(pagamento);
		        multa.setformaPamento(EstadoPagamento.QUITADO);
		        repository.save(multa);
			} else {
				throw new ObjectNotFoundException("Multa não encontrada");
			}
		}
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void pagarMultaPix(PagamentoComPixDTO pagamentoDTO) {
		MultaDTO multaDTO = pagamentoDTO.getMulta();
		Long multaId = multaDTO.getId();
		if (multaId != null) {
			Multa multa = repository.findById(multaId).orElseThrow(
					() -> new ObjectNotFoundException("Multa não encontrada"));
			if (multa != null && multa.getValor() > 0) {
				PagamentoComPix pagamento = new PagamentoComPix();
				pagamento.setDataPagamento(LocalDateTime.now());
				pagamento.setformaPagamento(FormaPagamento.PIX);
				pagamento.setValor(pagamentoDTO.getValor());
				pagamento.setMulta(multa);
				pagamentoRepository.save(pagamento);
		        multa.setformaPamento(EstadoPagamento.QUITADO);
		        repository.save(multa);
			} else {
				throw new ObjectNotFoundException("Multa não encontrada");
			}
		}

	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void pagarMultaBoleto(PagamentoComBoletoDTO pagamentoDTO) {
		MultaDTO multaDTO = pagamentoDTO.getMulta();
		Long multaId = multaDTO.getId();
		if (multaId != null) {
			Multa multa = repository.findById(multaId).orElseThrow(
					() -> new ObjectNotFoundException("Multa não encontrada"));
			if (multa != null && multa.getValor() > 0) {
				PagamentoComBoleto pagamento = new PagamentoComBoleto();
				pagamento.setDataVencimento(LocalDateTime.now().plusDays(30));
				pagamento.setDataGerada(LocalDateTime.now());
				pagamento.setDataPagamento(LocalDateTime.now());
				pagamento.setformaPagamento(FormaPagamento.BOLETO);
				pagamento.setValor(pagamentoDTO.getValor());
				pagamento.setMulta(multa);
				pagamentoRepository.save(pagamento);
		        multa.setformaPamento(EstadoPagamento.QUITADO);
		        repository.save(multa);
			} else {
				throw new ObjectNotFoundException("Multa não encontrada");
			}
		}

	}

}
