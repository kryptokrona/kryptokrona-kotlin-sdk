package org.kryptokrona.sdk.repository;

import org.kryptokrona.sdk.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Transaction Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
}
