// 부서 관리 페이지 시작
const deptPrint = async () => {
    const tbody=document.querySelector('.tbody-left');
    let html="";
    const response=await axios.get("/category");
    const data=response.data;
    for(let i=0;i<data.length;i++){
        const categorys=data[i];
        html+=`<tr>
        <td>${categorys.dept_name}</td>
        <td>
        <button onclick="deptUpdate(${categorys.dept_key})">수정</button>
        <button onclick="deptDelete(${categorys.dept_key})">삭제</button>
        </td>
        </tr>`;
    }
    tbody.innerHTML=html;
}
deptPrint();
const deptAdd = async () => {
    const dept_name=document.querySelector(".left-category").value;
    const obj={dept_name};
    const response=await axios.post("/category",obj);
    const data=response.data;
    if(data==true){
        alert("등록성공");
        document.querySelector(".left-category").value="";
        deptPrint();
    }    else{
        alert("등록실패 : 관리자에게 문의");
    }
}
const deptUpdate = async () => {

}
const deptDelete = async () => {

}
// 부서 관리 페이지 끝
// 사원 등록 페이지 시작
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
        </tr>`;
    }
    tbody.innerHTML=html;
}
onFindAll();
// 2] 등록 ,실행조건 : 등록버튼을 클릭했을때
const onWrite = async ( ) => {
    // 1. DOM 객체 가져온다. 
    const nameInput = document.querySelector(".name");
    const rankInput = document.querySelector(".rank");
    const categoryInput=document.querySelector(".category");
    // 2. 가져온 DOM 객체내 입력받은 값 꺼내기
    const emp_name=nameInput.value;
    const position=rankInput.value;
    const dept_key=categoryInput.value;
    // 3. 입력받은 값 으로 객체 구성 
    const obj = { "emp_name" : emp_name, "dept_key" : dept_key, "position" : position }
    // 4. (1개월차) 배열에 저장한다 --> (3개월차) AXIOS 이용하여 서버에게 저장 요청한다.
        // 동기화AXIOS , 1] 현재 함수 앞에 async 키워드 붙인다. 2] axios 앞에 await 키워드 붙인다.
    const response = await axios.post( "/dboard" , obj ); // axios.HTTP메소드명( "통신할주소", body본문 )
    const data = response.data;
    if( data == true ){ 
        alert("등록성공");
         nameInput.value = ''; rankInput.value = ''; categoryInput.value='disabled';    // 입력상자에 입력한 값들 초기화
         onFindAll();
    }
    else{ alert("등록실패 : 관리자에게 문의 "); }
} // fun end
// 4] 개별 삭제
const onDelete = async( emp_code ) => { // 1] 삭제할 번호를 매개변수로 받는다.
    // 2] axios 활용하여 삭제할 번호를 서버에게 쿼리스트링으로 요청 하고 결과를 응답 받는다.
    const response = await axios.delete( `/dboard?emp_code=${ emp_code }` );
    const data = response.data;
    // 3] 결과에 따른 코드 제어.
    if( data == true ){ alert("삭제성공"); onFindAll(); } // 화면 새로고침 
    else{ alert("삭제실패 : 관리자에게문의"); }
} // f end

// 5] 개별 수정
const onUpdate = async ( emp_code ) => {
    const emp_name=prompt("수정할 사원 이름");
    const dept_key=prompt("1.개발팀\n2.디자인팀\n3.기획팀");
    const position=prompt("수정할 직급");
    const obj={emp_code,emp_name,dept_key,position}
    const response= await axios.put(`/dboard`,obj);
    const data=response.data;
    if(data==true){
        alert("수정성공");
        onFindAll();
    }else{
        alert("수정 실패 : 관리자에게 문의")
    }
}
// 사원 등록 끝 //
//==================================================//
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