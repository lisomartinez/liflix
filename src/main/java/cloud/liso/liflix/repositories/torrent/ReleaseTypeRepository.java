package cloud.liso.liflix.repositories.torrent;

import cloud.liso.liflix.model.torrent.ReleaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseTypeRepository extends JpaRepository<ReleaseType, String> {
}
