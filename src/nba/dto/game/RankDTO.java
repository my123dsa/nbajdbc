package nba.dto.game;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankDTO {
    private int wins;
    private String name;
}
