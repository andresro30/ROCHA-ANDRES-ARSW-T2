var app = (function(){

    var getAirportsByName = function(name){
        apiClient.getAirportsByName(name,generarFila)
    }
    var generarFila = (function(lista){
        $("#table").empty();
        lista.map(function(airport){
            var fila = "<tr><td>"+airport.code+"<tr><td>"+airport.name+"<tr><td>"+airport.city+"<tr><td>"+airport.countryCode;
            $("#table").append(fila)
        })
        plotMarkers(lista);
    })

    return{
        getAirportsByName: getAirportsByName,
    }
})();