package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put((member.getId()),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       return Optional.ofNullable(store.get(id)); //key값 id를 통해 value값인 Member를 반환해줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //람다 함수 활용
                .filter(member->member.getName().equals(name))
                .findAny(); //해당 이름과 일치하는게있으면 객체를 반환해줌
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 있는 모든 멤버 반환
    }

    public void clearStore(){
        store.clear();
    }
}
