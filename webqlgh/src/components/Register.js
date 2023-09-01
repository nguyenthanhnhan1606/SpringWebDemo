import React, { useState } from 'react';
import { Form, Button, Container, Col, Row } from 'react-bootstrap';
import Apis, { endpoints } from '../configs/Apis';
import { Link, useNavigate } from 'react-router-dom';

const Register = () => {
    const nav = useNavigate();
    const [avatar, setAvatar] = useState(null); // Sửa ở đây, ban đầu file là null
    const [errorMessage, setErrorMessage] = useState("");
    const [formData, setFormData] = useState({
        taikhoan: '',
        matkhau: '',
        ten: '',
        cmnd: '',
        email: '',
        sdt: '',
        ngaysinh: '',
        gioitinh: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevValues) =>
            ({ ...prevValues, [name]: value }));
    };

    const handleFileChange = (event) => {
        setAvatar(event.target.files[0]); // Cập nhật file khi người dùng chọn tệp
    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.taikhoan) {
            setErrorMessage("Vui lòng điền đầy đủ thông tin vào các trường bắt buộc và chọn tệp.");
            return;
        }

        setErrorMessage("");

        const formDataWithFile = new FormData();
        formDataWithFile.append("avatar", avatar);
        for (const property in formData) {
            formDataWithFile.append(property, formData[property]);
        }


        try {
            const response = await Apis.post(endpoints['register'], formDataWithFile);
            if (response.status === 201) {
                nav('/login');
            } else {
                setErrorMessage("Tên tài khoản đã có người sử dụng!");
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <Container>
            <Row className="justify-content-center">
                <Col xs={12} md={6}>
                    <Form>
                        <h1 className="text-center text-info">Đăng ký tài khoản</h1>
                        <h6>(<span className="text-danger">*</span>) Không được để trống</h6>
                        {errorMessage && <p className="text-danger">{errorMessage}</p>}
                        <Form.Group controlId="taikhoan">
                            <Form.Label>Tài khoản <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="text"
                                name="taikhoan"
                                value={formData.taikhoan}
                                onChange={handleInputChange}
                            />
                        </Form.Group>

                        <Form.Group controlId="matkhau">
                            <Form.Label>Mật khẩu <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="password"
                                name="matkhau"
                                value={formData.matkhau}
                                onChange={handleInputChange}
                            />
                        </Form.Group>

                        <Form.Group controlId="ten">
                            <Form.Label>Tên <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="text"
                                name="ten"
                                value={formData.ten}
                                onChange={handleInputChange}
                            />
                        </Form.Group>

                        <Form.Group controlId="cmnd">
                            <Form.Label>CMND</Form.Label>
                            <Form.Control
                                type="text"
                                name="cmnd"
                                value={formData.cmnd}
                                onChange={handleInputChange}
                            />
                        </Form.Group>

                        <Form.Group controlId="email">
                            <Form.Label>Email <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="text"
                                name="email"
                                value={formData.email}
                                onChange={handleInputChange}
                            />
                        </Form.Group>

                        <Form.Group controlId="sdt">
                            <Form.Label>Số điện thoại <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="text"
                                name="sdt"
                                value={formData.sdt}
                                onChange={handleInputChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="ngaysinh">
                            <Form.Label>Ngày sinh</Form.Label>
                            <Form.Control
                                type="date"
                                name="ngaysinh"
                                value={formData.ngaysinh}
                                onChange={handleInputChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="gioitinh">
                            <Form.Label>Giới tính</Form.Label>
                            <Form.Select
                                name="gioitinh"
                                value={formData.gioitinh}
                                onChange={handleInputChange}
                            >
                                <option value="">Chọn giới tính</option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                                <option value="Khác">Khác</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group controlId="avatar">
                            <Form.Label>Hình đại diện <span className="text-danger">*</span></Form.Label>
                            <Form.Control
                                type="file"
                                name="avatar"
                                onChange={handleFileChange}
                            />
                        </Form.Group>

                        <Button variant="primary" className="mt-2" type="submit" onClick={handleSubmit}>
                            Đăng ký
                        </Button>

                        <Button variant="secondary" className="mt-2" as={Link} to="/" >
                            Quay lại
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default Register;
