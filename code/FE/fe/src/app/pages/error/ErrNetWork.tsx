import { Button } from "primereact/button";
import React from "react";
import { useNavigate } from "react-router-dom";

export default function ErrNetWork() {
  const navigate = useNavigate();

  return (
    <div className="d-flex align-items-center justify-content-center vh-100">
      <div className="text-center">
        <h1 className="display-1 fw-bold">500</h1>
        <p className="fs-3">
          {" "}
          <span className="text-danger">Xảy ra lỗi!</span> Lỗi kết nối mạng.
        </p>
        <Button onClick={() => navigate("/pitch")}>Về trang chủ</Button>
      </div>
    </div>
  );
}