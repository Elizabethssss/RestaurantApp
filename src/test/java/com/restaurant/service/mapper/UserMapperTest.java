package com.restaurant.service.mapper;

import com.restaurant.domain.Role;
import com.restaurant.domain.User;
import com.restaurant.entity.UserEntity;
import com.restaurant.service.util.PasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    private static final Long ID = 1L;
    private static final String USERNAME = "Vasya";
    private static final String EMAIL = "vasilii@kiev.ua";
    private static final String PASSWORD = "123";
    private static final Role ROLE = Role.USER;

    @Mock
    private PasswordEncryptor passwordEncryptor;
    @InjectMocks
    private UserMapper userMapper;

    @Before
    public void init() {
        when(passwordEncryptor.encrypt(eq(PASSWORD))).thenReturn(PASSWORD);
    }

    @Test
    public void mapEntityToDomainShouldReturnUser() {
        final UserEntity userEntity = UserEntity.builder()
                .withId(ID)
                .withUsername(USERNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRole(ROLE)
                .build();

        final User user = userMapper.mapEntityToDomain(userEntity);
        assertThat("Mapping id is failed", user.getId(), is(ID));
        assertThat("Mapping username is failed", user.getUsername(), is(USERNAME));
        assertThat("Mapping email is failed", user.getEmail(), is(EMAIL));
        assertThat("Mapping password is failed", user.getPassword(), is(PASSWORD));
        assertThat("Mapping roles is failed", user.getRole(), is(ROLE));
    }

    @Test
    public void mapDomainToEntityShouldReturnUserEntity() {
        final User user = User.builder()
                .withId(ID)
                .withUsername(USERNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRole(ROLE)
                .build();

        final UserEntity userEntity = userMapper.mapDomainToEntity(user);
        assertThat("Mapping id is failed", userEntity.getId(), is(ID));
        assertThat("Mapping username is failed", userEntity.getUsername(), is(USERNAME));
        assertThat("Mapping email is failed", userEntity.getEmail(), is(EMAIL));
        assertThat("Mapping password is failed", userEntity.getPassword(), is(PASSWORD));
        assertThat("Mapping roles is failed", userEntity.getRole(), is(ROLE));
    }
}