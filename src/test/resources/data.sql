INSERT INTO resolution(type) VALUES ( '1080p' );
INSERT INTO resolution(type) VALUES ( '720p' );
INSERT INTO resolution(type) VALUES ( '480p' );
INSERT INTO resolution(type) VALUES ( 'SD' );

INSERT INTO days_of_week (day_of_week_id, day)
VALUES (5, 'Friday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (1, 'Monday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (6, 'Saturday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (7, 'Sunday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (4, 'Thursday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (2, 'Tuesday');
INSERT INTO days_of_week(day_of_week_id, day)
VALUES (3, 'Wednesday');

INSERT INTO genres (genre_id, name)
VALUES (4, 'Action');
INSERT INTO genres (genre_id, name)
VALUES (8, 'Adventure');
INSERT INTO genres (genre_id, name)
VALUES (15, 'Anime');
INSERT INTO genres (genre_id, name)
VALUES (16, 'Comedy');
INSERT INTO genres (genre_id, name)
VALUES (5, 'Crime');
INSERT INTO genres (genre_id, name)
VALUES (1, 'Drama');
INSERT INTO genres (genre_id, name)
VALUES (9, 'Espionage');
INSERT INTO genres (genre_id, name)
VALUES (14, 'Family');
INSERT INTO genres (genre_id, name)
VALUES (13, 'Fantasy');
INSERT INTO genres (genre_id, name)
VALUES (17, 'History');
INSERT INTO genres (genre_id, name)
VALUES (6, 'Horror');
INSERT INTO genres (genre_id, name)
VALUES (19, 'Legal');
INSERT INTO genres (genre_id, name)
VALUES (18, 'Medical');
INSERT INTO genres (genre_id, name)
VALUES (10, 'Music');
INSERT INTO genres (genre_id, name)
VALUES (11, 'Mystery');
INSERT INTO genres (genre_id, name)
VALUES (7, 'Romance');
INSERT INTO genres (genre_id, name)
VALUES (2, 'Science-Fiction');
INSERT INTO genres (genre_id, name)
VALUES (22, 'Sports');
INSERT INTO genres (genre_id, name)
VALUES (12, 'Supernatural');
INSERT INTO genres (genre_id, name)
VALUES (3, 'Thriller');
INSERT INTO genres (genre_id, name)
VALUES (21, 'War');
INSERT INTO genres (genre_id, name)
VALUES (20, 'Western');

INSERT INTO schedule (schedule_id, time_of_show)
VALUES (1, '22:00:00');
INSERT INTO shows (show_id, tvmaze_id, name, type, status, language, premiered, runtime, schedule_id, image_url,
                   imdb_url, official_site_url, tv_maze_url, summary, last_update, rating)
VALUES (1, 1, 'Under the Dome', 'Scripted', 'Ended', 'English', '2013-06-24', 60, 1,
        'http://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg', 'https://www.imdb.com/title/tt1553656',
        'http://www.cbs.com/shows/under-the-dome/', 'http://www.tvmaze.com/shows/1/under-the-dome',
        'Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town''s inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.',
        '2019-06-05 11:25:40', 6.6);

INSERT INTO show_genre (shows_show_id, genres_genre_id)
VALUES (1, 1);
INSERT INTO show_genre (shows_show_id, genres_genre_id)
VALUES (1, 2);
INSERT INTO show_genre (shows_show_id, genres_genre_id)
VALUES (1, 3);
INSERT INTO season (show_id, tvmaze_id, season_id, number, episode_order, premiere_date, end_date, summary, image_url,
                    tvmaze_url)
VALUES (1, 1, 1, 1, 13, '2013-06-24', '2013-09-16', 'N/A',
        'http://static.tvmaze.com/uploads/images/medium_portrait/24/60941.jpg',
        'http://www.tvmaze.com/seasons/1/under-the-dome-season-1');
INSERT INTO season (show_id, tvmaze_id, season_id, number, episode_order, premiere_date, end_date, summary, image_url,
                    tvmaze_url)
VALUES (1, 2, 2, 2, 13, '2014-06-30', '2014-09-22', 'N/A',
        'http://static.tvmaze.com/uploads/images/medium_portrait/24/60942.jpg',
        'http://www.tvmaze.com/seasons/2/under-the-dome-season-2');
INSERT INTO season (show_id, tvmaze_id, season_id, number, episode_order, premiere_date, end_date, summary, image_url,
                    tvmaze_url)
VALUES (1, 6233, 3, 3, 13, '2015-06-25', '2015-09-10', 'N/A',
        'http://static.tvmaze.com/uploads/images/medium_portrait/182/457332.jpg',
        'http://www.tvmaze.com/seasons/6233/under-the-dome-season-3');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (1, 1, 1, 1, 'Pilot', '2013-06-24', '22:00:00', 60,
        'When the residents of Chester''s Mill find themselves trapped under a massive transparent dome with no way out, they struggle to survive as resources rapidly dwindle and panic quickly escalates.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4388.jpg',
        'http://www.tvmaze.com/episodes/1/under-the-dome-1x01-pilot');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (2, 2, 1, 2, 'The Fire', '2013-07-01', '22:00:00', 60,
        'While the residents of Chester''s Mill face the uncertainty of life in the dome, panic is heightened when a house goes up in flames and their fire department is outside of the dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4389.jpg',
        'http://www.tvmaze.com/episodes/2/under-the-dome-1x02-the-fire');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (3, 3, 1, 3, 'Manhunt', '2013-07-08', '22:00:00', 60,
        'When a former deputy goes rogue, Big Jim recruits Barbie to join the manhunt to keep the town safe. Meanwhile, Junior is determined to escape the dome by going underground.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4390.jpg',
        'http://www.tvmaze.com/episodes/3/under-the-dome-1x03-manhunt');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (4, 4, 1, 4, 'Outbreak', '2013-07-15', '22:00:00', 60,
        'The people of Chester''s Mill fall into a state of panic as an outbreak of meningitis strikes their community, threatening their already depleted medical supplies. Meanwhile, Julia continues to search for answers into her husband''s disappearance.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4391.jpg',
        'http://www.tvmaze.com/episodes/4/under-the-dome-1x04-outbreak');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (5, 5, 1, 5, 'Blue on Blue', '2013-07-22', '22:00:00', 60,
        'The Chester''s Mill residents receive an unexpected visit from their loved ones on the other side. Meanwhile, the community braces for a threat from outside the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4392.jpg',
        'http://www.tvmaze.com/episodes/5/under-the-dome-1x05-blue-on-blue');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (6, 6, 1, 6, 'The Endless Thirst', '2013-07-29', '22:00:00', 60,
        'When the town begins to run low on water, the residents of Chester''s Mill begin to fight for the remaining resources. Meanwhile, Julia discovers a strange connection that two of the town''s residents have with the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4393.jpg',
        'http://www.tvmaze.com/episodes/6/under-the-dome-1x06-the-endless-thirst');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (7, 7, 1, 7, 'Imperfect Circles', '2013-08-05', '22:00:00', 60,
        'Big Jim takes matters into his own hands when he feels his authority slipping away, and the dome displays its power when a life is taken just as a newborn arrives.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4394.jpg',
        'http://www.tvmaze.com/episodes/7/under-the-dome-1x07-imperfect-circles');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (8, 8, 1, 8, 'Thicker Than Water', '2013-08-12', '22:00:00', 60,
        'Junior stands up to his father and is shattered when he discovers the truth about his mother''s past. Meanwhile, Julia learns firsthand the powers of the "mini dome".',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4395.jpg',
        'http://www.tvmaze.com/episodes/8/under-the-dome-1x08-thicker-than-water');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (9, 9, 1, 9, 'The Fourth Hand', '2013-08-19', '22:00:00', 60,
        'Big Jim and Barbie discover their lives are more intertwined than they knew when a mysterious woman, Maxine, shows up unexpectedly in Chester''s Mill.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4396.jpg',
        'http://www.tvmaze.com/episodes/9/under-the-dome-1x09-the-fourth-hand');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (10, 10, 1, 10, 'Let the Games Begin', '2013-08-26', '22:00:00', 60,
        'Julia uncovers the truth about her husband''s disappearance and unravels some of Chester''s Mill''s darkest secrets. Meanwhile, Maxine shows Barbie how she plans to take control of the town.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4397.jpg',
        'http://www.tvmaze.com/episodes/10/under-the-dome-1x10-let-the-games-begin');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (11, 11, 1, 11, 'Speak of the Devil', '2013-09-02', '22:00:00', 60,
        'Big Jim turns the town against Barbie when the truth about his past is revealed. Meanwhile, Maxine makes it personal when she confronts Barbie''s closest ally.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4398.jpg',
        'http://www.tvmaze.com/episodes/11/under-the-dome-1x11-speak-of-the-devil');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (12, 12, 1, 12, 'Exigent Circumstances', '2013-09-09', '22:00:00', 60,
        'While the manhunt for Barbie continues, Big Jim gets the residents of Chester''s Mill riled up and the town demands justice for all of Barbie''s supposed crimes. Meanwhile, Joe and Norrie must find a new hiding place for the mini dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4399.jpg',
        'http://www.tvmaze.com/episodes/12/under-the-dome-1x12-exigent-circumstances');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (13, 13, 1, 13, 'Curtains', '2013-09-16', '22:00:00', 60,
        'Secrets of the Dome are revealed and Big Jim is determined to put an end to Barbie once and for all. Meanwhile, Junior, Angie, Joe and Norrie discover who the Monarch is after receiving a shocking visit from a familiar face.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/1/4400.jpg',
        'http://www.tvmaze.com/episodes/13/under-the-dome-1x13-curtains');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (14, 27, 2, 0, 'Inside Chester''s Mill', '2014-06-23', '22:00:00', 60, 'N/A', 'N/A',
        'http://www.tvmaze.com/episodes/27/under-the-dome-s02-special-inside-chesters-mill');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (15, 14, 2, 1, 'Heads Will Roll', '2014-06-30', '22:00:00', 60,
        'Barbie''s fate lies in Big Jim''s hands, and the Dome presents a new threat when it becomes magnetized. Meanwhile, Julia seeks out the help of a stranger to save the life of a mysterious girl who may hold clues to origin of the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10446.jpg',
        'http://www.tvmaze.com/episodes/14/under-the-dome-2x01-heads-will-roll');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (16, 15, 2, 2, 'Infestation', '2014-07-07', '22:00:00', 60,
        'Barbie risks his life to help Rebecca save the Chester''s Mill food supply when she discovers an infestation of butterfly eggs on the town''s crops.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10447.jpg',
        'http://www.tvmaze.com/episodes/15/under-the-dome-2x02-infestation');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (17, 16, 2, 3, 'Force Majeure', '2014-07-14', '22:00:00', 60,
        'When tensions in Chester''s Mill continue to rise as resources dwindle, Big Jim holds a census in order to forecast how long the town can continue to exist under the dire conditions. Meanwhile, a rainstorm brings much-needed water until it changes to acid rain, threatening the lives of everyone it touches. Also, Rebecca and Lyle butt heads over the reasons for the dome''s existence.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10448.jpg',
        'http://www.tvmaze.com/episodes/16/under-the-dome-2x03-force-majeure');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (18, 17, 2, 4, 'Revelation', '2014-07-21', '22:00:00', 60,
        'As conditions in Chester''s Mill worsen, Big Jim and Rebecca consider resorting to the extreme measure of population control, which drives a wedge between Barbie and Julia. Meanwhile, clues to Melanie''s past and her connection to the Dome are revealed.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10449.jpg',
        'http://www.tvmaze.com/episodes/17/under-the-dome-2x04-revelation');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (19, 18, 2, 5, 'Reconciliation', '2014-07-28', '22:00:00', 60,
        'Julia takes over as the leader of Chester''s Mill after the town becomes divided in the wake of Big Jim and Rebecca''s plans for population control. Meanwhile, Joe and Norrie help Melanie search for more clues about her identity at the Dome wall.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10450.jpg',
        'http://www.tvmaze.com/episodes/18/under-the-dome-2x05-reconciliation');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (20, 19, 2, 6, 'In the Dark', '2014-08-04', '22:00:00', 60,
        'When Barbie and Sam set out to investigate a mysterious tunnel, a cave-in severs their path back to Chester''s Mill. Meanwhile, Julia and Big Jim face off in a struggle for power as a dust storm rages in the town.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10451.jpg',
        'http://www.tvmaze.com/episodes/19/under-the-dome-2x06-in-the-dark');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (21, 20, 2, 7, 'Going Home', '2014-08-11', '22:00:00', 60,
        'When Barbie descends into the unknown abyss in the mysterious tunnel to look for Sam, he discovers a world that is familiar but filled with unanswered questions.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10452.jpg',
        'http://www.tvmaze.com/episodes/20/under-the-dome-2x07-going-home');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (22, 21, 2, 8, 'Awakening', '2014-08-18', '22:00:00', 60,
        'When Barbie enlists his father''s help to reach out to Julia, he realizes that Don may know more about the Dome than he is letting on. Meanwhile, Big Jim appoints himself sheriff of Chester''s Mill.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10453.jpg',
        'http://www.tvmaze.com/episodes/21/under-the-dome-2x08-awakening');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (23, 22, 2, 9, 'The Red Door', '2014-08-25', '22:00:00', 60,
        'When Barbie is apprehended by a group of mysterious men, he is relentlessly interrogated about his connection to the Dome. Meanwhile, Big Jim makes a deal that could seal the fate of the residents of Chester''s Mill forever.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10454.jpg',
        'http://www.tvmaze.com/episodes/22/under-the-dome-2x09-the-red-door');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (24, 23, 2, 10, 'The Fall', '2014-09-01', '22:00:00', 60,
        'Big Jim finally learns the shocking truth about what really happened to his wife, Pauline, when they are reunited. Meanwhile, climate change poses a new threat to Chester''s Mill.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10455.jpg',
        'http://www.tvmaze.com/episodes/23/under-the-dome-2x10-the-fall');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (25, 24, 2, 11, 'Black Ice', '2014-09-08', '22:00:00', 60,
        'When temperatures begin to plunge, Sam and Rebecca spring into action to try to save the residents of Chester''s Mill. Meanwhile, Barbie risks his own life in order to save Julia after a terrible accident.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10456.jpg',
        'http://www.tvmaze.com/episodes/24/under-the-dome-2x11-black-ice');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (26, 25, 2, 12, 'Turn', '2014-09-15', '22:00:00', 60,
        'When a new threat from the Dome intensifies, the residents of Chester''s Mill find themselves at risk of being crushed to death. Meanwhile, Melanie''s health continues to deteriorate as the fate of the egg remains unknown.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10457.jpg',
        'http://www.tvmaze.com/episodes/25/under-the-dome-2x12-turn');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (27, 26, 2, 13, 'Go Now', '2014-09-22', '22:00:00', 60,
        'Potential exit from the Dome is revealed just as the walls begin closing in on those trapped in Chester''s Mill.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/4/10458.jpg',
        'http://www.tvmaze.com/episodes/26/under-the-dome-2x13-go-now');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (28, 142270, 3, 1, 'Move On', '2015-06-25', '21:00:00', 60,
        'Season 3 begins with Chester''s Mill residents appearing inside and outside the Dome following an evacuation into the tunnels beneath the town. Meanwhile, the Dome begins to reveal its ultimate agenda; and surprising alliances form as new residents emerge.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/12/31233.jpg',
        'http://www.tvmaze.com/episodes/142270/under-the-dome-3x01-move-on');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (29, 151048, 3, 2, 'But I''m Not', '2015-06-25', '22:00:00', 60,
        'Chester''s Mill residents appear inside and outside the Dome following an exit into the tunnels beneath the town. Meanwhile, the Dome begins to reveal its ultimate agenda; and surprising alliances form as new residents emerge.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/12/31234.jpg',
        'http://www.tvmaze.com/episodes/151048/under-the-dome-3x02-but-im-not');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (30, 151645, 3, 3, 'Redux', '2015-07-02', '22:00:00', 60,
        'The residents of Chester''s Mill try to move on with their lives in the aftermath of their mysterious experience in the tunnels beneath town. Meanwhile, Big Jim suspects new residents Christine and Eva are keeping secrets concerning the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/12/31939.jpg',
        'http://www.tvmaze.com/episodes/151645/under-the-dome-3x03-redux');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (31, 153120, 3, 4, 'The Kinship', '2015-07-09', '22:00:00', 60,
        'Urges the townspeople towards specific individuals and projects that remind them of their experience in the tunnels. Also, Julia and Big Jim make shocking discoveries that reveal a new threat within the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/12/32457.jpg',
        'http://www.tvmaze.com/episodes/153120/under-the-dome-3x04-the-kinship');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (32, 176889, 3, 5, 'Alaska', '2015-07-16', '22:00:00', 60,
        'Big Jim and Julia form a tentative alliance to search for proof that will discredit Christine, which leads them to new information about the Dome''s capabilities. Meanwhile, when tensions run high in town and threaten Christine''s leadership, she puts a plan in play that has deadly consequences.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/13/33494.jpg',
        'http://www.tvmaze.com/episodes/176889/under-the-dome-3x05-alaska');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (33, 176890, 3, 6, 'Caged', '2015-07-23', '22:00:00', 60,
        'For information about her agenda. Also, when Joe and Norrie question the town''s new rules, they find themselves in a dangerous face-off with the increasingly unstable residents.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/13/34493.jpg',
        'http://www.tvmaze.com/episodes/176890/under-the-dome-3x06-caged');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (34, 185048, 3, 7, 'Ejecta', '2015-07-30', '22:00:00', 60,
        'As the world outside the Dome is rocked by a catastrophic meteor shower, unexpected alliances form inside the barrier. Eva tries to indoctrinate Barbie deeper into The Kinship and Big Jim and Julia turn to one another while isolated on Bird Island outside of town. Also, Joe is forced to accept help from Sam, the man who killed his sister.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/13/34948.jpg',
        'http://www.tvmaze.com/episodes/185048/under-the-dome-3x07-ejecta');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (35, 185049, 3, 8, 'Breaking Point', '2015-08-06', '22:00:00', 60,
        'Who is mobilizing residents to work on a massive excavation project in the caves underneath the town. Also, Hunter uses his tech skills to contact the outside world.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/14/36036.jpg',
        'http://www.tvmaze.com/episodes/185049/under-the-dome-3x08-breaking-point');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (36, 185050, 3, 9, 'Plan B', '2015-08-13', '22:00:00', 60,
        'Control over the town by initiating a life-or-death plot involving Barbie and Eva, while Joe and Norrie conduct research to better understand the Dome''s ultimate agenda. Meanwhile, Hunter finds more information about the true head of Aktaion, the nefarious private corporation that wants to harness the Dome''s energy.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/14/37358.jpg',
        'http://www.tvmaze.com/episodes/185050/under-the-dome-3x09-plan-b');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (37, 185051, 3, 10, 'Legacy', '2015-08-20', '22:00:00', 60,
        'The nefarious head of Aktaion. Despite Hektor revealing more about the Dome''s origins and helping to strike back at it, Big Jim and Julia still worry he will betray them. Meanwhile, Hunter receives encrypted files that give disturbing insights into the final effect the Dome will have on the infected townspeople.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/15/39057.jpg',
        'http://www.tvmaze.com/episodes/185051/under-the-dome-3x10-legacy');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (38, 185052, 3, 11, 'Love is a Battlefield', '2015-08-27', '22:00:00', 60,
        'The head of Aktaion, to test a possible cure for infected townspeople. Also, Joe chooses to work with Christine after she shares some shocking information about the entity behind the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/16/40250.jpg',
        'http://www.tvmaze.com/episodes/185052/under-the-dome-3x11-love-is-a-battlefield');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (39, 185053, 3, 12, 'Incandescence', '2015-09-03', '22:00:00', 60,
        'Who is adamant that infected townspeople never escape the Dome.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/16/41932.jpg',
        'http://www.tvmaze.com/episodes/185053/under-the-dome-3x12-incandescence');
INSERT INTO episode (episode_id, tvmaze_id, season_id, number, name, airdate, airtime, runtime, summary, image_url,
                     tvmaze_url)
VALUES (40, 185054, 3, 13, 'The Enemy Within', '2015-09-10', '22:00:00', 60,
        'As the Dome in Chester''s Mill comes down, the Resistance makes a final attempt to protect the outside world from the infected townspeople in the Kinship and their new queen.',
        'http://static.tvmaze.com/uploads/images/medium_landscape/17/43622.jpg',
        'http://www.tvmaze.com/episodes/185054/under-the-dome-3x13-the-enemy-within');
