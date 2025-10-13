package com.peliculas.peliculas.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PeliculaDto {

        @JsonProperty("imdbID")
        private String imdbID;

        @JsonProperty("Title")
        private String title;

        @JsonProperty("Year")
        private String year;

        @JsonProperty("Director")
        private String director;

        @JsonProperty("Plot")
        private String plot;

        @JsonProperty("Poster")
        private String poster;


        public String getImdbID() {
                return imdbID;
        }
        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        public String getDirector() {
                return director;
        }

        public void setDirector(String director) {
                this.director = director;
        }

        public String getPlot() {
                return plot;
        }

        public void setPlot(String plot) {
                this.plot = plot;
        }

        public String getPoster() {
                return poster;
        }

        public void setPoster(String poster) {
                this.poster = poster;
        }

         public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }
}
