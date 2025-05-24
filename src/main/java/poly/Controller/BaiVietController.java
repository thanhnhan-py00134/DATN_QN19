package poly.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.entity.BaiViet;
import poly.repository.BaiVietRepository;
import poly.repository.DanhMucRepository;
import poly.repository.NguonTinRepository;
import poly.service.BaiVietService;
import poly.repository.NguoiDungRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/baiviet")
@RequiredArgsConstructor
public class BaiVietController {

    private final BaiVietRepository baiVietRepo;
    private final DanhMucRepository danhMucRepo;
    private final NguonTinRepository nguonTinRepo;
    private final NguoiDungRepository nguoiDungRepo;
    @Autowired
    private BaiVietService baiVietService;
    @GetMapping
    public String hienThiDanhSach(Model model) {
        List<BaiViet> danhSachBaiViet = baiVietService.getAllBaiViet();
        model.addAttribute("listBaiViet", danhSachBaiViet);
        return "baiviet"; // trỏ đến file baiviet.html
    }
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("baiViet", new BaiViet());
        model.addAttribute("baiViets", baiVietRepo.findAll());
        model.addAttribute("danhMucs", danhMucRepo.findAll());
        model.addAttribute("nguonTins", nguonTinRepo.findAll());
        return "Admin/baiviet";
    }

    @PostMapping
    public String save(@ModelAttribute BaiViet baiViet,
                       @RequestParam(name = "hinhAnhBase64", required = false) String hinhAnhBase64,
                       @RequestParam(name = "danhMuc") Integer danhMucId,
                       @RequestParam(name = "nguonTin") Integer nguonTinId) {

        if (baiViet.getMaBaiViet() == null) {
            baiViet.setNgayTao(LocalDateTime.now());
            baiViet.setLuotXem(0);
        } else {
            baiViet.setNgaySua(LocalDateTime.now());
        }

        if (hinhAnhBase64 != null && !hinhAnhBase64.isBlank()) {
            baiViet.setHinhAnh(hinhAnhBase64);
        }

        // Gán Danh Mục
        danhMucRepo.findById(danhMucId).ifPresent(baiViet::setDanhMuc);

        // Gán Nguồn Tin
        nguonTinRepo.findById(nguonTinId).ifPresent(baiViet::setNguonTin);

        // Gán tác giả nếu có logic (ví dụ: người đang đăng nhập)
        baiViet.setTacGia(null);

        baiViet.setNgayDang(LocalDateTime.now());

        baiVietRepo.save(baiViet);
        return "redirect:/admin/baiviet";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<BaiViet> baiVietOpt = baiVietRepo.findById(id);
        if (baiVietOpt.isPresent()) {
            model.addAttribute("baiViet", baiVietOpt.get());
            model.addAttribute("baiViets", baiVietRepo.findAll());
            model.addAttribute("danhMucs", danhMucRepo.findAll());
            model.addAttribute("nguonTins", nguonTinRepo.findAll());
            return "Admin/baiviet";
        } else {
            return "redirect:/admin/baiviet";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        baiVietRepo.deleteById(id);
        return "redirect:/admin/baiviet";
    }
}
