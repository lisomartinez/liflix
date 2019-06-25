package cloud.liso.liflix.model.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "updates")
public class ShowUpdate {
    @Column(name = "last_update")
    LocalDateTime lastUpdate;
    @Id
    @Column(name = "update_id")
    private int id;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "show_id"))
    private Show show;
}
