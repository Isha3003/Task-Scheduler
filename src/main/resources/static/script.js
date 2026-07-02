let processCount = 0;

const algorithm =
    document.getElementById("algorithm");

algorithm.addEventListener("change", changeAlgorithm);

function changeAlgorithm() {

    const algo = algorithm.value;

    const priorityHeader =
        document.getElementById("priorityHeader");

    const priorityCells =
        document.querySelectorAll(".priority-cell");

    const quantumDiv =
        document.getElementById("timeQuantumDiv");

    if (algo === "PRIORITY") {

        priorityHeader.style.display = "";

        priorityCells.forEach(cell => {

            cell.style.display = "";

        });

    }

    else {

        priorityHeader.style.display = "none";

        priorityCells.forEach(cell => {

            cell.style.display = "none";

        });

    }

    if (algo === "ROUND_ROBIN") {

        quantumDiv.style.display = "block";

    }

    else {

        quantumDiv.style.display = "none";

    }

}

function addProcess() {

    processCount++;

    const tbody =
        document.getElementById("processBody");

    const row =
        document.createElement("tr");

    row.innerHTML =

        `<td>

            <input
            class="pid"
            value="P${processCount}">

        </td>

        <td>

            <input
            type="number"
            class="arrival"
            value="0">

        </td>

        <td>

            <input
            type="number"
            class="burst"
            value="1">

        </td>

        <td
        class="priority-cell"
        style="display:none;">

            <input
            type="number"
            class="priority"
            value="1">

        </td>

        <td>

            <button
            class="delete-btn"
            onclick="deleteProcess(this)">

                Delete

            </button>

        </td>`;

    tbody.appendChild(row);

    changeAlgorithm();

}

function deleteProcess(button) {

    const row =
        button.parentElement.parentElement;

    row.remove();

}

function resetSimulation() {

    processCount = 0;

    document
        .getElementById("processBody")
        .innerHTML = "";

    document
        .getElementById("resultBody")
        .innerHTML = "";

    document
        .getElementById("ganttChart")
        .innerHTML = "";

    document
        .getElementById("avgWaiting")
        .innerText = "0";

    document
        .getElementById("avgTurnaround")
        .innerText = "0";

    document
        .getElementById("cpuUtilization")
        .innerText = "0%";

    document
        .getElementById("throughput")
        .innerText = "0";

    addProcess();

    addProcess();

    addProcess();

}

function validateInput() {
    


    const rows = document.querySelectorAll("#processBody tr");

    if (rows.length === 0) {
        alert("Please add at least one process.");
        return false;
    }

    for (const row of rows) {

        const processId = row.querySelector(".pid").value.trim();
        const arrival = Number(row.querySelector(".arrival").value);
        
        const burst = Number(row.querySelector(".burst").value);

        if (processId === "") {
            alert("Process ID cannot be empty.");
            return false;
        }

        if (arrival < 0 || isNaN(arrival)) {
            alert("Arrival Time cannot be negative.");
            return false;
        }

        if (burst <= 0 || isNaN(burst)) {
            alert("Burst Time must be greater than 0.");
            return false;
        }

        if (algorithm.value === "PRIORITY") {

            const priority =
                Number(row.querySelector(".priority").value);

            if (priority <= 0 || isNaN(priority)) {
                alert("Priority must be greater than 0.");
                return false;
            }
        }
    }

    if (algorithm.value === "ROUND_ROBIN") {

        const quantum =
            Number(document.getElementById("timeQuantum").value);

        if (quantum <= 0 || isNaN(quantum)) {
            alert("Time Quantum must be greater than 0.");
            return false;
        }
    }

   
return true;
}
async function runSimulation() {
    

    if (!validateInput()) {
        return;

    }
    
    

    const rows =
        document.querySelectorAll("#processBody tr");

    const processes = [];

    rows.forEach(row => {

        const process = {

            processId:
                row.querySelector(".pid").value,

            arrivalTime:
                Number(
                    row.querySelector(".arrival").value
                ),

            burstTime:
                Number(
                    row.querySelector(".burst").value
                )

        };

        if (algorithm.value === "PRIORITY") {

            process.priority =
                Number(
                    row.querySelector(".priority").value
                );

        }

        processes.push(process);

    });

    const request = {

        algorithm: algorithm.value,

        timeQuantum:

            algorithm.value === "ROUND_ROBIN"

                ?

                Number(
                    document.getElementById(
                        "timeQuantum"
                    ).value
                )

                :

                null,

        processes: processes

    };

    try {
         console.log(request);

console.log(algorithm.value);

        const response =
            await fetch("/simulation/run", {

                method: "POST",

                headers: {

                    "Content-Type":
                        "application/json"

                },

                body:
                    JSON.stringify(request)

            });

        const data =
    await response.json();



console.log(data);


showStatistics(data);


showResultTable(data);


showGanttChart(data);



    }

    catch (error) {

       console.error(error);

    alert(error);

    }

}

function showStatistics(data) {

    document.getElementById("avgWaiting").innerText =
        data.averageWaitingTime.toFixed(2);

    document.getElementById("avgTurnaround").innerText =
        data.averageTurnaroundTime.toFixed(2);

    document.getElementById("cpuUtilization").innerText =
        data.cpuUtilization.toFixed(2) + "%";

    document.getElementById("throughput").innerText =
        data.throughput.toFixed(2);

}

function showResultTable(data) {

    const body =
        document.getElementById("resultBody");

    body.innerHTML = "";

    data.processes.forEach(process => {

        body.innerHTML +=

            `<tr>

                <td>${process.processId}</td>

                <td>${process.arrivalTime}</td>

                <td>${process.burstTime}</td>

                <td>${process.completionTime}</td>

                <td>${process.turnaroundTime}</td>

                <td>${process.waitingTime}</td>

                <td>${process.responseTime}</td>

            </tr>`;

    });

}

function showGanttChart(data) {

    const chart =
        document.getElementById("ganttChart");

    chart.innerHTML = "";

    data.ganttChart.forEach(block => {

        chart.innerHTML +=

            `<div class="gantt-box ${block.processId === "Idle" ? "idle-box" : ""}">

                <strong>

                    ${block.processId}

                </strong>

                <br><br>

                ${block.startTime}

                -

                ${block.endTime}

            </div>`;

    });

}

addProcess();

addProcess();

addProcess();

changeAlgorithm();