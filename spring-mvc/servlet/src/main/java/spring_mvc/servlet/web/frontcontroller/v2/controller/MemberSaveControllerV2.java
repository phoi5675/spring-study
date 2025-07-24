package spring_mvc.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring_mvc.servlet.domain.member.Member;
import spring_mvc.servlet.domain.member.MemberRepository;
import spring_mvc.servlet.web.frontcontroller.MyView;
import spring_mvc.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        req.setAttribute("member", member);
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
