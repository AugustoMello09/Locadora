package com.io.github.AugustoMello09.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entity.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

}
