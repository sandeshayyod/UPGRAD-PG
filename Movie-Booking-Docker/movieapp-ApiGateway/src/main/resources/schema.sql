create table movie
(
   movie_id integer not null,
   movie_name varchar(255) not null,
   movie_desc varchar(255),
   release_date timestamp,
   duration INTEGER NOT NULL,
   cover_photo_url varchar(255),
   trailer_url varchar(255),
   primary key(movie_id)
);
