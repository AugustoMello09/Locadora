package com.io.github.AugustoMello09.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	Cidade findByName(String name);
}
