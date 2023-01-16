addEventListener("DOMContentLoaded", () => {
    document.getElementById("update")
            .addEventListener("click", () => {
                fetch("http://localhost:8081/", {
                    mode: "cors"
                })
                        .then((resp) => resp.json())
                        .then((json) => {
                            console.log(json);
                        });
            });
});
