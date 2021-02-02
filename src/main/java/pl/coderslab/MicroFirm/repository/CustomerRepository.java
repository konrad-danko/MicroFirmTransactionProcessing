package pl.coderslab.MicroFirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.MicroFirm.model.Customer;
import pl.coderslab.MicroFirm.model.User;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    String sqlQueryAllWithGivenUser = "select * from customers where\n" +
            "created_by_user_id = ?1 or\n" +
            "updated_by_user_id = ?1";
    @Query(value = sqlQueryAllWithGivenUser, nativeQuery = true)
    List<Customer> findAllWithGivenUser(User user);
}
