package spring_mvc.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring_mvc.servlet.web.frontcontroller.MyView;

import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
