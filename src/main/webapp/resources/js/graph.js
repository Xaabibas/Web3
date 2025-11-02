const svg = document.getElementById("graph");
let pt = svg.createSVGPoint();
const centerX = 250;
const centerY = 250;
const gap = 40;

function drawGraph(event, ui) {
    const newR = ui.value;
    const triangle = document.getElementById("triangle");
    const rectangle = document.getElementById("rectangle");
    const  circle = document.getElementById("circle");

    const newTrianglePoints = [
        [centerX, centerY],
        [centerX, centerY + newR * gap],
        [centerX + newR / 2 * gap, centerY]
    ];
    triangle.setAttribute(
        "points",
        newTrianglePoints.map(p => p.join(",")).join(" ")
    );

    const newRectanglePoints = [
        [centerX, centerY],
        [centerX, centerY + newR * gap],
        [centerX - newR * gap , centerY + newR * gap],
        [centerX - newR * gap, centerY]
    ];
    rectangle.setAttribute(
        "points",
        newRectanglePoints.map(p => p.join(",")).join(" ")
    );

    const startX = centerX + newR * gap;
    const startY = centerY;
    const endX = centerX;
    const endY = centerY - newR * gap;
    const sweepFlag = 0;
    const R = newR * gap;

    circle.setAttribute(
       "d",
       `M ${startX} ${startY} A ${R} ${R} 0 0 ${sweepFlag} ${endX} ${endY} L ${centerX} ${centerY} Z`
    );
}

function drawPoint(x, y, result) {
    const point = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    if (result) {
        point.classList.add("hit");
    } else {
        point.classList.add("miss");
    }
    point.style.visibility = "visible";
    point.setAttribute("r", 3);

    point.setAttribute("cx", centerX + x * gap);
    point.setAttribute("cy", centerY - y * gap);
    svg.appendChild(point);
}

let lastX = 0;
let lastY = 0;

svg.onclick = async function clickedPoint(e) {
    pt.x = e.clientX;
    pt.y = e.clientY;

    let cursorPoint = pt.matrixTransform(svg.getScreenCTM().inverse());

    let correctX = ((cursorPoint.x - 250) / gap).toFixed(2);
    let correctY = (-(cursorPoint.y - 250) / gap).toFixed(2);

    const res = await getR();
    currentR = res.jqXHR.pfArgs.r;

    lastX = correctX;
    lastY = correctY;

    const response = await addPoint([
        {name: "x", value: correctX},
        {name: "y", value: correctY},
        {name: "r", value: currentR}
    ]);

    const result = response.jqXHR.pfArgs.result;
    drawPoint(lastX, lastY, result);
}

function drawAttempts(attempts) {
    for (let i = 0; i < attempts.length; i++) {
        const x = attempts[i].point.x;
        const y = attempts[i].point.y;
        const result = attempts[i].result;

        drawPoint(x, y, result);
    }
}

async function drawDefaultPoint() {
    const response = await update();
    const x = response.jqXHR.pfArgs.x;
    const y = response.jqXHR.pfArgs.y;
    const result = response.jqXHR.pfArgs.result;
    console.log(x, y, result);
    drawPoint(x, y, result);
}

function clear() {
    // TODO: clear points
}

window.drawAttempts = drawAttempts;