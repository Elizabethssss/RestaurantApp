package com.restaurant.injector;

import com.restaurant.command.Command;
import com.restaurant.command.menu.DishCommand;
import com.restaurant.command.menu.IndexCommand;
import com.restaurant.command.menu.MenuCommand;
import com.restaurant.command.purchase.BasketCommand;
import com.restaurant.command.purchase.BuyingCommand;
import com.restaurant.command.user.LoginCommand;
import com.restaurant.command.user.SignUpCommand;
import com.restaurant.dao.DishDao;
import com.restaurant.dao.IngredientDao;
import com.restaurant.dao.LunchDao;
import com.restaurant.dao.OrderDao;
import com.restaurant.dao.UserDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.dao.impl.DishDaoImpl;
import com.restaurant.dao.impl.IngredientDaoImpl;
import com.restaurant.dao.impl.LunchDaoImpl;
import com.restaurant.dao.impl.OrderDaoImpl;
import com.restaurant.dao.impl.UserDaoImpl;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Ingredient;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.IngredientEntity;
import com.restaurant.entity.LunchEntity;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;
import com.restaurant.service.DishService;
import com.restaurant.service.IngredientService;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import com.restaurant.service.impl.DishServiceImpl;
import com.restaurant.service.impl.IngredientServiceImpl;
import com.restaurant.service.impl.OrderServiceImpl;
import com.restaurant.service.impl.UserServiceImpl;
import com.restaurant.service.mapper.DishMapper;
import com.restaurant.service.mapper.IngredientMapper;
import com.restaurant.service.mapper.LunchMapper;
import com.restaurant.service.mapper.Mapper;
import com.restaurant.service.mapper.OrderMapper;
import com.restaurant.service.mapper.UserMapper;
import com.restaurant.service.util.PasswordEncryptor;
import com.restaurant.service.validator.CreditCardValidator;
import com.restaurant.service.validator.UserValidator;
import com.restaurant.service.validator.impl.CreditCardValidatorImpl;
import com.restaurant.service.validator.impl.UserValidatorImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationInjector {
    private static final HikariCPManager DB_CONNECTOR = new HikariCPManager("properties/db");
    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);
    private static final OrderDao ORDER_DAO = new OrderDaoImpl(DB_CONNECTOR);
    private static final LunchDao LUNCH_DAO = new LunchDaoImpl(DB_CONNECTOR);
    private static final IngredientDao INGREDIENT_DAO = new IngredientDaoImpl(DB_CONNECTOR);
    private static final DishDao DISH_DAO = new DishDaoImpl(DB_CONNECTOR);

    private static final UserValidator USER_VALIDATOR = new UserValidatorImpl();
    private static final CreditCardValidator CREDIT_CARD_VALIDATOR= new CreditCardValidatorImpl();

    private static final Mapper<UserEntity, User> USER_MAPPER = new UserMapper(PASSWORD_ENCRYPTOR);
    private static final Mapper<OrderEntity, Order> ORDER_MAPPER = new OrderMapper();
    private static final Mapper<LunchEntity, Lunch> LUNCH_MAPPER = new LunchMapper();
    private static final Mapper<DishEntity, Dish> DISH_MAPPER = new DishMapper();
    private static final Mapper<IngredientEntity, Ingredient> INGREDIENT_MAPPER = new IngredientMapper();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO, ORDER_MAPPER);
    private static final DishService DISH_SERVICE = new DishServiceImpl(DISH_DAO, DISH_MAPPER);
    private static final IngredientService INGREDIENT_SERVICE = new IngredientServiceImpl(INGREDIENT_DAO, INGREDIENT_MAPPER);


    private static final Command SIGN_UP_COMMAND = new SignUpCommand(USER_SERVICE, ORDER_SERVICE);
    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE, ORDER_SERVICE);
    private static final Command INDEX_COMMAND = new IndexCommand(ORDER_SERVICE, DISH_SERVICE);
    private static final Command MENU_COMMAND = new MenuCommand(DISH_SERVICE);
    private static final Command DISH_COMMAND = new DishCommand(DISH_SERVICE, INGREDIENT_SERVICE);
    private static final Command BUYING_COMMAND = new BuyingCommand(ORDER_SERVICE);
    private static final Command BASKET_COMMAND = new BasketCommand(ORDER_SERVICE, DISH_SERVICE);

    private static final Map<String, Command> COMMANDS = initCommands();

    private static Map<String, Command> initCommands() {
        Map<String, Command> authorizationCommands = new HashMap<>();
        authorizationCommands.put("/signUp", SIGN_UP_COMMAND);
        authorizationCommands.put("/login", LOGIN_COMMAND);
//        authorizationCommands.put("/logout", LOGOUT_COMMAND);
        authorizationCommands.put("/index", INDEX_COMMAND);
        authorizationCommands.put("/menu", MENU_COMMAND);
        authorizationCommands.put("/dish", DISH_COMMAND);
        authorizationCommands.put("/buy", BUYING_COMMAND);
        authorizationCommands.put("/basket", BASKET_COMMAND);
//        authorizationCommands.put("/pay", PAYING_COMMAND);
//        authorizationCommands.put("/profile", PROFILE_COMMAND);

        return Collections.unmodifiableMap(authorizationCommands);
    }

    private static ApplicationInjector applicationInjector;

    private ApplicationInjector() { }

    public static ApplicationInjector getInstance() {
        if(applicationInjector == null) {
            synchronized (ApplicationInjector.class) {
                if (applicationInjector == null) {
                    applicationInjector = new ApplicationInjector();
                }
            }
        }
        return applicationInjector;
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

    public static UserValidator getUserValidator() {
        return USER_VALIDATOR;
    }
    public static CreditCardValidator getCreditCardValidator() {
        return CREDIT_CARD_VALIDATOR;
    }
}
