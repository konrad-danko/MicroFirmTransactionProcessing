package pl.coderslab.MicroFirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.MicroFirm.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
