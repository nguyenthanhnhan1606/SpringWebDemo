import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { Badge, Button, Card, Col, Container, Form, ListGroup, Row } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import { format } from "date-fns";
import { MyUserContext } from "../App";


const ShipperDetail = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const { id } = useParams();
    const nav = useNavigate();
    const [rating,setRating] =useState();
    const [shipper, setShipper] = useState(null);
    const [comments, setComments] = useState();
    const [newComment, setNewComment] = useState("");
    const [danhgia, setDanhgia] = useState("");

    const loadRating = async () => {
        try {
            const response = await Apis.get(`${endpoints.rating}/${id}`); // Sử dụng endpoint 'comment' thay vì 'comment'
            setRating(response.data); // Cập nhật danh sách bình luận từ API
        } catch (error) {
            console.error("Error loading comments:", error);
        }
    };
    const loadBinhluan = async () => {
        try {
            const response = await Apis.get(`${endpoints.comment}/${id}`); // Sử dụng endpoint 'comment' thay vì 'comment'
            setComments(response.data); // Cập nhật danh sách bình luận từ API
        } catch (error) {
            console.error("Error loading comments:", error);
        }
    };


    useEffect(() => {
        const loadShipperDetail = async () => {
            try {
                const response = await Apis.get(`${endpoints.shippers}/${id}`);
                setShipper(response.data);
            } catch (error) {
                console.error("Error loading shipper detail:", error);
            }
        };
        loadShipperDetail();
        loadBinhluan();
        loadRating();
    }, [id]);

    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        if (user === null) {
            const userConfirm = window.confirm("Bạn hãy đăng nhập trước khi bình luận!");
            if (userConfirm)
                nav("/login");
        }

        try {
            const formData = new FormData();
            formData.append("idUser", user.id);
            formData.append("idShipper", parseInt(id));
            formData.append("danhgia", danhgia ? danhgia : 0);
            formData.append("noidung", newComment);
            const response = await Apis.post(`${endpoints.comment}`, formData)
            loadBinhluan();
            // Nếu thêm bình luận thành công, cập nhật danh sách bình luận và đặt newComment về rỗng
            // setComments([...comments, response.data]);
            // setNewComment("");
        } catch (error) {
            console.error("Error adding comment:", error);
        }
    };

    if (!comments)
        return <MySpinner />;
    if (!shipper) {
        return <MySpinner />;
    }

    return (<>
        <Container className="mt-2">
            <h1 className="text-center text-info">Thông tin chi tiết của {shipper.user.ten}</h1>
            <Row>
                <Col md={4}>
                    <Card>
                        <Card.Img
                            variant="top"
                            style={{ objectFit: "cover", height: "350px" }}
                            src={shipper.user.avatar}
                        />
                    </Card>
                </Col>
                <Col md={8}>
                    <Card>
                        <Card.Body>
                            <Card.Title>Shipper:  {shipper.user.ten}</Card.Title>
                            <Card.Text>Ngày sinh: {format(new Date(shipper.user.ngaysinh), "dd/MM/yyyy")}</Card.Text>
                            <Card.Text>Giới tính: {shipper.user.gioitinh}</Card.Text>
                            <Card.Text>Email: {shipper.user.email}</Card.Text>
                            <Card.Text>Số điện thoại: {shipper.user.sdt}</Card.Text>
                            <Card.Text>Đánh giá: <strong>{rating}</strong>  <Badge  bg="warning">
                                                Sao
                                            </Badge> </Card.Text>
                            <Form className="mt-3" onSubmit={handleCommentSubmit}>
                                <Form.Group controlId="rating">
                                    <Form.Label>Đánh giá:</Form.Label>
                                    <Form.Control
                                        type="number"
                                        min="1"
                                        max="5"
                                        value={danhgia}
                                        onChange={(e) => setDanhgia(e.target.value)}
                                    />
                                    <Button variant="primary" className="mt-2" type="submit" >
                                        Đánh giá
                                    </Button>
                                </Form.Group>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Bình luận:</Card.Title>
                            <ListGroup>
                                {comments.map(comment => (
                                    <ListGroup.Item key={comment.id}>
                                        <strong>{comment.idUser.ten}:</strong> {comment.noidung}
                                    </ListGroup.Item>
                                ))}
                            </ListGroup>
                            <Form className="mt-3" onSubmit={handleCommentSubmit}>
                                <Form.Group controlId="comment">
                                    <Form.Control
                                        as="textarea"
                                        rows={3}
                                        placeholder="Viết bình luận..."
                                        value={newComment}
                                        onChange={(e) => setNewComment(e.target.value)}
                                    />
                                    <div className="mt-2">
                                        <Button variant="primary" type="submit" >
                                            Đăng bình luận
                                        </Button>
                                        {/* Thêm nút khác ở đây nếu cần */}
                                    </div>
                                </Form.Group>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Link to="/shippers" className="btn btn-success mt-2">
                Quay lại
            </Link>
        </Container>

    </>

    );
};

export default ShipperDetail;