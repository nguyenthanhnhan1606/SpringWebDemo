import { useContext, useEffect, useState } from "react";
import { Alert, Badge, Button, Card, Col, Container, ListGroup, Row } from "react-bootstrap";
import { authApi, endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import { Link, Navigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../App";

const Order = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [orders, setOrders] = useState(null);
    const [q] = useSearchParams();
    const [showSpinner, setShowSpinner] = useState(false);
    const trangThaiMauSac = {
        "Hoàn thành": "success",
        "Hoàn hàng": "danger",
        "Mới tạo": "info",
        "Đang đấu giá": "primary",
        "Đang giao": "Warning"
        // ...Thêm các trạng thái và màu sắc tương ứng
    };
    const sendEmail = async (orderId, shipperId, daugiaId) => {
        setShowSpinner(true);
        setTimeout(() => {
            setShowSpinner(false);
        }, 3000);
        if (orders.some(order => order.id === orderId && order.daugiaSet.some(daugia => daugia.ketqua === true))) {
            window.confirm("Đơn hàng này đã có shipper hoặc đã được chọn shipper. Không thể chọn thêm!");
            return;
        }
        try {
            const formData = new FormData();
            formData.append("orderId", orderId);
            formData.append("shipperId", shipperId);
            formData.append("daugiaId", daugiaId);
            const res = await authApi().post(endpoints['updateDG'], formData);
            if (res.data === "success") {
                alert("Chọn thành công")
                loadOrder();
            }
            const response = await authApi().post(endpoints['sendMail'], formData);
            console.log(response.data);
        } catch (error) {
            console.error("Error sending email:", error);
        }
    };

    const loadOrder = async () => {
        try {
            let e = `${endpoints['addOrder']}/${user.id}`;
            let search = q.get("search");
            if (search !== null)
                e = `${e}?search=${search}`
            let trangthai = q.get("trangthai");
            if (trangthai != null)
                e = `${e}?trangthai=${trangthai}`
            let res = await authApi().get(e);
            setOrders(res.data);
        } catch (ex) {
            console.error(ex);
        }

    }
    useEffect(() => {

        loadOrder();
    }, [q])
    if (user === null)
        return <Navigate to="/login" />
    if (orders === null)
        return <MySpinner />
    if (orders.length === 0)
        return <Alert variant="info" className="mt-1 text-center">Không có sản phẩm nào!</Alert>

    return (
        <Container className="mt-2">
            <h1 className="text-center text-info">Danh sách đơn hàng</h1>
            {showSpinner && <MySpinner />}
            {orders.map(order => (
                <Card key={order.id} className="mb-4">
                    <Card.Body>
                        <Row>
                            <Col xs={12} md={3} className="text-center">
                                {/* <h4>Hình ảnh đơn hàng</h4> */}
                                <img
                                    src={order.image}
                                    alt={`Đơn hàng #${order.id}`}
                                    style={{ maxWidth: '100%', maxHeight: '300px' }}
                                />
                            </Col>
                            <Col xs={12} md={6}>
                                <h4>Thông tin đơn hàng</h4>
                                <ListGroup variant="flush">
                                    <ListGroup.Item><strong>Nơi gửi:</strong> {order.noigui}</ListGroup.Item>
                                    <ListGroup.Item><strong>Nơi nhận:</strong> {order.noinhan}</ListGroup.Item>
                                    <ListGroup.Item><strong>Ngày tạo:</strong> {order.ngaytao}</ListGroup.Item>
                                    <ListGroup.Item><strong>Mô tả:</strong> {order.mota}</ListGroup.Item>
                                    {order.daugiaSet.filter(daugia => daugia.ketqua === true)
                                        .map(daugia => (
                                            <ListGroup.Item key={daugia.id}>
                                                <strong>Shipper:</strong> {daugia.idShipper.user.ten}
                                            </ListGroup.Item>
                                        ))}
                                    <ListGroup.Item>
                                        <strong>Giá trị đơn hàng:</strong> {order.giatridh} VND
                                    </ListGroup.Item>
                                    <ListGroup.Item>
                                        <strong>Trạng thái:</strong>{' '}
                                        <Badge bg={trangThaiMauSac[order.trangthai]}>
                                            {order.trangthai}
                                        </Badge>
                                    </ListGroup.Item>
                                </ListGroup>
                            </Col>

                        </Row>
                        <ListGroup>
                            {order.daugiaSet.map(daugia => (
                                <ListGroup.Item key={daugia.id}>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div>
                                            <strong>{daugia.idShipper.user.ten} :</strong> {daugia.gia}
                                        </div>
                                        <div>
                                            {daugia.ketqua === true && daugia !== null ? (<>
                                                <Button variant="secondary" className="me-2">
                                                    Đã Chọn
                                                </Button>
                                            </>
                                            ) : (
                                                <Button variant="info" className="me-2" onClick={() => sendEmail(order.id, daugia.idShipper.id, daugia.id)} >
                                                    Chọn
                                                </Button>
                                            )}
                                            <Button variant="success" as={Link} to={`/shippers/${daugia.idShipper.id}`}>
                                                Xem thông tin shipper
                                            </Button>
                                        </div>
                                    </div>
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    </Card.Body>
                </Card>
            ))}
        </Container>

    );
}
export default Order;