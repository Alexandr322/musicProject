function searchSong() {
    var song=document.getElementById("song").value;
    $.ajax({
        url:"http://localhost:8080/songName/" + song,
        type:"POST",
        dataType:"json"
    }).done(function (data) {
        var idSong =data.song_id;
        $.ajax({
            url: "http://localhost:8080/rating/" + idSong,
            type: "GET",
            dataType: "text"
        }).done(function (rating) {
          var rating= rating;
        $("#content").html("<pre>" +"<p><span style='color: blue;font-size: 17px'>Название артиста: </span>"+data.artist.artistName+"</p>"
            +"<p><span style='color: blue;font-size: 17px'>Название песни: </span>"+data.songName+"</p>"
            +"<p><span style='color: blue;font-size: 17px'>Текст песни:</span>"+"</p>"
            + data.text.replace(/\n/gi,"</br>") +"</br></br>"
            +"<p><span style='color: blue;font-size: 17px'>Оценка песни:  </span>"+rating+"</p>"
            +"<input id='rating-system' type='number' class='rating-stars' min='1' max='5' step='1'>"+"</pre>");
        });
    });
}

function searchOneArtist() {
    var artist=document.getElementById("artist").value;
    $.ajax({
        url:"http://localhost:8080/artist/" + artist,
        type:"POST",
        dataType:"json"
    }).done(function (data) {
        $("#content").html("<pre>" +"<p><span style='color: blue;font-size: 17px'>Aртист: </span>"
            +"<a id='artist1' href='javascript:getSongsListArtists()'>"+data.artistName+"</a>"+"</p>" + "</pre>");
    });
}

function getSongsListArtists() {
    var artist = document.getElementById("artist1").textContent;
    var artistUrl = 'http://localhost:8080/songs/' + artist;
    $.ajax({
        type: 'GET',
        url: artistUrl,
        async: false,
        dataType: 'json',
        success: songReturn
    });
    function songReturn(json) {
        $("#content").html("<span style='color: blue;font-size: 17px'>Песни артиста: </span>" +
            "<pre id='listSong'></pre>");
        $.each(json, function (idx, column) {
            var song = column.songName;
            $("#listSong").append("<a id='"+column.song_id + "' href='javascript:void(0)' onclick='getSongArtist()'>" + song + "</a>" + "</br>");
        });
    }
}

function getSongArtist() {
    $("#listSong").on('click', function (event) {
        var id = event.target.id;
        $.ajax({
            url:"http://localhost:8080/song/" + id,
            type:"POST",
            dataType:"json"
        }).done(function (data) {
            $("#content").html("<pre>" +"<p><span style='color: blue;font-size: 17px'>Название артиста: </span>"+data.artist.artistName+"</p>"
                +"<p><span style='color: blue;font-size: 17px'>Название песни: </span>"+data.songName+"</p>"
                +"<p><span style='color: blue;font-size: 17px'>Текст песни:</span>"+"</p>"
                + data.text.replace(/\n/gi,"</br>") +"</br></br>"+"</pre>");
        });
    });
}

$( function() {
    $("#artist").keyup(function (event) {
        var artist = event.target.value;
        var arrayArtist = [];
        $.ajax({
            url: "http://localhost:8080/artistspart/" + artist,
            type: "GET",
            async: false,
            setTimeout: 5000,
            dataType: "json",
            success: artistSearchPart
        });

        function artistSearchPart(data) {
            $.each(data, function (idx, column) {
                arrayArtist.push(column.artistName);
            });
            $("#artist").autocomplete({
                minLength:2,
                source: function(request, response) {
                    var results = $.ui.autocomplete.filter(arrayArtist, request.term);
                    response(results.slice(0, 20));
                }
            });
        }
    });
});

$( function() {
    $("#song").keyup(function (event) {
        var song = event.target.value;
        var arraySong  = [];
        $.ajax({
            url: "http://localhost:8080/songspart/" + song,
            type: "GET",
            async: false,
            setTimeout: 5000,
            dataType: "json",
            success: songSearchPart
        });

        function songSearchPart(data) {
            $.each(data, function (idx, column) {
                arraySong.push(column.songName);
            });
            $("#song").autocomplete({
                minLength:3,
                source: function(request, response) {
                    var results = $.ui.autocomplete.filter(arraySong, request.term);
                    response(results.slice(0, 20));
                }
            });
        }
    });
});