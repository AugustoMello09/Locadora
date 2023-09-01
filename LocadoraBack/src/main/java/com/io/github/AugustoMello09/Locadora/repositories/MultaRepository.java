package com.io.github.AugustoMello09.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entity.Multa;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

}
