/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// script.js

function delPro(path, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 201) {
                location.reload();
                alert("Xóa thành công!!!");
            } else
                alert("thất bại!!!");
        });
    }
}

function recycleBin(path, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "put"
        }).then(res => {
            if (res.status === 201) {
                location.reload();
                alert("Xóa thành công!!!");
            } else
                alert("thất bại!!!");
        });
    }
}

function confirmShipper(path, id) {
    if (confirm("Bạn có muốn xác nhận người này?") === true) {
        fetch(path, {
            method: "put"
        }).then(res => {
            if (res.status === 201) {
                location.reload();
                alert("Đã xác nhận thành công!!!");
            } else
                alert("thất bại!!!");
        });
    }
}

function refuseShipper(path, id) {
    if (confirm("Bạn chắc chắn từ chối người này không?") === true) {
        fetch(path, {
            method: "put"
        }).then(res => {
            if (res.status === 201) {
                location.reload();
                alert("Đã từ chối thành công!!!");
            } else
                alert("Thất bại!!!");
        });
    }
}





