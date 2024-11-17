package org.example.library.mapper;

import org.example.library.dto.DataDto;
import org.example.library.entity.LibraryUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "userFullName", target = "fullName")
    @Mapping(source = "userActive", target = "active")
    LibraryUser toLibraryUser(DataDto dataDto);
}
