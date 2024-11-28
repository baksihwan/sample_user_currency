package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @Length(max = 50, message = "이메일은 50자 이내 입니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Length(max = 6, message = "이름은 6자 이내 입니다.")
    private String name;

    public User toEntity() {
        return new User(
                this.email,
                this.name
        );
    }
}
