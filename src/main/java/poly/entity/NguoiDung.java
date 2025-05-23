package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNguoiDung;
    
    @Column(name = "TenDangNhap", nullable = false, unique = true, length = 50)
    private String tenDangNhap;
    
    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "MatKhau", nullable = false, length = 255)
    private String matKhau;
    
    @Column(name = "VaiTro", length = 20)
    private String vaiTro = "user";
    
    @Column(name = "PhuongThucDangNhap", length = 20)
    private String phuongThucDangNhap = "local";
    
    @Column(name = "MaPhuongThuc", length = 100)
    private String maPhuongThuc;
    
    @Column(name = "XacThucEmail")
    private Boolean xacThucEmail = false;
    
    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    @Column(name = "LanDangNhapCuoi")
    private LocalDateTime lanDangNhapCuoi;
}