package org.kryptokrona.sdk.repository;

import org.kryptokrona.sdk.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Wallet Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}