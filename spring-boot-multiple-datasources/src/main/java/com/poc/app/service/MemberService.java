package com.poc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poc.app.member.entity.Member;
import com.poc.app.member.repo.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Member saveMemberInfo(Member member) {
		return memberRepository.save(member);
	}
	
	public List<Member> fetchAllMembers() {
		return (List<Member>) memberRepository.findAll();
	}

}
