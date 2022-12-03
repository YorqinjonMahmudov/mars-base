package uz.me.marsbase.service;

import uz.me.marsbase.mappers.BlockMapper;
import uz.me.marsbase.model.dao.BlockDao;
import uz.me.marsbase.model.dao.imp.BlockDAOImpl;
import uz.me.marsbase.model.dto.BlockDTO;
import uz.me.marsbase.model.entity.Block;

import java.util.List;
import java.util.Optional;

public class BlockServiceImpl implements BlockService {
    private static final BlockDao blockDao = BlockDAOImpl.getInstance();
    private static final BlockMapper blockMapper = new BlockMapper();

    @Override
    public Optional<BlockDTO> findByName(String name) {
        Optional<Block> optionalBlock = blockDao.findByName(name);
        return optionalBlock.map(blockMapper::toDto);

    }

    @Override
    public Optional<BlockDTO> findById(Integer id) {
        Optional<Block> optionalBlock = blockDao.findById(id);
        return optionalBlock.map(blockMapper::toDto);
    }

    @Override
    public List<BlockDTO> getBlocks() {
        return blockMapper.toDto(blockDao.findAll());
    }
}
