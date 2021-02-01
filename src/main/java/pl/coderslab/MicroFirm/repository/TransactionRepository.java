package pl.coderslab.MicroFirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.MicroFirm.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    String sqlQueryGetTransactionWithMostRecentInvoiceNo = "select * from transactions\n" +
            "where invoice_no is not null\n" +
            "and transaction_date>= ?1\n" +
            "and transaction_date<= ?2\n" +
            "order by invoice_no desc\n" +
            "limit 1";
    @Query(value = sqlQueryGetTransactionWithMostRecentInvoiceNo, nativeQuery = true)
    Optional<Transaction> getTransactionWithMostRecentInvoiceNo(LocalDate startDate, LocalDate endDate);

    List<Transaction> findAllByCustomer_Id(long id);
}
