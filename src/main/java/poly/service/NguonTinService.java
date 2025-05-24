package poly.service;

import poly.entity.NguonTin;
import java.util.List;
import java.util.Optional;

public interface NguonTinService {
    List<NguonTin> findAll();
    Optional<NguonTin> findById(Integer id);
    NguonTin save(NguonTin nguonTin);
    void deleteById(Integer id);
}
