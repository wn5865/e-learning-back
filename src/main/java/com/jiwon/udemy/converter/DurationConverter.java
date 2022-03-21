package com.jiwon.udemy.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration attribute) {
        if (attribute == null) return null;
        return attribute.toNanos();
    }

    @Override
    public Duration convertToEntityAttribute(Long duration) {
        if (duration == null) return null;
        return Duration.of(duration, ChronoUnit.NANOS);
    }
}