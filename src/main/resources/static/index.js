const onFindAll = async () => {
    const tbody=document.querySelector("#table tbody");
    let html="";
    const response=await axios.get( "/dboard" );
    const data=response.data;
    for(let i=0;i<data.length;i++){
        const board=data[i];
        html+=`
        <tr>
        <td>${board.emp_name}</td>
        <td>${board.dept_name}</td>
        <td>${board.position}</td>
        <td>
        <button onclick="onUpdate(${board.emp_code})"> 수정 </button>
        <button onclick="onDelete(${board.emp_code})"> 삭제 </button>
        </td>
        <td>`;

    }
}


// 휴가조회 
const findAll = async () => {
    const container = document.querySelector(".all-list");

    let html = "";

    const response = await axios.get("/vacation");
    const data = response.data;

    for (let i = 0; i < data.length; i++) {
        const vacation = data[i];

        html += `
        <div class="list">
            <div class="top">
                <h4>${vacation.emp_name}</h4>
                <button onclick="vacationDelete(${vacation.leave_code})">신청취소</button>
            </div>
            <div class="center">
                ${vacation.start_date} ~ ${vacation.end_date}
            </div>
            <div class="bottom">
                사유: ${vacation.leave_reason}
            </div>
        </div>
        `;
    }

    container.innerHTML = html; // ⭐ 이거 꼭 있어야 함
}

findAll();
// 휴가 신청
const onInsert = async () => {

    const emp_name = document.querySelector(".inputdept").value;
    const start_date = document.querySelector(".startdate").value;
    const end_date = document.querySelector(".enddate").value;
    const leave_reason = document.querySelector(".width").value;

    // 유효성 검사
    if (emp_name === "" || start_date === "" || end_date === "" || leave_reason === "") {
        alert("모든 항목을 입력하세요.");
        return;
    }

    const obj = {
        emp_name,
        start_date,
        end_date,
        leave_reason
    };
    
    const response = await axios.post("/vacation", obj);
    const data = response.data;

    console.log(response);
console.log(response.data);

    if (data === true) {
        alert("휴가 신청이 완료되었습니다.");
        findAll(); // 목록 다시 불러오기
    } else {
        alert("휴가 신청 실패");
    }
};

// 사원 목록 조회 
const findEmp = async () => {
    const select = document.querySelector(".inputdept");

    let html = "";

    const response = await axios.get("/fmember");
    const data = response.data;

    for (let i = 0; i < data.length; i++) {
        const emp = data[i];
        html += `<option value="${emp.emp_name}">${emp.emp_name}</option>`;
    }

    select.innerHTML = html;
}
findEmp();


// 휴가 취소 
const vacationDelete = async (leave_code) => {
    const response = await axios.delete(`/vacation?leave_code=${leave_code}`);
    const data = response.data;
    if (data === true) {
        alert("휴가 신청이 취소되었습니다.");
        findAll(); // 목록 다시 불러오기
    } else {
        alert("휴가 신청 취소 실패");
    }
}