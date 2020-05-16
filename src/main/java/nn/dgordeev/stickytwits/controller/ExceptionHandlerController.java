package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.MVCError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest req,
                                  Exception e,
                                  Map<String, Object> model) {
        model.put("error", new MVCError(e.getMessage(), req.getServletPath(),
                req.getMethod(), LocalDateTime.now()));
        return "error";
    }
}
