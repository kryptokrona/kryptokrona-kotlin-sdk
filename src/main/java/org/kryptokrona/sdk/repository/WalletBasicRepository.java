package org.kryptokrona.sdk.repository;

import org.kryptokrona.sdk.model.wallet.WalletBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Wallet Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 */
@Repository
public interface WalletBasicRepository extends JpaRepository<WalletBasic, Long> {
}