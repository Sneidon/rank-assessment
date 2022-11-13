package com.rank.startup.repository;

import com.rank.startup.entities.Player;
import com.rank.startup.entities.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByUsernameIgnoreCase(String username);

    @Query(value = "SELECT t FROM Transaction t WHERE t.player = ?1")
    List<Transaction> findTransactionsByPlayer(@Param("player") Player player, Pageable pageable);
}