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
    @Id
    @Column(name = "update_id")
    private int id;

    @Column(name = "last_update")
    LocalDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "show_id", foreignKey = @ForeignKey(name = "fk_updates_show"))
    private Show show;
}
