import { useContext, useEffect, useState } from "react";
import { Alert, Badge, Button, Card, Col, Container, FormControl, InputGroup, ListGroup, Row } from "react-bootstrap";
import { Navigate, useSearchParams } from "react-router-dom";
import { authApi, endpoints } from "../configs/Apis";
import { MyUserContext } from "../App";
import MySpinner from "../layout/MySpinner";
import { format } from "date-fns";

const ListOrderDg = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [orders, setOrders] = useState(null);
    const [q] = useSearchParams();
    const [formData, setFormData] = useState({
        gia: "",
        idShipper: user.id,
        idDonhang: 0,
    });

    const loadOrder = async () => {
        try {
            let e = `${endpoints['order']}/?trangthai=Đang đấu giá`;
            let search = q.get("search");
            if (search !== null)
                e = `${e}&search=${search}`

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

    const handleBidSubmit = async (e, orderId) => {
        e.preventDefault();
        const formDataWithFile = new FormData();
        for (const property in formData) {
            if (property === "idDonhang")
                formDataWithFile.append(property, orderId);
            formDataWithFile.append(property, formData[property]);
        }
        try {
            const response = await authApi().post(endpoints['addDauGia'], formDataWithFile);

            if (response.status === 201) {
                window.confirm("Đấu giá thành công!!")
                console.log("Dau gia added successfully");
                loadOrder();
            } else {
                // Handle failure or display an error message
                console.error("Failed to add dau gia");
            }
        } catch (error) {
            console.error("Error adding dau gia:", error);
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
                        <ListGroup.Item>
                            <strong className="text-success">Nhập giá đấu:</strong>{' '}
                            <InputGroup className="mt-2">
                                <FormControl
                                    type="number"
                                    placeholder="Nhập giá đấu"
                                    value={formData.gia}
                                    onChange={(e) => setFormData({ ...formData, gia: e.target.value })}
                                />
                                <Button variant="info" onClick={(e) => handleBidSubmit(e, order.id)}>
                                    Đấu giá
                                </Button>
                            </InputGroup>
                        </ListGroup.Item>
                        <h5 className="mt-4">Thông tin đấu giá:</h5>
                        <ListGroup>
                            {order.daugiaSet.filter(daugia => daugia.idShipper.id === user.id)
                            .map(daugia => (
                                <ListGroup.Item key={daugia.id}>
                                    <strong>{daugia.idShipper.user.ten} :</strong> {daugia.gia} VND
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    </Card.Body>
                </Card>
            ))}
        </Container>
    );
}
export default ListOrderDg;