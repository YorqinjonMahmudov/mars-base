package uz.me.marsbase.model.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.model.dao.BlockDao;
import uz.me.marsbase.model.dao.Dao;
import uz.me.marsbase.model.entity.Block;
import uz.me.marsbase.exception.MyException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockDAOImpl implements BlockDao {
    private static final String INSERT = "INSERT INTO block(name, area, location) VALUES (?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, name,  area, location FROM block WHERE id=?;";
    private static final String FIND_ALL = "SELECT id, name,  area, location  FROM block;";
    private static final String FIND_BY_NAME = "SELECT id, name,  area, location  FROM block WHERE name = ?;";
    private static final String UPDATE = "UPDATE block SET name = ?,  area =?, location = ? FROM block WHERE id = ?;";

    private static BlockDAOImpl teamDaoImpl;

    private BlockDAOImpl() {
    }

    public static BlockDAOImpl getInstance() {
        if (teamDaoImpl == null)
            teamDaoImpl = new BlockDAOImpl();
        return teamDaoImpl;
    }

    @Override
    public boolean insert(Block block) {
        if (findByName(block.getName()).isPresent())
            throw new MyException("block already exists");

        return executeUpdate(INSERT,
                Dao.STRING + block.getLocation(),
                Dao.DOUBLE + block.getArea(),
                Dao.STRING + block.getName());
    }

    /**
     * users can't delete blocks
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Optional<Block> findById(int id) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(FIND_BY_ID)) {
            ResultSet rs = putArgs(ps, Dao.INTEGER + id).executeQuery();
            Block block;
            if (rs.next() && (block = mapRsToBlock(rs)).getId() != null)
                return Optional.of(block);
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<Block> findAll() {

        try {
            ResultSet resultSet = MyConnectionPool.getInstance().getConnection().createStatement().executeQuery(FIND_ALL);
            List<Block> list = new ArrayList<>();

            while (resultSet.next())
                list.add(mapRsToBlock(resultSet));

            return list;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }

    }


    @Override
    public boolean update(int id, Block block) {
        Optional<Block> optionalBlock = findById(id);
        if (!optionalBlock.isPresent())
            throw new MyException("block not found");

        if (findByName(block.getName()).isPresent())
            throw new MyException("this name is already available");

        return executeUpdate(UPDATE,
                Dao.STRING + block.getName(),
                Dao.STRING + block.getLocation(),
                Dao.DOUBLE + block.getArea(),
                Dao.INTEGER + id);
    }

    @Override
    public Optional<Block> findByName(String name) {

        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(FIND_BY_NAME)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.STRING + name);

            Block block = null;
            if (resultSet.next())
                block = mapRsToBlock(resultSet);
            if (block == null || block.getId() == null)
                return Optional.empty();
            return Optional.of(block);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private ResultSet executePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args).executeQuery();
    }

    private boolean executeUpdate(String ps, String... args) throws MyException {
        try (PreparedStatement pStatement = MyConnectionPool.getInstance().getConnection().prepareStatement(ps)) {
            return putArgs(pStatement, args).executeUpdate() > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    private Block mapRsToBlock(ResultSet rs) throws SQLException {
        Block block = new Block();
        block.setId(rs.getInt("id"));
        block.setArea(rs.getDouble("area"));
        block.setName(rs.getString("name"));
        block.setLocation(rs.getString("location"));
        return block;
    }

}
