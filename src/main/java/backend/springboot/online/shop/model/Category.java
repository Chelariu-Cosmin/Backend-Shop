package backend.springboot.online.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    HOODIE ("hoodie"), BLOUSE ("blouse"), PANTS ("pants");

    private final String value;

}