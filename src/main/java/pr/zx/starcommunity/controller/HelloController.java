package pr.zx.starcommunity.controller;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String greeting(@RequestParam(name="zxname", required=false, defaultValue="World") String name,
                           @RequestParam(name="zxage", required=false, defaultValue="World") String age,Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "greeting";
    }

}