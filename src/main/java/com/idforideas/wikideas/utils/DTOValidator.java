package com.idforideas.wikideas.utils;

import com.idforideas.wikideas.exception.WikiException;

import jakarta.validation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.idforideas.wikideas.exception.MessageErrorEnum.MANDATORY_PARAMETERES_MISSING;
import static com.idforideas.wikideas.exception.MessageErrorEnum.WRONG_PARAMETERS;

public class DTOValidator {

    private static final String NOT_NULL = "constraints.NotNull";


    public static <T> void validate(final List<T> dtoList, final Class... groups) {
        for (final T dto : dtoList) {
            validate(dto, groups);
        }
    }

    public static <T> void validate(final T dto, final Class... groups) {
        if (dto == null) {
            throw new WikiException(MANDATORY_PARAMETERES_MISSING.getMessage());
        }
        Set<ConstraintViolation<T>> violationsSet = (Set<ConstraintViolation<T>>)Validation.buildDefaultValidatorFactory().getValidator()
                .validate(dto, groups);
        if (hasViolations(violationsSet)) {
            final List<String> attributes = validateOptionalParameters(dto);
            for (final ConstraintViolation<T> violation : violationsSet) {
                final String propertyViolated = violation.getPropertyPath().toString();
                final String message = String.valueOf(propertyViolated) + " " + violation.getMessage();
                Boolean isOptional = false;
                for (final String value : attributes) {
                    if (propertyViolated.replaceAll("\\.", "").contains(value)) {
                        isOptional = true;
                        break;
                    }
                    isOptional = false;
                }
                if (!isOptional) {
                    throwBusinessException(violation, message);
                }
            }
        }
    }

    private static <T> List<String> validateOptionalParameters(final T dto) {
        final Class<?> objectToValidate = dto.getClass();
        final Field[] attributes = objectToValidate.getDeclaredFields();
        final List<String> attributeList = new ArrayList<>();
        Field[] array;
        for (int length = (array = attributes).length, j = 0; j < length; ++j) {
            final Field value = array[j];
            final Annotation annotation = value.getAnnotation(OptionalValidate.class);
            if (annotation != null && annotation instanceof OptionalValidate) {
                final OptionalValidate optional = (OptionalValidate)annotation;
                final Class[] groups = optional.groups();
                for (int i = 0; i < groups.length; ++i) {
                    if (groups[i] == IDTOInOptionalAttributes.class || groups[i] == IDTOOutOptionalAttributes.class) {
                        attributeList.add(value.getName());
                    }
                }
            }
        }
        return attributeList;
    }

    private static final <T> boolean hasViolations(final Set<ConstraintViolation<T>> violationsSet) {
        return violationsSet != null && !violationsSet.isEmpty() && violationsSet.iterator().next() != null;
    }

    private static final <T> void throwBusinessException(final ConstraintViolation<T> violation, final String message) {
        final String messageViolation = violation.getConstraintDescriptor().getMessageTemplate();
        if (messageViolation.contains("constraints.NotNull")) {
            throw new WikiException(MANDATORY_PARAMETERES_MISSING.getMessage());
        }
        throw new WikiException(WRONG_PARAMETERS.getMessage());
    }
}
