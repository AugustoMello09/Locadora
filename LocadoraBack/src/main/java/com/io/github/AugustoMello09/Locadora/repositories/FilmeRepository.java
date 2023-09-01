package com.io.github.AugustoMello09.Locadora.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	@Query("SELECT obj FROM Filme obj WHERE obj.categoria.id = :idCategoria ORDER BY nome")
	List<Filme> findAllByCategory(@Param(value = "idCategoria")Long idCategoria);
	
	Optional<Filme> findByEstoque(Estoque estoque);

}
