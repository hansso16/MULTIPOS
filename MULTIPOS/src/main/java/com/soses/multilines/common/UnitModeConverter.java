package com.soses.multilines.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class UnitModeConverter implements AttributeConverter<UnitModeEnum, String> {

	
	@Override
    public String convertToDatabaseColumn(UnitModeEnum attribute) {
        if (attribute == null) return null;
        return String.valueOf(attribute.getCode()); // "C" or "P"
    }

    @Override
    public UnitModeEnum convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return UnitModeEnum.fromCode(dbData.charAt(0));
    }
}
