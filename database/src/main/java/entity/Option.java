package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Option {

    private Integer id;
    private String name;
    private Integer price;
}