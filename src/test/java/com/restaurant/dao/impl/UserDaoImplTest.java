package com.restaurant.dao.impl;

import com.restaurant.dao.UserDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.Role;
import com.restaurant.entity.UserEntity;
import com.restaurant.exception.DataBaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private UserEntity userEntity;
    private UserEntity userEntity2;
    private UserEntity testUserEntity;
    private UserDao userDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        try {
            manager = new HikariCPManager(DB_PROP_PATH);
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            String schemaQuery = new String(Files.readAllBytes(Paths.get(SCHEMA_PATH)));
            statement.execute(schemaQuery);
            String dataQuery = new String(Files.readAllBytes(Paths.get(DATA_PATH)));
            statement.execute(dataQuery);
            statement.close();
            connection.close();
            userDao = new UserDaoImpl(manager);
            setEntities();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        manager.shutdown();
    }

    @Test
    public void findByEmailShouldReturnOptionalUserEntity() {
        assertTrue(userDao.findByEmail(userEntity.getEmail()).isPresent());
    }

    @Test
    public void findByEmailShouldReturnOptionalEmpty() {
        assertFalse(userDao.findByEmail(testUserEntity.getEmail()).isPresent());
    }

    @Test
    public void saveUserShouldReturnTrue() {
        assertTrue(userDao.save(testUserEntity));
    }

    @Test
    public void saveUserShouldThrowException() {
        exception.expect(DataBaseException.class);
        assertFalse(userDao.save(userEntity));
    }

    @Test
    public void findByIdShouldReturnOptionalUserEntity() {
        assertTrue(userDao.findById(userEntity.getId()).isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmpty() {
        assertFalse(userDao.findById(testUserEntity.getId()).isPresent());
    }

    @Test
    public void updateShouldReturnTrue() {
        assertTrue(userDao.update(userEntity));
    }

    @Test
    public void updateShouldReturnFalse() {
        assertFalse(userDao.update(testUserEntity));
    }

    @Test
    public void updateUserShouldThrowException() {
        exception.expect(DataBaseException.class);
        assertFalse(userDao.update(userEntity2));
    }

    @Test
    public void deleteShouldReturnTrue() {
        assertTrue(userDao.delete(userEntity.getId()));
    }

    @Test
    public void deleteShouldReturnFalse() {
        assertFalse(userDao.delete(0));
    }

    @Test
    public void deleteUserShouldThrowException() {
        exception.expect(DataBaseException.class);
        assertFalse(userDao.delete(userEntity2.getId()));
    }

    private void setEntities() {
        userEntity = UserEntity.builder()
                .withId(1L)
                .withUsername("Elizabethssss")
                .withEmail("teddy020301@gmail.com")
                .withPassword("202cb962ac59075b964b07152d234b70")
                .withRole(Role.USER)
                .build();
        userEntity2 = UserEntity.builder()
                .withId(2L)
                .withUsername("Dasha")
                .withPassword("4bea249142664d11a289ffdf30225a91")
                .withRole(Role.USER)
                .build();
        testUserEntity = UserEntity.builder()
                .withId(5L)
                .withUsername("Dima")
                .withEmail("dimusia@gmail.com")
                .withPassword("dima")
                .withRole(Role.USER)
                .build();
    }
}