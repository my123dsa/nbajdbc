package nba.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerState {
    private Integer teamId;
    private String userType;
}
