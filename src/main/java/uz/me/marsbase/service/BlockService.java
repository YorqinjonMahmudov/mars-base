package uz.me.marsbase.service;

import uz.me.marsbase.model.dto.BlockDTO;

import java.util.List;
import java.util.Optional;

public interface BlockService {

    Optional<BlockDTO> findByName(String name);

    Optional<BlockDTO> findById(Integer id);

    List<BlockDTO> getBlocks();
}
