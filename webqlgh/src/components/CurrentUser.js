import { useContext } from "react";
import { MyUserContext } from "../App";
import { Card, Col, Container, Row } from "react-bootstrap";
import { format } from "date-fns";
import { Link } from "react-router-dom";

const CurrentUser = () => {
    const [user, dispatch] = useContext(MyUserContext);

    return (<>
        <Container className="mt-2">
            <h1 className="text-center text-info">Thông tin chi tiết của bạn</h1>
            <Row>
                <Col md={4}>
                    <Card>
                        <Card.Img
                            variant="top"
                            style={{ objectFit: "cover", height: "350px" }}
                            src={user.avatar}
                        />
                    </Card>
                </Col>
                <Col md={8}>
                    <Card>
                        <Card.Body>
                            <Card.Title>Họ và tên:  {user.ten}</Card.Title>
                            <Card.Text>Ngày sinh: {format(new Date(user.ngaysinh), "dd/MM/yyyy")}</Card.Text>
                            <Card.Text>Giới tính: {user.gioitinh}</Card.Text>
                            <Card.Text>Email: {user.email}</Card.Text>
                            <Card.Text>Số điện thoại: {user.sdt}</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Link to="/" className="btn btn-success mt-2">
                Quay lại
            </Link>
        </Container>
    </>)
}

export default CurrentUser;