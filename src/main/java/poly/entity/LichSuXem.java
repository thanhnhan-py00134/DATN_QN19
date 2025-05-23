package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LichSuXem")
public class LichSuXem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLichSu;
    
    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;
    
    @ManyToOne
    @JoinColumn(name = "MaBaiViet")
    private BaiViet baiViet;
    
    @Column(name = "ThoiGianXem")
    private LocalDateTime thoiGianXem = LocalDateTime.now();
}