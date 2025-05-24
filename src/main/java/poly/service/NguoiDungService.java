package poly.service;

import poly.entity.NguoiDung;
import java.util.List;
import java.util.Optional;

public interface NguoiDungService {
    List<NguoiDung> findAll();
    Optional<NguoiDung> findById(Long id);
    NguoiDung save(NguoiDung nguoiDung);
    void deleteById(Long id);
}
