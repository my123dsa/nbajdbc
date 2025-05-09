package nba.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
abstract class Person implements Serializable {
    private String name;
    private LocalDate birth;
    private Long salary;

    public Person(String name,LocalDate birth, Long salary) {
        this.name = name;
        this.birth = birth;
        this.salary = salary;
    }
}
