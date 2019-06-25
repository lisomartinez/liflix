package cloud.liso.liflix.services.torrent;

import cloud.liso.liflix.model.torrent.Codec;
import cloud.liso.liflix.model.torrent.ReleaseType;
import cloud.liso.liflix.model.torrent.Resolution;
import cloud.liso.liflix.model.torrent.Torrent;
import cloud.liso.liflix.repositories.torrent.CodecRepository;
import cloud.liso.liflix.repositories.torrent.ReleaseTypeRepository;
import cloud.liso.liflix.repositories.torrent.ResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TorrentAssociatedEntitiesService {

    private CodecRepository codecRepository;

    private ResolutionRepository resolutionRepository;

    private ReleaseTypeRepository releaseTypeRepository;

    @Autowired
    public TorrentAssociatedEntitiesService(CodecRepository codecRepository, ResolutionRepository resolutionRepository, ReleaseTypeRepository releaseTypeRepository) {
        this.codecRepository = codecRepository;
        this.resolutionRepository = resolutionRepository;
        this.releaseTypeRepository = releaseTypeRepository;
    }

    public List<Torrent> getOrPersistTorrentAssociatedEntitiesOfAll(List<Torrent> torrents) {
        return torrents.stream().map(this::getOrSaveComponents).collect(Collectors.toList());
    }

    public Torrent getOrSaveComponents(Torrent torrent) {
        Resolution resolution = torrent.getResolution();
        torrent.setResolution(resolutionRepository.findById(resolution.getType())
                .orElse(resolutionRepository.save(resolution)));
        Codec codec = torrent.getCodec();
        torrent.setCodec(codecRepository.findById(codec.getType())
                .orElse(codecRepository.save(codec)));
        ReleaseType releaseType = torrent.getReleaseType();
        torrent.setReleaseType(releaseTypeRepository.findById(releaseType.getType())
                .orElse(releaseTypeRepository.save(releaseType)));

        return torrent;
    }
}
