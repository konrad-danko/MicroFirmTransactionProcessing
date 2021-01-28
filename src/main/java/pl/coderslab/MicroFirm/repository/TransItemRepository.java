package pl.coderslab.MicroFirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.MicroFirm.model.TransItem;

import java.util.List;

public interface TransItemRepository extends JpaRepository<TransItem, Long> {

    List<TransItem> findAllByTransaction_Id(long id);

    List<TransItem> findAllByProduct_Id(long id);
}
