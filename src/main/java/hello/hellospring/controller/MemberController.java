package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {//@Autowired :스프링 컨테이너에서 memberservice를 가져와줌
        this.memberService = memberService;
    }

    //회원 가입
    @GetMapping("/members/new")
    public String createFrom(){
        return "members/createMemberForm";

    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; //회원가입이되면 홈화면으로 돌아가도록
    }

    //회원 조회
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}