import { Button, Container, Form } from "react-bootstrap";
import { authApi, endpoints } from "../configs/Apis";
import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import MySpinner from "../layout/MySpinner";

const CreateOrder = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const nav = useNavigate();
    const [file, setFile] = useState(null); // Sửa ở đây, ban đầu file là null
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [khuyenmais, setKhuyenmais] = useState();
    const [idKhuyenmai, setIdKhuyenmai]=useState();

    const [formData, setFormData] = useState({
        noigui: "",
        noinhan: "",
        mota: "",
        ghichu: "",
        giatridh: "",
        trangthai: "Mới tạo",
        idUser:0
    });
    useEffect(() => {
        const loadpromotion = async () => {
            try {
                let res = await authApi().get(endpoints['getPromotion']);
                setKhuyenmais(res.data);
            } catch (ex) {
                console.error(ex);
            }

        }
        loadpromotion();
        if (user === null)
            return nav("/login");
        if (successMessage) {
            const userConfirm = window.confirm("Đã tạo đơn hàng thành công. Bạn có muốn chuyển trang không?");
            if (userConfirm)
                nav("/");

        }
    }, [successMessage, nav]);
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleFileChange = (event) => {
        setFile(event.target.files[0]); // Cập nhật file khi người dùng chọn tệp
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.noigui || !formData.noinhan || !formData.mota || !formData.giatridh || !file) {
            setErrorMessage("Vui lòng điền đầy đủ thông tin vào các trường bắt buộc và chọn tệp.");
            return;
        }

        setErrorMessage("");

        const formDataWithFile = new FormData();
        formDataWithFile.append("file", file); 
        formDataWithFile.append("idKhuyenmai",idKhuyenmai)// Thêm tệp vào FormData
        for (const property in formData) {
            if (property === "idUser")
                formDataWithFile.append("idUser", user.id);
            formDataWithFile.append(property, formData[property]);
        }

        try {
            const response = await authApi().post(endpoints['addOrder'], formDataWithFile);
            console.log("Response data:", response.data);


            if (response.status === 200)
                setSuccessMessage("Đã tạo đơn hàng thành công!");
            // Xử lý sau khi thêm đơn hàng thành công
        } catch (error) {
            console.error("Error:", error);
            // Xử lý lỗi nếu có
        }
    };


    if (khuyenmais === null || !khuyenmais)
        return <MySpinner />


    return (<>
        <Container className="mt-2">
            <div>
                <h1 className="text-center text-info">Tạo đơn hàng</h1>
                {successMessage && <p className="text-success">{successMessage}</p>}
                {errorMessage && <p className="text-danger">{errorMessage}</p>}
                <Form >
                    <Form.Group controlId="noigui">
                        <Form.Label>Nơi gửi</Form.Label>
                        <Form.Control
                            type="text"
                            name="noigui"
                            value={formData.noigui}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="noinhan">
                        <Form.Label>Nơi nhận</Form.Label>
                        <Form.Control
                            type="text"
                            name="noinhan"
                            value={formData.noinhan}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="mota">
                        <Form.Label>Mô tả</Form.Label>
                        <Form.Control
                            type="text"
                            name="mota"
                            value={formData.mota}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="ghichu">
                        <Form.Label>Ghi chú</Form.Label>
                        <Form.Control
                            type="text"
                            name="ghichu"
                            value={formData.ghichu}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <Form.Group controlId="giatridh">
                        <Form.Label>Gía trị đơn hàng</Form.Label>
                        <Form.Control
                            type="number"
                            name="giatridh"
                            value={formData.giatridh}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    {/* Thêm các trường khác tương tự */}

                    <Form.Group controlId="file">
                        <Form.Label>File</Form.Label>
                        <Form.Control
                            type="file"
                            name="file"
                            onChange={handleFileChange}
                        />
                    </Form.Group>

                    <Form.Control className="mt-2" as="select" name="idKhuyenmai" value={idKhuyenmai} onChange={(e)=>setIdKhuyenmai(e.target.value)}>
                        <option value="">Chọn khuyến mãi</option>
                        {khuyenmais.map((km) => (
                            <option key={km.id} value={km.id}>{km.mota}</option>
                        ))}
                    </Form.Control>


                    <Button variant="primary" className="mt-2" type="submit" onClick={handleSubmit}>
                        Thêm đơn hàng
                    </Button>

                    <Link style={{ marginLeft: '5px' }} className="btn btn-danger mt-2" to={"/"}>
                        Quay lại
                    </Link>
                </Form>
            </div>
        </Container>
    </>)
}
export default CreateOrder;