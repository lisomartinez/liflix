package cloud.liso.liflix.services.tvMaze;

import cloud.liso.liflix.dto.ShowUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    private TvMazeClient client;

    private ModelMapper modelMapper;

    public TvMazeServiceImpl(TvMazeClient client) {
        this.client = client;
    }


    @Override
    public ShowUpdateDto updatedShows(int ttl) {
        return null;
    }
}
 