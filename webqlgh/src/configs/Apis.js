import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/WebGiaoHang";
const SERVER = "http://localhost:8080";


export const endpoints = {
    "shippers": `${SERVER_CONTEXT}/api/shippers`,
    "addOrder": `${SERVER_CONTEXT}/api/donhang`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/register/`,
    "order": `${SERVER_CONTEXT}/api/donhang`,
    "updateStatusOrder": `${SERVER_CONTEXT}/api/donhang/update`,
    "getOrderByidShip": `${SERVER_CONTEXT}/api/donhang/daugia`,
    "getOrderSuccessByidShip": `${SERVER_CONTEXT}/api/donhang/daugiasuccess`,
    "getPromotion": `${SERVER_CONTEXT}/api/khuyenmais`,
    "addDauGia":`${SERVER_CONTEXT}/api/adddg`,
    "updateDG":`${SERVER_CONTEXT}/api/danggiao`,
    "sendMail":`${SERVER_CONTEXT}/api/send-email`,
    "sendMailConfirm":`${SERVER_CONTEXT}/api/send-emailconfirm`,
    "comment":`${SERVER_CONTEXT}/api/binhluan`,
    "rating":`${SERVER_CONTEXT}/api/danhgia`
}


export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})