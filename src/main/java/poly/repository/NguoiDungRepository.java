package poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);
    boolean existsByEmail(String email);
}
