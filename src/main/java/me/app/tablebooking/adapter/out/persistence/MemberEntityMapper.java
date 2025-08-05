package me.app.tablebooking.adapter.out.persistence;

import me.app.tablebooking.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

    public MemberEntity toEntity(Member member) {
        return new MemberEntity(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getMemberRole(),
                member.getName(),
                member.getPhoneNumber(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    public Member toDomain(MemberEntity entity) {
        return new Member(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getMemberRole(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
