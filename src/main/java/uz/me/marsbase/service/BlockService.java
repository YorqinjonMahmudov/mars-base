package uz.me.marsbase.service;

import uz.me.marsbase.payload.BlockDTO;

import java.util.List;
import java.util.Optional;

public interface BlockService {

    Optional<BlockDTO> findByName(String name);

    Optional<BlockDTO> findById(Integer id);

    List<BlockDTO> getBlocks();
}
