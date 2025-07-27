package spring_mvc.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        logger.info("info log = {}", name);

        return "ok";
    }
}
