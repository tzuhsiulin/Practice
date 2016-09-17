
import Map from "./map";
import FieldLayer from "./field_layer";
import Legend from "./legend";

class AirMap {

  constructor() {
    this.MAP_SVG_ID = "#map-svg";
    this.FIELD_LAYER = "#field-layer";
    this.LEGEND = "#legend";
    this.windowSize = {
      width: Math.max(960, window.innerWidth),
      height: Math.max(500, window.innerHeight)
    };
    this.envPrefix = this.prefixMatch(["webkit", "ms", "Moz", "O"]);
    this.projection = this.initProjection();
    this.drawMap();
    this.drawFieldLayer();
  }

  initProjection() {
    return d3.geo.mercator()
      .scale((1 << 15) / 2 / Math.PI)
      .translate([ this.windowSize.width / 2, this.windowSize.height / 2]);
  }

  drawMap() {
    const { width, height } = this.windowSize;
    let map = new Map({
      envPrefix: this.envPrefix,
      projection: this.projection,
      tileStyle: "blackAndWhite",
      width,
      height
    });
    map.draw();
  }

  drawFieldLayer() {
    const fieldLayer = new FieldLayer(this);
    const legend = new Legend(this);
  }

  prefixMatch(p) {
    let i = -1;
    const n = p.length;
    const s = document.body.style;
    while (++i < n) if (p[i] + "Transform" in s) return "-" + p[i].toLowerCase() + "-";
    return "";
  }

}

new AirMap();
