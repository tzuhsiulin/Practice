
class FieldLayer {

  constructor(context) {
    this.context = context;
    this.initContainer();
    this.drawCircle();
  }

  initContainer() {
    const { width, height } = this.context.windowSize;
    this.fieldLayer = d3.select("body")
      .append("canvas")
      .attr("id", "field-layer")
      .attr("width", width)
      .attr("height", height);
    this.canvasContext = this.fieldLayer.node().getContext("2d");
  }

  drawCircle() {
    const { projection } = this.context;
    var radius = 70;
    var position = projection([ 120.240813029, 22.822963271 ]);
    this.canvasContext.globalCompositeOperation = "lighter";

    // context.shadowOffsetX = 100;
    // context.shadowOffsetY = 100;
    // context.strokeStyle = "rgba(255, 255, 255, 0.1)";
    // context.shadowColor="yellow";
    // context.shadowBlur = 20;

    // context.strokeStyle = "rgba(255, 255, 255, 0.1)";
    // context.lineWidth = 0;

    var radgrad = this.canvasContext.createRadialGradient(position[0], position[1], 0, position[0], position[1], radius);
    radgrad.addColorStop(0, 'rgba(255, 0, 0, 1)');
    radgrad.addColorStop(0.8, 'rgba(255, 0, 0, 0.5)');
    radgrad.addColorStop(1, 'rgba(255, 0, 0, 0)');

    this.canvasContext.beginPath();
    this.canvasContext.arc(position[0], position[1], radius, 0, 2 * Math.PI, false);
    this.canvasContext.fillStyle = radgrad;
    this.canvasContext.fill();
  }

}

export default FieldLayer;
