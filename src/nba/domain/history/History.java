package nba.domain.history;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class History {
    private Integer id;
    private String tableName;
    private Integer rowId;
    private String operation;  //사실 Enum으로 빼는게
    private LocalDateTime createdAt;
    private String changeContent;

    @Builder
    public History(Integer id, String tableName, Integer rowId, String operation, LocalDateTime createdAt, String changeContent) {
        this.id = id;
        this.tableName = tableName;
        this.rowId = rowId;
        this.operation = operation;
        this.createdAt = createdAt;
        this.changeContent = changeContent;
    }
}
