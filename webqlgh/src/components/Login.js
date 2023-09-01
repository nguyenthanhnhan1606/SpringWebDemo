import { useContext, useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { Link, Navigate } from "react-router-dom";
import Apis, { authApi, endpoints } from "../configs/Apis";
import { MyUserContext } from "../App";
import cookie from "react-cookies";
import { signInWithPopup, FacebookAuthProvider } from "firebase/auth";
import { auth, provider, providerGg } from "../configs/FireBaseConfig";



const Login = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [errorMessage, setErrorMessage] = useState("");
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    const [profilePicture, setProfilePicture] = useState(null);


    const login = (e) => {
        e.preventDefault();
        const process = async () => {
            if (username == null || password == null)
                setErrorMessage("Bạn chưa nhập tài khoản hoặc mật khẩu!!");
            else {
                let res = await Apis.post(endpoints['login'], {
                    "taikhoan": username,
                    "matkhau": password
                })
                if (res.data === "error") {
                    setErrorMessage("Tài khoản hoặc mật khẩu không chính xác!!");
                } else {
                    cookie.save("token", res.data);

                    let { data } = await authApi().get(endpoints['current-user']);
                    cookie.save("user", data);

                    dispatch({
                        "type": "login",
                        "payload": data
                    });
                }

            }
        }
        process();
    }

    const handleFacebookLogin = () => {
        signInWithPopup(auth, provider).then((result) => {
            cookie.save("user", result.user);
            dispatch({
                "type": "login",
                "payload": result.user
            });
            // This gives you a Facebook Access Token. You can use it to access the Facebook API.
            const credential = FacebookAuthProvider.credentialFromResult(result);
            const accessToken = credential.accessToken;
            // fetch facebook graph api to get user actual profile picture
            fetch(`https://graph.facebook.com/${result.user.providerData[0].uid}/picture?type=large&access_token=${accessToken}`)
                .then((response) => response.blob())
                .then((blob) => {
                    setProfilePicture(URL.createObjectURL(blob));
                })
        }).catch((err) => {
            console.log(err);
        })
    }
    const handleGoogleSignIn = () => {
        signInWithPopup(auth, providerGg).then((result) => {
            const user1 = result.user;
            console.log(user1);
            cookie.save("user", user1);
            dispatch({
                "type": "login",
                "payload": user1
            });
        }).catch((err) => {
            console.log(err);
        })
    }
    if (user !== null)
        return <Navigate to="/" />

    return (<>
        <Container className="mt-2">
            <Row className="justify-content-center">
                <Col xs={12} md={6}>
                    <h2 className="mt-2 text-center text-info">Đăng nhập</h2>
                    {errorMessage && <p className="text-danger">{errorMessage}</p>}
                    <Form className="mt-3" onSubmit={login}>
                        <Form.Group className="mb-3 form-floating" controlId="username">
                            <Form.Control type="text" placeholder=" " value={username} onChange={(e) => setUsername(e.target.value)} />
                            <Form.Label>Nhập tài khoản...</Form.Label>
                        </Form.Group>

                        <Form.Group className="mb-3 form-floating" controlId="password">
                            <Form.Control type="password" placeholder=" " value={password} onChange={(e) => setPassword(e.target.value)} />
                            <Form.Label>Nhập mật khẩu...</Form.Label>
                        </Form.Group>

                        <Button variant="primary" type="submit" className="w-100 mt-2">
                            Đăng nhập
                        </Button>

                        <Row className="justify-content-center mt-2">
                            <Col xs="auto">
                                <Button variant="primary" className="btn-md" onClick={handleFacebookLogin}>
                                    Đăng nhập bằng Facebook
                                </Button>
                            </Col>
                            <Col xs="auto">
                                <Button variant="danger" className="btn-md" onClick={handleGoogleSignIn}>
                                    Đăng nhập bằng Google
                                </Button>
                            </Col>
                        </Row>
                        <div className="text-center mt-3">
                            <p className="mb-0">Bạn không có tài khoản?<Link to="/register"> Đăng ký</Link></p>
                        </div>

                    </Form>
                </Col>
            </Row>
        </Container>
    </>)
}
export default Login;