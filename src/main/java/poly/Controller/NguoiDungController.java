package poly.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.entity.NguoiDung;
import poly.repository.NguoiDungRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/nguoidung")
@RequiredArgsConstructor
public class NguoiDungController {

    private final NguoiDungRepository nguoiDungRepo;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "Admin/nguoidung";
    }

    @PostMapping
    public String save(@ModelAttribute NguoiDung nguoiDung) {
        nguoiDung.setNgayTao(LocalDateTime.now());
        nguoiDungRepo.save(nguoiDung);
        return "redirect:/Admin/nguoidung";
    }
}
