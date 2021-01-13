package pl.coderslab.MicroFirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.MicroFirm.model.FirmData;

public interface FirmDataRepository extends JpaRepository<FirmData, Long> {
}
