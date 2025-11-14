package org.example.pw;

import org.example.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @DisplayName("패스워드를 초기화함")
    @Test
    void passwordTest() {
        // given
        var user = new User();
        // when
        user.initPassword(() -> "aaabbbcc");
        user.initPassword(new CorrectPasswordGenerator());
        // then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합되지 초기화가 되지 않는다.")
    @Test
    void passwordTest2() {
        // given
        var user = new User();
        // when
        user.initPassword(new WrongPasswordGenerator());
        // then
        assertThat(user.getPassword()).isNull();
    }
}