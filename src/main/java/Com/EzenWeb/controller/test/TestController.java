package Com.EzenWeb.controller.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class TestController {
    @GetMapping("")
    public Resource gettext(){
        return new ClassPathResource("templates/test1.html");
    }
}
