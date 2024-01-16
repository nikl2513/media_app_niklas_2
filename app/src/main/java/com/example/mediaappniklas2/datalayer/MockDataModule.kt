package com.example.mediaappniklas2.datalayer



fun createMockDataList() : List<MovieData>{


   val list =  listOf<MovieData>(
        MovieData("001","001","The Wolf of Wall Street","2014","https://www.movieposters.com/cdn/shop/products/wolfofwallstreet_480x.progressive.jpg?v=1620229322"),
        MovieData("002","002","The Big Short","2015","https://m.media-amazon.com/images/I/91dC4o8mScL._AC_UF894,1000_QL80_.jpg"),
        MovieData("003","003","Margin Call","2011","https://m.media-amazon.com/images/M/MV5BMjE5NzkyNDI2Nl5BMl5BanBnXkFtZTcwMTYzNDc2Ng@@._V1_.jpg"),
        MovieData("004","004","All Quiet on the Western Front","2022","https://dx35vtwkllhj9.cloudfront.net/netflix/all-quiet-on-the-western-front/images/regions/us/onesheet.jpg"),
        MovieData("005","005","The Longest Day","1962","https://m.media-amazon.com/images/I/51dbJQ1cCKL._AC_UF894,1000_QL80_.jpg"),
        MovieData("006","006","Ran","1985","https://m.media-amazon.com/images/I/815ZpOPgObL._AC_UF894,1000_QL80_.jpg"),
        MovieData("007","007","Monty Python's Life of Brian","1979","https://i.ebayimg.com/images/g/NFIAAOSwy21d2XZA/s-l1600.jpg"),
        MovieData("008","008","The Godfather","1972","https://m.media-amazon.com/images/I/61WL2lvVrtL._AC_UF894,1000_QL80_.jpg"),
        MovieData("009","009","Pulp Fiction","1994","https://m.media-amazon.com/images/I/71wPS3A1EYL._AC_UF894,1000_QL80_.jpg"),
        MovieData("010","010","Ratatouille","2017","https://m.media-amazon.com/images/I/71nmk6zlvcL._AC_UF894,1000_QL80_.jpg"),
        MovieData("011","011","Scary Movie","2000","https://m.media-amazon.com/images/M/MV5BMGEzZjdjMGQtZmYzZC00N2I4LThiY2QtNWY5ZmQ3M2ExZmM4XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"),
        MovieData("012","012","Interstellar","2014","https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg"),
        MovieData("013","013","Get Out", "2017", "https://m.media-amazon.com/images/M/MV5BMjUxMDQwNjcyNl5BMl5BanBnXkFtZTgwNzcwMzc0MTI@._V1_.jpg"),
        MovieData("014","014","Oppenheimer","2023","https://www.nfbio.dk/sites/nfbio.dk/files/styles/movie_poster/public/media-images/2023-06/Oppenheimer%20-%20Plakat.PNG?itok=Zl5Ew_TZ"),
        MovieData("015","015","Avengers: Endgame","2019", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg"),
        MovieData("016","016","Kung Fu Hustle ","2004", "https://m.media-amazon.com/images/M/MV5BMjZiOTNlMzYtZWYwZS00YWJjLTk5NDgtODkwNjRhMDI0MjhjXkEyXkFqcGdeQXVyMjgyNjk3MzE@._V1_.jpg"),
        MovieData("017","017","Shaolin Soccer","2001","https://m.media-amazon.com/images/M/MV5BMTk3NDg5NTE4MV5BMl5BanBnXkFtZTcwNzMxMjAwMQ@@._V1_.jpg"),
        MovieData("018","018","The Social Network","2010","https://m.media-amazon.com/images/M/MV5BNmMxZWQ2YjAtMDY3Yi00ZTU4LWEyY2ItM2E3MTgxYzEyOGEyXkEyXkFqcGdeQWpybA@@._V1_.jpg"),
        MovieData("019","019","The Hunger Games","2012","https://m.media-amazon.com/images/M/MV5BMjA4NDg3NzYxMF5BMl5BanBnXkFtZTcwNTgyNzkyNw@@._V1_.jpg"),
        MovieData("020","020","Se7en","1995","https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg"),
        MovieData("021","021","Batman Begins","2005","https://www.kultunaut.dk/images/film/2653133/plakat.jpg"),
        MovieData("022","022","The Dark Knight","2008","https://www.kultunaut.dk/images/film/7087149/plakat.jpg"),
        MovieData("023","023","The Dark Knight Rises","2012","https://www.kultunaut.dk/images/film/7089376/plakat.jpg"),
        MovieData("024","024","Joker","2019","https://www.kultunaut.dk/images/film/7097756/plakat.jpg"),
        MovieData("025","025","The Batman","2022","https://www.kultunaut.dk/images/film/7100552/plakat.jpg"),
        MovieData("026","026","Dumb and Dumber To","2014","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWJ22K5zsNKxC6zmROmAjovOxw5g4_h8t2SaKcAaEMfLZ_2pWl"),
        MovieData("027","027","Spider-Man: No Way Home","2021","https://www.kultunaut.dk/images/film/7101057/plakat.jpg"),
        MovieData("028","028","Shutter Island","2010","https://images-na.ssl-images-amazon.com/images/S/pv-target-images/4651306db4501622166aa68b058b20e7a3c598bcfebea4ebc56733301d54fdae._RI_TTW_.jpg"),
        MovieData("029","029","Biler","2006","https://img-cdn.sfanytime.com/COVERM/cd459f22-8e8f-4e84-ac76-a5df00eaf664_COVERM_DA.jpg?w=375&fm=pjpg&s=60c97ab306628f0ecff20ea7bd9cfff8"),
        MovieData("030","030","Shrek","2001","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSnvRBJ9yORH2QcFIrBNn_t7O9_RvQyW5U-lqEzw9vr9mhLanMj"),



   )







    return list
}