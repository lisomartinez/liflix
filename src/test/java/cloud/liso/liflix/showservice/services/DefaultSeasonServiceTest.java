package cloud.liso.liflix.showservice.services;

import cloud.liso.liflix.exceptions.SeasonNotFoundException;
import cloud.liso.liflix.model.show.Season;
import cloud.liso.liflix.repositories.SeasonRepository;
import cloud.liso.liflix.services.api.show.SeasonService;
import cloud.liso.liflix.services.impl.show.DefaultSeasonService;
import cloud.liso.liflix.showservice.utils.ShowFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultSeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    private SeasonService seasonService;

    @BeforeEach
    void setUp() {
        seasonService = new DefaultSeasonService(seasonRepository);
    }

    @Test
    void getAllSeasons() {
        when(seasonRepository.findAllByShow(anyInt())).thenReturn(Arrays.asList(new Season(), new Season(), new Season()));
        List<Season> allSeasons = seasonService.getAllSeasons(1);
        assertThat(allSeasons.size()).isEqualTo(3);
    }

    @Test
    void getSeasonByShowId_valiShowIdAndValidSeasonNumber_ReturnsSeason() {
        Season seasonWithTenEpisodes = ShowFactory.getLastSeasonWithEpisodes(null);
        when(seasonRepository.getSeasonsByShowId(anyInt(), anyInt())).thenReturn(Optional.of(seasonWithTenEpisodes));
        Season result = seasonService.getSeasonByNumber(1, 3);
        assertThat(result.getEpisodes().size()).isEqualTo(10);
    }

    @Test
    void getSeasonByShowId_invalidShowId_TrowsSeasonNotFoundException() {
        when(seasonRepository.getSeasonsByShowId(anyInt(), anyInt())).thenReturn(Optional.empty());

        assertThrows(SeasonNotFoundException.class, () -> seasonService.getSeasonByNumber(Integer.MAX_VALUE, 3));
    }

    @Test
    void getSeasonByShowId_validShowIdInvalidSeasonId_TrowsSeasonNotFoundException() {
        when(seasonRepository.getSeasonsByShowId(anyInt(), anyInt())).thenReturn(Optional.empty());

        assertThrows(SeasonNotFoundException.class, () -> seasonService.getSeasonByNumber(1, Integer.MAX_VALUE));
    }
}