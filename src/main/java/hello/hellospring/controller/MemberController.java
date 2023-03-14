package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member(); // 멤버 인스턴스 생성
        member.setName(form.getName()); // form에서 name을 받아 이름을 저장

        memberService.join(member); // 회원가입

        return "redirect:/"; // 회원가입 후, home(/) 화면으로 redirect
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        // memberService에서 findMembers 메소드를 통해 members 모두 가져옴
        List<Member> members = memberService.findMembers();
        // 가져온 members의 list를 model에 담아서 화면(view)에 넘김
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
