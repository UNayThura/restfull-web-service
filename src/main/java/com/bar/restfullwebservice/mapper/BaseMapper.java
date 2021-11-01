package com.bar.restfullwebservice.mapper;

import java.util.List;

/**
 * Created by Htay Hlaing Aung on 9/22/2021
 */
public interface BaseMapper<DTO, Entity> {

    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
    List<DTO> toDTOs(List<Entity> entities);
    List<Entity> toEntities(List<DTO> dtoList);
}
