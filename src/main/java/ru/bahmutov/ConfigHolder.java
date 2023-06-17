package ru.bahmutov;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
@Getter
public class ConfigHolder {
    @Value("${app.num}")
    private String num;
    @Value("${app.str}")
    private String str;
}
