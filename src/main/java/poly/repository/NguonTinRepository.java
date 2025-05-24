package poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.entity.NguonTin;

public interface NguonTinRepository extends JpaRepository<NguonTin, Integer> {
    boolean existsByTenNguon(String tenNguon);
}
