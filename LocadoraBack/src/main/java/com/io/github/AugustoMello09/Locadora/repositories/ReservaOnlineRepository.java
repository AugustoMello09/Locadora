package com.io.github.AugustoMello09.Locadora.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;

@Repository
public interface ReservaOnlineRepository extends JpaRepository<ReservaOnline, Long> {

	@Query("SELECT r FROM ReservaOnline r WHERE r.user.id = ?1")
	Page<ReservaOnline> findByUser_Id(Long userId, Pageable pageable);
	
	@Query("SELECT r FROM ReservaOnline r WHERE r.user.id = ?1")
	Optional<ReservaOnline> findByUserId(Long id, Long userId);

}
