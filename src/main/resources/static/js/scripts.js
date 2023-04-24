const models = $(".models");
const modelName = $("#model").val();
const properties = $(".properties")
const carName = $("#cars").val();
const container = $("#year, #engine, #transmission, #actuator, #owner");

function establishSelect(container, carName) {
    container.each((k, v) => {
        v.classList.remove("active");
        if (v.getAttribute("id") === carName) {
            v.classList.add("active");
        }
        $(".models").closest(".properties_container").css("display", "none")
        $(".models.active").closest(".properties_container").css({"display": "block", "padding-right": "0"})
    })
}

$(".cars").on("change", function (e) {
    const carName = $(this).val();
    clearAndHideOption(carName, "car");
})

$(".model").on("change", function (e) {
    const carName = $("#cars").val();
    const composedCarName = carName + " " + $(this).val();
    clearSelect($("#year, #engine, #transmission, #actuator, #owner"), composedCarName)
    clearAndHideOption(carName, "model", composedCarName);
    establishSelect(properties, composedCarName);
})

function clearSelect(elems, composedCarName) {
    elems.each((index, elem) => {
        elem.style.border = "1px solid #ced4da";
        const parent = elem.closest(".properties");
        const parent_id = parent.getAttribute("id");
        if (parent_id === composedCarName) {
            for (let option of elem.options) {
                option.disabled = false;
                if (option.value === "") option.selected = "selected";
                else {
                    option.selected = "";
                    option.style.display = "block";
                }
            }
        }
    })
}

function clearAndHideOption(carName, type, id) {
    const option = $("#" + carName);
    models.find("option:selected").removeAttr("selected");
    models.find("option").attr("disabled", true);
    option.find("option").attr("disabled", false);
    option.find("option").eq(0).attr("selected", true);
    const modelName = option.find("option").eq(1).val();
    const composedCarName = type === "car" ? carName + " " + option.find("option").eq(1).val() : id;
    container.each((index, elem) => {
        const parent = elem.closest(".properties");
        const parent_id = parent.getAttribute("id");
        for (let option of elem.options) {
            if (parent_id !== composedCarName) {
                option.setAttribute("disabled", "");
            } else {
                if (elem.getAttribute("id") !== "year") option.setAttribute("disabled", true);
                else option.removeAttribute("disabled");

            }
        }
    })
    establishSelect(models, carName);
    establishSelect(properties, carName + " " + modelName);
}

clearAndHideOption(carName, "model", carName + " " + modelName);
establishSelect(models, carName);
establishSelect(properties, carName + " " + modelName);

container.on("change", function (e) {
    const $this = $(this).attr("id");
    const car = $("#cars").val();
    const model = $(".models.active").find("#model").find("option:selected").val();
    const composedCarName = car + " " + model;
    if ($this === "year") {
        clearSelect($("#engine, #transmission, #actuator, #owner"), composedCarName);
    }
    const post = $("#post").serialize();
    fetch("/post/ajaxFindCarByProperty/" + post, {method: "post"})
        .then((response) => {
            console.log("status =", response.status);
            console.log("Content-Type =", response.headers.get('Content-Type'));
            return response.json();
        }).then((car) => {
        container.each((index, elem) => {
            try {
                const parent = elem.closest(".properties");
                const parent_id = parent.getAttribute("id");
                const property = elem.getAttribute("id");
                const properties = car[0].properties[model][property + "s"];
                if (property === "year") return;
                if (parent_id === composedCarName) {
                    for (let option of elem.options) {
                        if ($this === "year") {
                            if (property === "engine") {
                                elem.style.border = "1px solid green";
                                ajax_selected(properties, option)
                            } else {
                                option.disabled = true;
                            }
                        }
                        if ($this === "engine") {
                            if (property === "engine") continue;
                            if (property === "transmission") {
                                elem.style.border = "1px solid green";
                                ajax_selected(properties, option)
                            } else {
                                option.disabled = true;
                            }
                        }
                        if ($this === "transmission") {
                            if (property === "engine" || property === "transmission") continue;
                            if (property === "actuator") {
                                elem.style.border = "1px solid green";
                                ajax_selected(properties, option)
                            } else {
                                option.disabled = true;
                            }
                        }
                        if ($this === "actuator") {
                            if (property === "engine" || property === "transmission" || property === "actuator") continue;
                            if (property === "owner") {
                                elem.style.border = "1px solid green";
                                ajax_selected(properties, option)
                            } else {
                                option.disabled = true;
                            }
                        }
                    }
                }
            } catch (e) {
            }
        })
    }).catch(console.error);
})

function ajax_selected(properties, option) {
    for (let prop of properties) {
        if (prop.name === option.value || option.value === "") {
            option.style.display = "block";
            option.disabled = false;
        } else {
            option.style.display = "none";
            option.disabled = true;
        }
    }
}