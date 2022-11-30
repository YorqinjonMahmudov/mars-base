package uz.me.marsbase.component;

import lombok.SneakyThrows;
import uz.me.marsbase.ConnectionSource;
import uz.me.marsbase.dao.imp.BlockDAOImpl;
import uz.me.marsbase.dao.imp.UserDAOImpl;
import uz.me.marsbase.entity.Block;
import uz.me.marsbase.entity.User;
import uz.me.marsbase.entity.enums.Role;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataLoader {

    private static final boolean create = false;

    private static final boolean loadData = false;

    public static void initialize() {
        if (create)
            create();
        if (loadData)
            loadData();

    }

    @SneakyThrows
    private static void loadData() {

        Block block = Block.builder()
                .name("Admin`s office")
                .location("Wall Street 39A, UzaMars, Mars")
                .area(123456789)
                .build();

        BlockDAOImpl.getInstance().insert(block);

        User admin = User.builder()
                .blockId(1)
                .firstName("Admin")
                .lastName("Tom")
                .password("root123")
                .email("admin@gmail.com")
                .role(Role.ADMIN)
                .build();
        UserDAOImpl.getInstance().insert(admin);

        User teamLid = User.builder()
                .blockId(1)
                .firstName("John")
                .lastName("Hopkins")
                .password("john123")
                .email("john@gmail.com")
                .role(Role.TEAM_LEADER)
                .build();

        UserDAOImpl.getInstance().insert(teamLid);

    }

    private static void create() {
        ConnectionSource connectionSource = new ConnectionSource();
        Connection connection = connectionSource.createConnection();

        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(new File("D:/Documents/java/mars-base/src/main/resources/init-ddl.sql").getAbsolutePath());
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;

            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);

            connection.createStatement().execute(stringBuilder.toString());

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
