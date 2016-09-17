
class Legend {

  constructor(context) {
    this.context = context;
    this.svgContainer = this.initContainer();
    this.draw();
  }

  initContainer() {
    const {
      LEGEND,
      windowSize: { width, height }
    } = this.context;
    return d3.select("body")
      .append("svg")
      .attr("width", width)
      .attr("height", height)
      .attr("class", LEGEND)
      .append("g");
  }

  draw() {
    d3.selectAll("rect")
      .data(["rgb(119, 255, 51)", "rgb(255, 255, 51)", "rgb(255, 153, 51)", "rgb(255, 51, 51)", "rgb(51, 0, 0)"])
      .enter()
      .append("rect")
      .attr("height", 8)
      .attr("x", );
  }

}

export default Legend;
