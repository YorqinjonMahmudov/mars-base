package uz.me.marsbase.dao;

import uz.me.marsbase.entity.Block;
import uz.me.marsbase.exception.MyException;

import java.util.Optional;

public interface BlockDao extends Dao<Block> {
    /**
     * Find Team by name.
     *
     * @param name Category name.
     * @return Category obj if exists with this id, otherwise Optional.empty().
     * @throws MyException if an error occurs.
     */
    Optional<Block> findByName(String name);

}
