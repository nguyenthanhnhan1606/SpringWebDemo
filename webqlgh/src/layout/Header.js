import { useContext, useState } from "react";
import { Container, Form, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [search, setSearch] = useState("");
    const nav = useNavigate();
    const location = useLocation();

    const searchKw = (evt) => {
        evt.preventDefault();
        nav(`${location.pathname}?search=${search}`)
    }

    const logout = () => {
        dispatch({
            "type": "logout"
        })
    }
    if (user !== null && user.userRole === "ROLE_SHIPPER") {
        return (<>
           <Navbar expand="lg" bg="dark" variant="dark" sticky="top">
                <Container>
                    <Navbar.Brand href="#home">Quản lý giao hàng</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Trang chủ</Nav.Link>
                            <Nav.Link as={Link} to="/listorder"> Đơn hàng mới</Nav.Link>
                            <Nav.Link as={Link} to="/listorderdg">Đơn đang đấu giá</Nav.Link>
                            <NavDropdown title="Đơn hàng của bạn" id="basic-nav-dropdown">
                                <NavDropdown.Item as={Link} to={`/lsdaugia`}>Đã đấu giá</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`/daugiasucces`}>Đấu giá thành công</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`/lsdaugia/?trangthai=Hoàn thành`}>Hoàn thành</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`/lsdaugia/?trangthai=Hoàn hàng`}>Hoàn hàng</NavDropdown.Item>
                            </NavDropdown>
                            
                            <Nav.Link as={Link} to="/shippers">Xem các shipper</Nav.Link>

                            {/* <Nav.Link href="#link">Đăng nhập</Nav.Link>
                            <Nav.Link href="#link">Đăng ký</Nav.Link> */}
                        </Nav>
                        <Nav className="ml-auto">
                            <Form onSubmit={searchKw} inline>
                                <Form.Control
                                    type="text"
                                    placeholder="Tìm kiếm"
                                    name="search"
                                    className="mr-sm-2 mt-2"
                                    onChange={e => setSearch(e.target.value)}
                                    style={{
                                        borderRadius: "0.25rem",
                                        border: "1px solid #ccc",
                                        backgroundColor: "#f7f7f7",
                                        color: "#333",
                                        padding: "0.25rem 0.5rem", // Điều chỉnh khoảng cách nội dung bên trong
                                        height: "28px",
                                        width: "250px", // Điều chỉnh chiều cao của ô tìm kiếm
                                        fontSize: "14px" // Điều chỉnh kích thước chữ
                                    }}
                                />
                            </Form>
                            {user === null ? <>
                                <Link className="nav-link text-danger" to="/login">Đăng nhập</Link>
                                <Link className="nav-link text-info" to="/register">Đăng ký</Link>
                            </> : <>
                                <NavDropdown title={`Chào, ${user.ten}`} id="navbarDropdown">
                                    <NavDropdown.Item as={Link} to="/current-user">
                                        <img src={user.avatar} width="40" alt="Avatar" />
                                        <span className="text-info">Thông tin</span>

                                    </NavDropdown.Item>
                                    <NavDropdown.Item>
                                        <Link className="nav-link text-danger text-center" to="/" onClick={logout}>Đăng xuất</Link>
                                    </NavDropdown.Item>
                                </NavDropdown>

                            </>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>)
    } else {
        return (<>
            <Navbar expand="lg" bg="dark" variant="dark" sticky="top">
                <Container>
                    <Navbar.Brand href="#home">Quản lý giao hàng</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Trang chủ</Nav.Link>
                            <Nav.Link as={Link} to="/createorder">Tạo đơn hàng</Nav.Link>
                            <NavDropdown title="Đơn hàng của bạn" id="basic-nav-dropdown">
                                <NavDropdown.Item as={Link} to="/order">Tất cả</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`order/?trangthai=Mới tạo`}>Chờ dấu giá</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`order/?trangthai=Đang đấu giá`}>Chờ shipper</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`order/?trangthai=Đang giao`}>Đang giao</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`order/?trangthai=Hoàn thành`}>Đã hoàn thành</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to={`order/?trangthai=Hoàn hàng`}>Hoàn hàng</NavDropdown.Item>
                            </NavDropdown>
                            <Nav.Link as={Link} to="/shippers">Xem các shipper</Nav.Link>
                            <Nav.Link as={Link} to="/registersp">Đang ký shipper</Nav.Link>

                            {/* <Nav.Link href="#link">Đăng nhập</Nav.Link>
                            <Nav.Link href="#link">Đăng ký</Nav.Link> */}


                        </Nav>
                        <Nav className="ml-auto">
                            <Form onSubmit={searchKw} inline>
                                <Form.Control
                                    type="text"
                                    placeholder="Tìm kiếm"
                                    name="search"
                                    className="mr-sm-2 mt-2"
                                    onChange={e => setSearch(e.target.value)}
                                    style={{
                                        borderRadius: "0.25rem",
                                        border: "1px solid #ccc",
                                        backgroundColor: "#f7f7f7",
                                        color: "#333",
                                        padding: "0.25rem 0.5rem", // Điều chỉnh khoảng cách nội dung bên trong
                                        height: "28px",
                                        width: "250px", // Điều chỉnh chiều cao của ô tìm kiếm
                                        fontSize: "14px" // Điều chỉnh kích thước chữ
                                    }}
                                />
                            </Form>
                            {user === null ? <>
                                <Link className="nav-link text-danger" to="/login">Đăng nhập</Link>
                                <Link className="nav-link text-info" to="/register">Đăng ký</Link>
                            </> : <>
                                <NavDropdown title={`Chào, ${user.ten}`} id="navbarDropdown">
                                    <NavDropdown.Item as={Link} to="/current-user">
                                        <img src={user.avatar} width="40" alt="Avatar" />
                                        <span className="text-info">Thông tin</span>

                                    </NavDropdown.Item>
                                    <NavDropdown.Item>
                                        <Link className="nav-link text-danger text-center" to="/" onClick={logout}>Đăng xuất</Link>
                                    </NavDropdown.Item>
                                </NavDropdown>

                            </>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>)
    }
}
export default Header;