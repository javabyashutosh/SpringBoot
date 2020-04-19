package com.poc.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.app.card.entity.Card;
import com.poc.app.member.entity.Member;
import com.poc.app.service.CardService;
import com.poc.app.service.MemberService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProcessController {

	private final MemberService memberService;

	private final CardService cardService;

	@PostMapping("/saveMember")
	//@ResponseStatus(code = HttpStatus.CREATED, reason = "Member Details saved successfully")
	@ApiResponses({
		@ApiResponse(code =200,message = "Member Details saved successfully into database"),
	})
	public Member saveMemberInfo(@RequestBody Member member) {
		member = memberService.saveMemberInfo(member);
		return member;
	}

	@PostMapping("/saveCard")
	@ApiResponses({
		@ApiResponse(code =200,message = "Card Details saved successfully into database")
	})
	//@ResponseStatus(code = HttpStatus.CREATED, reason = "Card Details saved successfully")
	public Card saveCardInfo(@RequestBody Card card) {
		card = cardService.saveCardInfo(card);
		return card;
	}
	
	@GetMapping("/getMembers")
	@ApiResponses({
		@ApiResponse(code =200,message = "Members Details fetched successfully from database")
	})
	public List<Member> getAllMembers(){
		
		return memberService.fetchAllMembers();
	}
}
