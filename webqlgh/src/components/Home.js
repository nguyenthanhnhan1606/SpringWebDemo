import { Carousel, Col, Container, Row } from "react-bootstrap";

const Home = () => {
    return (<>
        <div className="mt-1">
            <div>
                <Carousel>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://file.hstatic.net/200000472237/file/1920x700_a07ed859d3b849f19756bc3a1c2c1165.png"
                            alt="Hình ảnh 1"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://file.hstatic.net/200000472237/file/ghn-fullfilment_383eda5c46494f2d_676fe52e6c884663aefa95b484b641f6.jpg"
                            alt="Hình ảnh 1"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://file.hstatic.net/200000472237/file/slider-4-desk_afc9605d1a184f078587528caeb43905.jpg"
                            alt="Hình ảnh 1"
                        />
                    </Carousel.Item>
                </Carousel>
            </div>

        </div>
        <div className="mt-3">
            <h3 className="text-center text-dark">DÙNG BAO NHIÊU TRẢ BẤY NHIÊU</h3>
            <h5 className="text-center">Bạn chỉ phải trả một khoản phí rất nhỏ trên mỗi đơn hàng giao thành công</h5>
        </div>

        <Container className="hidden-sm hidden-xs">
            <video
                playsInline
                autoPlay
                loop
                muted
                preload="auto"
                id="video-desk"
                poster="https://file.hstatic.net/200000472237/file/video-banner_b16761a3ebde4346a2ce5dd8e661fc8c.jpg"
                className="img-fluid" // Sử dụng lớp img-fluid để điều chỉnh kích thước video
            >
                <source
                    type="video/mp4"
                    src="https://file.hstatic.net/1000376681/file/1920x900_d8d881b358674809a162eb6b6069ff10.mp4"
                />
            </video>

            <section className="section section-policy">
                <Container>
                    <Row>
                        <Col md={3} sm={6} xs={6} className="col-policy">
                            <div className="box-polic">
                                <div className="ic-policy d-flex justify-content-center ">
                                    <img
                                        width="50"
                                        height="50"
                                        className="dt-width-auto"
                                        src="//theme.hstatic.net/200000472237/1001083717/14/icon-app1_small.png?v=504"
                                        alt="Top công ty giao nhận hàng đầu VN"
                                    />
                                </div>
                                <div className="desc-policy">
                                    <p className="text-center">Top công ty giao nhận hàng đầu VN</p>
                                </div>
                            </div>
                        </Col>
                        <Col md={3} sm={6} xs={6} className="col-policy">
                            <div className="box-policy">
                                <div className="ic-policy d-flex justify-content-center">
                                    <img
                                        width="50"
                                        height="50"
                                        className="dt-width-auto"
                                        src="//theme.hstatic.net/200000472237/1001083717/14/icon-app2_small.png?v=504"
                                        alt="Mạng lưới phủ sóng 100% 63 tỉnh thành"
                                    />
                                </div>
                                <div className="desc-policy">
                                    <p className="text-center">Mạng lưới phủ sóng 100% 63 tỉnh thành</p>
                                </div>
                            </div>
                        </Col>
                        <Col md={3} sm={6} xs={6} className="col-policy">
                            <div className="box-policy">
                                <div className="ic-policy d-flex justify-content-center">
                                    <img
                                        width="50"
                                        height="50"
                                        className="dt-width-auto"
                                        src="//theme.hstatic.net/200000472237/1001083717/14/icon-app3_small.png?v=504"
                                        alt="Giao hàng nhanh Tỷ lệ hoàn hàng thấp"
                                    />
                                </div>
                                <div className="desc-policy">
                                    <p className="text-center">Giao hàng nhanh Tỷ lệ hoàn hàng thấp</p>
                                </div>
                            </div>
                        </Col>
                        <Col md={3} sm={6} xs={6} className="col-policy">
                            <div className="box-policy">
                                <div className="ic-policy d-flex justify-content-center">
                                    <img
                                        width="50"
                                        height="50"
                                        className="dt-width-auto"
                                        src="//theme.hstatic.net/200000472237/1001083717/14/icon-app4_small.png?v=504"
                                        alt="Hệ thống quản lý trực tuyến 24/7"
                                    />
                                </div>
                                <div className="desc-policy">
                                    <p className="text-center">Hệ thống quản lý trực tuyến 24/7</p>
                                </div>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </section>
        </Container>
    </>)

}
export default Home;