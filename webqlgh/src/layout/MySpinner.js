import { Spinner } from "react-bootstrap";

const MySpinner = () => {
    return (
        <div className="d-flex align-items-center justify-content-center spinner-overlay">
            <Spinner animation="grow" variant="info" />
        </div>
    );
}
export default MySpinner;