package cloud.liso.liflix.repositories.torrent;

import cloud.liso.liflix.model.torrent.Codec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodecRepository extends JpaRepository<Codec, String> {
}
