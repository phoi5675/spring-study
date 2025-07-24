package spring_mvc.servlet.web.frontcontroller.v3.controller;

import spring_mvc.servlet.domain.member.Member;
import spring_mvc.servlet.domain.member.MemberRepository;
import spring_mvc.servlet.web.frontcontroller.ModelView;
import spring_mvc.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
