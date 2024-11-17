package org.example.library.mapper;

import org.example.library.dto.DataDto;
import org.example.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(source = "bookName", target = "title")
    @Mapping(source = "bookAuthor", target = "author")
    @Mapping(target = "date", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "user", ignore = true)
    Book toBook(DataDto dataDto);
}
