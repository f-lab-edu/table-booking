package me.app.tablebooking.application.port.out;

import me.app.tablebooking.application.domain.model.Member;

public interface MemberPort {
    void save(Member member);
    Member findByUsername(String username);
}
