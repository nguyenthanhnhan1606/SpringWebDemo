import { useContext, useEffect, useState } from "react";
import { Alert, Badge, Button, Card, Col, Container, ListGroup, Row } from "react-bootstrap";
import { Navigate, useSearchParams } from "react-router-dom";
import { authApi, endpoints } from "../configs/Apis";
import { MyUserContext } from "../App";
import MySpinner from "../layout/MySpinner";
import { format } from "date-fns";

const DauGiaSuccess = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [orders, setOrders] = useState(null);
    const [q] = useSearchParams();

    const loadOrder = async () => {
        try {
            let e = `${endpoints['getOrderSuccessByidShip']}/${user.id}`;
            let search = q.get("search");
            if (search !== null)
                e = `${e}?search=${search}`

            // if (trangthai != null)
            //     e = `${e}?trangthai=${trangthai}`
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

    const handleBidSubmit = async (e, orderId,status) => {
        e.preventDefault();
        try{
            const response = await authApi().put(`${endpoints['updateStatusOrder']}/${orderId}`, null, {
                params: { trangthai: status }
            });
            if (response.status === 200) {
                alert("Đã cập nhật trạng thái đơn hàng thành công");
                loadOrder();
            } else {
                alert("Cập nhật trạng thái đơn hàng thất bại");
                return;
            }
            const res = await authApi().post(endpoints['sendMailConfirm'],null,{
                params:{orderId: orderId}
            })
        } catch (error) {
            console.error("Lỗi khi cập nhật trạng thái đơn hàng:", error);
        }
       
    };
    return (
        <Container className="mt-2">
            <h1 className="text-center text-info">Danh sách đơn hàng</h1>
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
                                    <ListGroup.Item><strong>Người tạo:</strong> {order.idUser.ten}</ListGroup.Item>
                                    <ListGroup.Item><strong>Mã đơn hàng:</strong> {order.id}</ListGroup.Item>
                                    <ListGroup.Item><strong>Nơi gửi:</strong> {order.noigui}</ListGroup.Item>
                                    <ListGroup.Item><strong>Nơi nhận:</strong> {order.noinhan}</ListGroup.Item>
                                    <ListGroup.Item><strong>Ngày tạo:</strong> {format(new Date(order.ngaytao), "dd/MM/yyyy")}</ListGroup.Item>
                                    <ListGroup.Item><strong>Mô tả:</strong> {order.mota}</ListGroup.Item>
                                    <ListGroup.Item>
                                        <strong>Giá trị đơn hàng:</strong> {order.giatridh} VND
                                    </ListGroup.Item>
                                    <ListGroup.Item>
                                        <strong>Trạng thái:</strong>{' '}
                                        <Badge>
                                            {order.trangthai}
                                        </Badge>
                                    </ListGroup.Item>

                                </ListGroup>
                            </Col>
                        </Row>
                        <ListGroup>

                            <ListGroup.Item >
                                <div className="d-flex justify-content-between align-items-center">
                                    <div>
                                    </div>
                                    <div>
                                        <Button variant="success" className="me-2" onClick={(e) => handleBidSubmit(e, order.id, "Hoàn thành")}>
                                            Hoàn thành
                                        </Button>


                                        <Button variant="danger" className="me-2" onClick={(e) => handleBidSubmit(e, order.id, "Hoàn hàng")} >
                                            Hoàn hàng
                                        </Button>

                                     
                                    </div>
                                </div>
                            </ListGroup.Item>

                        </ListGroup>
                    </Card.Body>

                </Card>
            ))}
        </Container>
    );
}
export default DauGiaSuccess;