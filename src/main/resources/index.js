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