import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import { Alert, Card, Col, Container, Row } from "react-bootstrap";
import { format } from "date-fns";
import { Link, useSearchParams } from "react-router-dom";

const Shipper = () => {
    const [shippers, setShippers] = useState(null);
    const [q] = useSearchParams();


    useEffect(() => {
        console.log("useEffect in Shipper component is triggered");
        const loadShipper = async () => {
            try {
                let e = endpoints['shippers'];
                let search = q.get("search");
                if (search !== null)
                    e = `${e}?search=${search}`;
                let res = await Apis.get(e);
                setShippers(res.data);
            }catch(ex){
                console.error(ex);
            }
           
        }
        loadShipper();
    }, [q])

    if (shippers === null)
        return <MySpinner />
    if (shippers.length === 0)
        return <Alert variant="info" className="mt-1 text-center">Không có shipper nào!</Alert>
    return (<>
        <Container className="mt-2">
            <h1 className="text-center text-info">Danh sách Shipper</h1>

            <Row xs={1} md={2} lg={4} className="g-4">
                {shippers.map((shipper) => (
                    <Col key={shipper.id}>
                        <Card>
                            <Card.Img
                                variant="top"
                                style={{ objectFit: "cover", height: "200px" }} // Điều chỉnh kích thước hình ảnh
                                src={shipper.user.avatar}
                            />
                            <Card.Body>
                                <Card.Title className="text-center"> {shipper.user.ten}</Card.Title>
                                <Card.Text>Ngày sinh: {format(new Date(shipper.user.ngaysinh), "dd/MM/yyyy")}</Card.Text>
                                <Card.Text>Trạng thái: {shipper.trangthai}</Card.Text>
                                <Card.Text>
                                    <Link to={`/shippers/${shipper.id}`} className="btn btn-info">
                                        Xem chi tiết
                                    </Link>
                                </Card.Text>
                            </Card.Body>
                        </Card>

                    </Col>
                ))}
            </Row>
        </Container>
    </>)
}

export default Shipper;