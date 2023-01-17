async function init()
{
    document.getElementById("update")
            .addEventListener("click", async () => {

                const errorBox = document.getElementById("error-box");
                const infoDisp = document.getElementById("info-disp");

                const lineSect = document.getElementById("line-sect");
                const lineList = document.getElementById("line-list");

                const stopSect = document.getElementById("stop-sect");
                const stopList = document.getElementById("stop-list");
                const stopHead = document.getElementById("stop-head");

                errorBox.style.display = "none";
                infoDisp.style.display = "";
                infoDisp.innerHTML = "Väntar på data från servern...";

                try {
                    const resp = await fetch("http://localhost:8081/", {
                        mode: "cors"
                    });
                    const json = await resp.json();

                    lineList.innerHTML = "";

                    for (const index in json) {
                        const line = json[index];
                        const item = document.createElement("li");

                        item.style.cursor = "pointer";
                        item.innerHTML =
                                "<span>Linje " + line.number + "</span>" +
                                "<span class=\"w3-badge w3-indigo w3-margin-left\">" + line.stops.length + "</span>";

                        item.addEventListener("click", () => {
                            if (stopSect.dataset.index === index) {
                                stopSect.style.display = stopSect.style.display === "none" ? "" : "none";
                                return;
                            }

                            stopHead.innerHTML = "Hållplatser (Linje " + index + ")";
                            stopList.innerHTML = "";
                            stopSect.style.display = "";
                            stopSect.dataset.index = index;

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
                } catch (e) {
                    errorBox.innerHTML = "Gick inte att hämta data (" + e.message + ")";
                    errorBox.style.display = "";
                } finally {
                    infoDisp.style.display = "none";
                }
            });
}

addEventListener("DOMContentLoaded", () => init());
