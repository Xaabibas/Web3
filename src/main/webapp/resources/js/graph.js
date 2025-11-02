const svg = document.getElementById("graph");
const centerX = 250;
const centerY = 250;
const gap = 40;

function drawGraph(newR) {
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

drawGraph(5);