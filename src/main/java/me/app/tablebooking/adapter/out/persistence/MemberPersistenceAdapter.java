package me.app.tablebooking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.port.out.MemberPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberMapper memberMapper;
    private final MemberEntityMapper memberEntityMapper;

    @Override
    public void save(Member member) {
        MemberEntity entity = memberEntityMapper.toEntity(member);
        memberMapper.insert(entity);
    }

    public Member findByUsername(String username){
        MemberEntity entity = memberMapper.findByUsername(username);
        return memberEntityMapper.toDomain(entity);
    }
}
