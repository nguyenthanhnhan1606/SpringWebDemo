import { Alert } from "react-bootstrap";

const Footer = () => {
    return (<>
       <Alert variant="success" className="d-flex justify-content-between align-items-center mt-5">
            <div className="text-muted">NGUYEN THANH NHAN &copy; 2023</div>
            <div>
               Privacy Policy
                &middot;
                Terms &amp; Conditions
            </div>
        </Alert>
    </>)
}

export default Footer;