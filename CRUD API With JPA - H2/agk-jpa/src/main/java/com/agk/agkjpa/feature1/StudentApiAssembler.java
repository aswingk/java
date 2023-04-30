package com.agk.agkjpa.feature1;

import com.agk.agkjpa.models.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentApiAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

    @NotNull
    @Override
    public EntityModel<Student> toModel(@NotNull Student entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(StudentApiController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(StudentApiController.class).all()).withRel("students"));
    }

    @NotNull
    @Override
    public CollectionModel<EntityModel<Student>> toCollectionModel(@NotNull Iterable<? extends Student> entities) {
        List<EntityModel<Student>> employees = ((List<Student>)entities).stream() //
                .map(this::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(StudentApiController.class).all()).withSelfRel());
    }
}
