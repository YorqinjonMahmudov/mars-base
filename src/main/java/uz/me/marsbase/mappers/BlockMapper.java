package uz.me.marsbase.mappers;


import uz.me.marsbase.model.dto.BlockDTO;
import uz.me.marsbase.model.entity.Block;

import java.util.List;

public class BlockMapper implements BaseMapper<Block, BlockDTO> {

    @Override
    public BlockDTO toDto(Block block) {
        if (block == null) return null;
        return new BlockDTO(block.getId(), block.getName(),
                block.getArea(), block.getLocation());
    }

    @Override
    public Block fromDto(BlockDTO blockDTO) {
        return new Block(blockDTO.getId(), blockDTO.getName(), blockDTO.getArea(), blockDTO.getLocation());
    }

    @Override
    public List<Block> fromDto(List<BlockDTO> dto) {
        return dto
                .stream()
                .map(this::fromDto)
                .toList();
    }

    @Override
    public List<BlockDTO> toDto(List<Block> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

}
