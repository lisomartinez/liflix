DROP table if exists days_of_week;
DROP table if exists genres;
DROP table if exists schedule_days_of_week;
DROP table if exists schedule;
DROP table if exists episode;
drop table if exists season;
drop table if exists show_genre;
drop table if exists shows;
drop table if exists users;
drop table if exists users_roles;
drop table if exists roles;

drop table if exists cards;
drop table if exists codec;
drop table if exists release_type;
drop table if exists resolution;
drop table if exists torrent_list;
drop table if exists torrent;
drop table if exists updates;

create table days_of_week
(
    day_of_week_id int auto_increment
        primary key,
    day            varchar(255) not null,
    constraint day
        unique (day)
);

create table genres
(
    genre_id int auto_increment
        primary key,
    name     varchar(255) not null,
    constraint name
        unique (name)
);

create table schedule
(
    schedule_id  int auto_increment
        primary key,
    time_of_show time null
);

create table schedule_days_of_week
(
    schedule_id     int not null,
    days_of_week_id int not null,
    primary key (schedule_id, days_of_week_id),
    constraint fk_schedule_days_of_week_days_of_week
        foreign key (days_of_week_id) references days_of_week,
    constraint fk_schedule_days_of_week_schedule
        foreign key (schedule_id) references schedule
);

create table shows
(
    show_id           int auto_increment
        primary key,
    tvmaze_id         int unique     not null,
    name              varchar(255)   not null,
    type              varchar(255)   not null,
    status            varchar(255)   not null,
    language          varchar(255)   not null,
    premiered         date           not null,
    runtime           int            not null,
    schedule_id       int            not null,
    image_url         varchar(1000)  not null,
    imdb_url          varchar(1000)  not null,
    official_site_url varchar(1000)  not null,
    tv_maze_url       varchar(1000)  not null,
    summary           varchar(10000) not null,
    last_update       timestamp      not null,
    rating            double         not null,
    constraint fk_show_schedule_id
        foreign key (schedule_id) references schedule
);

create table season
(
    show_id       int            not null,
    tvmaze_id     int unique     not null,
    season_id     int auto_increment
        primary key,
    number        int            not null,
    episode_order int            not null,
    premiere_date date           not null,
    end_date      date           not null,
    summary       varchar(10000) not null,
    image_url     varchar(1000)  not null,
    tvmaze_url    varchar(1000)  not null,
    constraint fk_season_show
        foreign key (show_id) references shows
);

create table episode
(
    episode_id int auto_increment
        primary key,
    tvmaze_id  int unique     not null,
    season_id  int            null,
    number     int            null,
    name       varchar(255),
    airdate    date           null,
    airtime    time           null,
    runtime    int            null,
    summary    varchar(10000) null,
    image_url  varchar(1000)  null,
    tvmaze_url varchar(1000)  null,
    constraint fk_episode_season
        foreign key (season_id) references season
);

create index idxepisode_name
    on episode (name);

create index idxepisode_number
    on episode (number);

create index idxseason
    on season (number);

create table show_genre
(
    shows_show_id   int not null,
    genres_genre_id int not null,
    constraint fk_show_genre_genre
        foreign key (shows_show_id) references shows,
    constraint fk_show_genre_show
        foreign key (genres_genre_id) references genres
);

create index idxshow_name
    on shows (name);

create table users
(
    user_id           int auto_increment
        primary key,
    username          varchar(255) null,
    password          varchar(255) null,
    registration_date timestamp    null,
    active            tinyint(1)   null,
    email             varchar(255) null
);

create table users_roles
(
    id      int auto_increment
        primary key,
    user_id int          not null,
    role    varchar(255) not null,
    constraint fk_users_users_roles
        foreign key (user_id) references users
);


create table cards
(
    id         varchar(255) primary key,
    genre_name varchar(255),
    show_id    INTEGER,
    name       varchar(255),
    image_url  varchar(255),
    rating     double,
    seasons    bigint
);

create table codec
(
    type VARCHAR(255) PRIMARY KEY
);

create table release_type
(
    type VARCHAR(255) PRIMARY KEY
);

create table resolution
(
    type VARCHAR(255) PRIMARY KEY
);


create table torrent_list
(
    torrent_list_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    show_id         INTEGER,
    season_number   INTEGER,
    episode_number  INTEGER,
    last_update     TIMESTAMP
);

create table torrent
(
    torrent_id      INTEGER AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255)  NOT NULL,
    magnet          VARCHAR(2000) NOT NULL,
    resolution_id   VARCHAR(255),
    codec_id        VARCHAR(255),
    release_id      VARCHAR(255),
    size_number     INTEGER,
    size_unit       VARCHAR(255),
    seeders         INTEGER,
    leechers        INTEGER,
    torrent_list_id INTEGER,
    constraint fk_torrent_resolution
        foreign key (resolution_id) references resolution (type),
    constraint fk_torrent_codec
        foreign key (codec_id) references codec (type),
    constraint fk_torrent_release
        foreign key (release_id) references release_type (type),
    constraint fk_torrent_torrent_list
        foreign key (torrent_list_id) references torrent_list (torrent_list_id)
);

create table updates
(
    update_id   INTEGER   not null primary key,
    last_update TIMESTAMP not null,
    show_id     INTEGER,
    FOREIGN KEY (show_id) REFERENCES shows (show_id)
);
