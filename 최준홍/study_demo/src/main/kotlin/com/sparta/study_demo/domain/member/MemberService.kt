package com.sparta.study_demo.domain.member

import com.sparta.study_demo.domain.member.dto.req.MemberCreateRequestDto
import com.sparta.study_demo.domain.member.dto.res.MemberResponseDto

interface MemberService {

    fun createMember(memberCreateRequestDto:MemberCreateRequestDto):MemberResponseDto;
}