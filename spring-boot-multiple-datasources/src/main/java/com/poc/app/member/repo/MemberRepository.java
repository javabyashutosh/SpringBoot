package com.poc.app.member.repo;

import org.springframework.data.repository.CrudRepository;

import com.poc.app.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Integer>{

}
