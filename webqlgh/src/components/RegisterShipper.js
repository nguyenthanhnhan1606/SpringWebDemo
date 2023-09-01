import { useContext } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { Navigate, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import { useState } from "react";
import { authApi, endpoints } from "../configs/Apis";

const RegisterShipper = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [cmnd, setCmnd] = useState(user ? user.cmnd : "");
    const nav = useNavigate();

    const submitRegister = async (e) => {
        e.preventDefault();
        if (cmnd === null || !cmnd) {
            alert("Vui lòng nhập CMND trước khi đăng ký shipper.");
            return;
        }
        try {
            const formData = new FormData();
            formData.append("idUser", user.id);
            formData.append("cmnd", cmnd);
            const response = await authApi().post(endpoints['shippers'], formData);
            if (response.status === 201) {
                const userConfirm = window.confirm("Bạn đã đăng ký thành công.Chờ xét duyệt bạn có muốn quay lại trang chủ ko?");
                if (userConfirm)
                    nav("/")

            }
            if (response.status === 204) {
                const userConfirm = window.confirm("Bạn đã đăng ký rồi.Đang chờ xét duyệt bạn có muốn quay lại trang chủ ko?");
                if (userConfirm)
                    nav("/")
            }

        } catch (error) {
            console.error("Error submitting shipper registration:", error);
        }
    }


    if (!user)
        return <Navigate to="/login" />
    return (<>
        <Container>
            <Row className="justify-content-center">
                <Col xs={12} md={6}>
                    <Form>
                        <h1 className="text-center text-info">Đăng ký shipper</h1>
                        <Form.Group controlId="cmnd">
                            <Form.Label>Vui lòng nhập CMND</Form.Label>
                            <Form.Control
                                type="text"
                                name="cmnd"
                                value={cmnd}
                                onChange={e => setCmnd(e.target.value)}
                            />
                            <Button variant="primary" className="mt-2" onClick={submitRegister} >
                                Đăng ký
                            </Button>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
        </Container>

    </>)
}
export default RegisterShipper;