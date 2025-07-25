package spring_mvc.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring_mvc.servlet.web.frontcontroller.ModelView;
import spring_mvc.servlet.web.frontcontroller.MyView;
import spring_mvc.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import spring_mvc.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import spring_mvc.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import spring_mvc.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import spring_mvc.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import spring_mvc.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import spring_mvc.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import spring_mvc.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        // V3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView modelView = adapter.handle(req, resp, handler);

        String viewName = modelView.getViewName();

        MyView view = viewResolver(viewName);
        view.render(modelView.getModel(), req, resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) throws IllegalArgumentException {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("Can't find handler adapter for " + handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
}
