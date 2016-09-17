
class Map {

  constructor(options) {
    this.options = options;
    this.tileOptions = {
      base: ".basemaps.cartocdn.com/light_all/",
      blackAndWhite: ".tiles.wmflabs.org/bw-mapnik/"
    };
    this.projection = options.projection;

    // init tile of d3 plugin
    this.tile = d3.geo.tile().size([ options.width, options.height ]);

    // init map projection
    const center = this.projection([ 121.50, 23.47 ]);

    // create event listeners to handle zooming and panning gestures
    // scale: Specifies the current zoom scale. If not specified, returns the current zoom scale, which defaults to 1.
    // scaleExtent: Specifies the zoom scale's allowed range as a two-element array, [minimum, maximum]. If not specified, returns the current scale extent, which defaults to [0, Infinity].
    // translate: Specifies the current zoom translation vector. If not specified, returns the current translation vector, which defaults to [0, 0].
    this.zoom = d3.behavior.zoom()
      .scale(this.projection.scale() * 2 * Math.PI)
      .scaleExtent([ (1 << 14) / 2 / Math.PI, (1 << 23) / 2 / Math.PI ])
      .translate([ options.width - center[0], options.height - center[1] ])
      .on("zoom", this.draw);

    // init dom
    // call: Invokes the specified function once, passing in the current selection along with any optional arguments
    const map = d3.select("body").append("div")
      .attr("class", "map")
      .style("width", options.width + "px")
      .style("height", options.height + "px")
      .call(this.zoom);
    this.layer = map.append("div").attr("class", "layer");
  }

  matrix3d(scale, translate) {
    const k = scale / 256;
    const r = scale % 1 ? Number : Math.round;
    return "matrix3d(" + [k, 0, 0, 0, 0, k, 0, 0, 0, 0, k, 0, r(translate[0] * scale), r(translate[1] * scale), 0, 1] + ")";
  }

  draw = () => {
    var tiles = this.tile.scale(this.zoom.scale()).translate(this.zoom.translate())();

    this.projection.scale(this.zoom.scale() / 2 / Math.PI).translate(this.zoom.translate());
    const image = this.layer
      .style(this.options.envPrefix + "transform", this.matrix3d(tiles.scale, tiles.translate))
      .selectAll(".tile")
      .data(tiles, d => d);
    image.exit().remove();
    image.enter().append("img")
      .attr("class", "tile")
      .attr("src", d => {
        return "http://" + ["a", "b", "c"][Math.random() * 3 | 0] +
          this.tileOptions[this.options.tileStyle] + d[2] + "/" + d[0] + "/" + d[1] + ".png";
      })
      .style("left", d => (d[0] << 8) + "px")
      .style("top", d => (d[1] << 8) + "px");
  }

}

export default Map;
