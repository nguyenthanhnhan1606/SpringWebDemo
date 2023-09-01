import { Route, Routes } from "react-router-dom"
import Header from "./layout/Header"
import Footer from "./layout/Footer"
import Home from "./components/Home"
import Shipper from "./components/Shipper";
import 'bootstrap/dist/css/bootstrap.min.css';
import ShipperDetail from "./components/ShipperDetail";
import Order from "./components/Order";
import CreateOrder from "./components/CreateOrder";
import Login from "./components/Login";
import { createContext, useReducer } from "react";
import cookie from "react-cookies";
import MyUserReducer from "./reducers/MyUserReducer";
import CurrentUser from "./components/CurrentUser";
import Register from "./components/Register";
import RegisterShipper from "./components/RegisterShipper";
import ListOrder from "./components/ListOrder";
import ListOrderDg from "./components/ListOrderDg";
import HisDauGia from "./components/HisDauGia";
import DauGiaSuccess from "./components/DauGiaSuccess";

export const MyUserContext = createContext();


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);

  return (
    <MyUserContext.Provider value={[user, dispatch]} >
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shippers" element={<Shipper />} />
          <Route path="/shippers/:id" element={<ShipperDetail />} />
          <Route path="/order" element={<Order />} />
          <Route path="/lsdaugia" element={<HisDauGia />} />
          <Route path="/daugiasucces" element={<DauGiaSuccess />} />
          <Route path="/listorder" element={<ListOrder />} />
          <Route path="/listorderdg" element={<ListOrderDg />} />
          <Route path="/createorder" element={<CreateOrder />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/registersp" element={<RegisterShipper />} />
          <Route path="/current-user" element={<CurrentUser />} />
        </Routes>
        <Footer />
    </ MyUserContext.Provider>
  )

}

export default App;