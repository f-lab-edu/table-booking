package me.app.tablebooking.adapter.out.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("""
                INSERT INTO member (username, password, member_role, name, phone_number, created_at, modified_at)
                VALUES (#{username}, #{password}, #{memberRole}, #{name}, #{phoneNumber}, #{createdAt}, #{modifiedAt})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MemberEntity member);

    @Select("SELECT id, username, password, member_role as memberRole, name, phone_number as phoneNumber, created_at as createdAt, modified_at as modifiedAt FROM member WHERE username = #{username}")
    MemberEntity findByUsername(String username);

}
