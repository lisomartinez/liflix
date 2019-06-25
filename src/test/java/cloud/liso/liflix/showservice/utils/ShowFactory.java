package cloud.liso.liflix.showservice.utils;

import cloud.liso.liflix.model.show.*;
import cloud.liso.liflix.services.imdb.HttpImdbService;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShowFactory {


    public static int getShowId() {
        return 1;
    }

    public static int getSeasonId() {
        return 3;
    }


    public static Show getShowBase() {
        return Show.builder()
                .id(1)
                .name("Under the Dome")
                .tvMaze("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .imdb("https://www.imdb.com/title/tt1553656")
                .image("http://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg")
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.")
                .build();
    }

    public static Show getShowWithGenreAndSchedule(LocalDateTime lastUpdate, String status) {
        Show show = getShowBase();
        show.setSchedule(getScheduleWithId());
//        show.setGenres(getGenresWithId());
        show.setGenres(new ArrayList<>());
        List<Genre> genresWithId = getGenresWithId();
        for (Genre genre : genresWithId) {
            show.addGenre(genre);
        }
        show.setLastUpdate(lastUpdate);
        show.setStatus(status);
        return show;
    }

    private static List<Genre> getGenresWithId() {
        Genre drama = new Genre(1, "Drama", new ArrayList<>());
        Genre science_fiction = new Genre(2, "Science-Fiction", new ArrayList<>());
        Genre thriller = new Genre(3, "Thriller", new ArrayList<>());
        return Arrays.asList(drama, science_fiction, thriller);
    }

    private static Schedule getScheduleWithId() {
        return new Schedule(1, new ArrayList<>(Arrays.asList(DayOfWeek.of(DayOfWeek.THURSDAY))), LocalTime.of(22, 0));
    }

    public static Show getShowComplete(LocalDateTime lastUpdate) throws IOException {
        Show show = getShowBase();
        show.setSchedule(getSchedule());
        show.setGenres(getGenres());
        show.setLastUpdate(lastUpdate);
        show.setStatus("airing");

        List<Season> seasons = getSeasons(show);
//        set seasons episodes
//        seasons.get(2).setEpisodes(getEpisodesFromSeason(3));
        show.setSeasons(seasons);

        return show;
    }


    public static Show getShowWithOutSeasons(LocalDateTime lastUpdate, String status) throws IOException {
        Show show = getShowBase();
        show.setSchedule(getSchedule());
        show.setGenres(getGenres());
        show.setLastUpdate(lastUpdate);
        show.setStatus(status);
        List<Season> seasons = new ArrayList<>();
        show.setSeasons(seasons);
        return show;
    }

    public static Schedule getSchedule() {
        return new Schedule(1, new ArrayList<>(Arrays.asList(DayOfWeek.of(DayOfWeek.THURSDAY))), LocalTime.of(22, 0));
    }

    public static List<Genre> getGenres() {
        Genre drama = new Genre(1, "Drama", new ArrayList<>());
        Genre science_fiction = new Genre(2, "Science-Fiction", new ArrayList<>());
        Genre thriller = new Genre(3, "Thriller", new ArrayList<>());
        return Arrays.asList(drama, science_fiction, thriller);
    }

    public static List<Season> getSeasons(Show show) {
        Season seasonOne = Season.builder()
                .id(1)
                .tvmazeId(1)
                .show(show)
                .number(1)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2013, 6, 24))
                .endDate(LocalDate.of(2013, 9, 16))
                .image("http://static.tvmaze.com/uploads/images/original_untouched/24/60941.jpg")
                .tvMaze("http://www.tvmaze.com/seasons/1/under-the-dome-season-1")
                .summary("N/A")
                .build();

        Season seasonTwo = Season.builder()
                .id(2)
                .tvmazeId(2)
                .show(show)
                .number(2)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2014, 6, 30))
                .endDate(LocalDate.of(2014, 9, 22))
                .image("http://static.tvmaze.com/uploads/images/original_untouched/24/60942.jpg")
                .tvMaze("http://www.tvmaze.com/seasons/2/under-the-dome-season-2")
                .summary("N/A")
                .build();

        Season seasonThree = Season.builder()
                .id(3)
                .tvmazeId(6233)
                .show(show)
                .number(3)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2015, 6, 25))
                .endDate(LocalDate.of(2015, 9, 10))
                .image("http://static.tvmaze.com/uploads/images/original_untouched/182/457332.jpg")
                .tvMaze("http://www.tvmaze.com/seasons/6233/under-the-dome-season-3")
                .summary("N/A")
                .build();

        List<Season> seasons = new ArrayList<>();
        seasons.add(seasonOne);
        seasons.add(seasonTwo);
        seasons.add(seasonThree);
        return seasons;
    }

    public static Season getLastSeasonWithEpisodes(Show show) {
        return Season.builder()
                .id(6233)
                .show(show)
                .number(3)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2015, 6, 25))
                .endDate(LocalDate.of(2015, 9, 10))
                .image("http://static.tvmaze.com/uploads/images/original_untouched/182/457332.jpg")
                .tvMaze("http://www.tvmaze.com/seasons/6233/under-the-dome-season-3")
                .summary("N/A")
                .episodes(IntStream.rangeClosed(1, 10).mapToObj(i -> new Episode()).collect(Collectors.toList()))
                .build();
    }

    public static Season createLastSeasonWithoutEpisodes(Show show) throws IOException {
        return Season.builder()
                .id(6233)
                .show(show)
                .number(3)
                .episodeOrder(13)
                .premiereDate(LocalDate.of(2015, 6, 25))
                .endDate(LocalDate.of(2015, 9, 10))
                .image("http://static.tvmaze.com/uploads/images/original_untouched/182/457332.jpg")
                .tvMaze("http://www.tvmaze.com/seasons/6233/under-the-dome-season-3")
                .summary("N/A")
                .build();
    }
