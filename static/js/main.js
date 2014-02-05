var charts = document.querySelectorAll(".chart");

for(var i = 0; i < charts.length; i++){
    var data    = eval(charts.item(i).dataset.chart),
        revData = [];
    for(var p in data)
        revData.push({ x: data[p].x, y: 100-data[p].y });

    var graph = new Rickshaw.Graph( {
        element: charts.item(i),
        height: 120,
        series: [
            {
                color: '#0AA699',
                data: data
            },
            {
                color: 'transparent',
                data: revData
            }
        ]
    } );

    graph.render();
}