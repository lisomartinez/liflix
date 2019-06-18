package cloud.liso.liflix.repositories.torrent;

import cloud.liso.liflix.model.torrent.Torrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorrentRepository extends JpaRepository<Torrent, Integer> {
}
