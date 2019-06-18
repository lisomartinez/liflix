package cloud.liso.liflix.repositories.torrent;

import cloud.liso.liflix.model.torrent.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, String> {
}
