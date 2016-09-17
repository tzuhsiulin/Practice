
// d3.json('./assets/twCounty2010.topo.json', function(topodata) {
//   var size = getWindowSize();
//   var width = size.width;
//   var height = size.height;

//   var data = topojson.feature(topodata, topodata.objects.layer1);

//   var scale  = 100;
//   var offset = [width / 2, height / 2];
//   var center = d3.geo.centroid(data)
//   var projection = d3.geo.mercator().center(center).scale(scale).translate(offset);
//   var path = d3.geo.path().projection(projection);

//   var bounds  = path.bounds(data);
//   var hscale  = scale * width / (bounds[1][0] - bounds[0][0]);
//   var vscale  = scale * height / (bounds[1][1] - bounds[0][1]);
//   scale   = (hscale < vscale) ? hscale : vscale;
//   offset  = [width - (bounds[0][0] + bounds[1][0]) / 2,
//                     height - (bounds[0][1] + bounds[1][1]) / 2];
//   console.log(width, (bounds[0][0] + bounds[1][0]), height, (bounds[0][1] + bounds[1][1]));
//   projection = d3.geo.mercator().center(center).scale(scale).translate(offset);
//   path = path.projection(projection);

//   d3.select(MAP_SVG_ID)
//     .attr("width", size.width)
//     .attr("height", size.height)
//     .append("path")
//     .datum(data)
//     .attr("id", "tw-map")
//     .attr("d", path);
// });