//
//    public static List<Episode> getEpisodesFromSeason(int id) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        File episodes = null;
//        switch(id) {
//            case 1:
//                episodes = JsonTestFiles.getSeason1Episodes();
//                break;
//            case 2:
//                episodes = JsonTestFiles.getSeason2Episodes();
//                break;
//            case 3:
//                episodes = JsonTestFiles.getSeason6233Episodes();
//                break;
//
//        }
//        List<TvMazeEpisodeDto> episodeDtoList = objectMapper.readValue(episodes, new TypeReference<List<TvMazeEpisodeDto>>() {
//        });
//        ModelMapper modelMapper = new ModelMapper();
//        return episodeDtoList.stream()
//                .map(ep -> modelMapper.map(ep, Episode.class))
//                .collect(Collectors.toList());
//    }
public static Show saveShow(TestEntityManager em) throws IOException {
    Show showComplete = ShowFactory.getShowComplete(LocalDateTime.now().withSecond(0).withNano(0));

    DayOfWeek dayOfWeek = em.find(DayOfWeek.class, 4);

    showComplete.getSchedule().setId(0);
    showComplete.getSchedule().setDays(new ArrayList<>(Arrays.asList(dayOfWeek)));
    Schedule persist = em.persist(showComplete.getSchedule());
    showComplete.setSchedule(persist);

    List<Genre> genres = showComplete.getGenres();
    List<Genre> saved = genres.stream().peek(g -> g.setId(0)).map(em::persist).collect(Collectors.toList());
    showComplete.setGenres(saved);

    return em.persist(showComplete);
}
    public static String getImdb() {
        return HttpImdbService.IMDB_BASE_URL;
    }

    public static String getImdbVideo() {
        return "vi3897206297";
    }

    public static String getImdbVideoFullURL() {
        return HttpImdbService.IMDB_BASE_URL + "vi3897206297";
    }
}
