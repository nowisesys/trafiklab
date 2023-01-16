async function init()
{
    document.getElementById("update")
            .addEventListener("click", async () => {

                const resp = await fetch("http://localhost:8081/", {
                    mode: "cors"
                });
                const json = await resp.json();

                const lineSect = document.getElementById("line-sect");
                const lineList = document.getElementById("line-list");

                const stopSect = document.getElementById("stop-sect");
                const stopList = document.getElementById("stop-list");
                const stopHead = document.getElementById("stop-head");

                lineList.innerHTML = "";

                for (const index in json) {
                    const line = json[index];
                    const item = document.createElement("li");

                    item.style.cursor = "pointer";
                    item.innerHTML =
                            "<span>Linje " + line.number + "</span>" +
                            "<span class=\"w3-badge w3-indigo w3-margin-left\">" + line.stops.length + "</span>";

                    item.addEventListener("click", () => {
                        stopHead.innerHTML = "Hållplatser (Linje " + index + ")";
                        stopList.innerHTML = "";
                        stopSect.style.display = "";

                        for (const index in line.stops) {
                            const stop = line.stops[index];
                            const item = document.createElement("li");

                            item.innerHTML = stop.stopPointName;
                            item.id = stop.stopPointNumber;
                            
                            stopList.appendChild(item);
                        }
                    });

                    lineList.appendChild(item);
                }

                lineSect.style.display = "";
            });
}

addEventListener("DOMContentLoaded", () => init());
