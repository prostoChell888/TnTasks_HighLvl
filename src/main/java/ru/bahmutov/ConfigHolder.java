package ru.bahmutov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Component
@Getter
public class ConfigHolder {
    @Value("${app.num}")
    private Integer num;
    @Value("${app.str}")
    private String str;

}
