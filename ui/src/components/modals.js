import React from 'react';


export function Modal({id, header, message }) {
  return (
    <div className="modal fade" id={id} aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabIndex="-1">
      <div className="modal-dialog modal-dialog-centered">
	<div className="modal-content">
	  <div className="modal-header">
	    <h1 className="modal-title fs-5" id="exampleModalToggleLabel">{ header }</h1>
	    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	  </div>
	  <div className="modal-body">
	    { message }
	  </div>
	</div>
      </div>
    </div>
  );
}

export function OrderSuccessModal({ id }) {
  return (
    <div className="modal fade" id={id} aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabIndex="-1">
      <div className="modal-dialog modal-dialog-centered">
	<div className="modal-content">
	  <div className="modal-header">
	    <h1 className="modal-title fs-5" id="exampleModalToggleLabel">Thành công</h1>
	  </div>
	  <div className="modal-body">
	    Xác nhận đơn hàng thành công.
	  </div>
	  <div className="modal-footer">
	    <a href="/" className="btn btn-primary">Về trang chủ</a>
	  </div>
	</div>
      </div>
    </div>
  );
}

export function OrderFailedModal({ id }) {
  return (
    <div className="modal fade" id={id} aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabIndex="-1">
      <div className="modal-dialog modal-dialog-centered">
	<div className="modal-content">
	  <div className="modal-header">
	    <h1 className="modal-title fs-5" id="exampleModalToggleLabel">Thất bại</h1>
	  </div>
	  <div className="modal-body">
	    Có lỗi xảy ra trong quá trình xác nhận đơn hàng.
	  </div>
	  <div className="modal-footer">
	    <a href="/" className="btn btn-primary">Về trang chủ</a>
	  </div>
	</div>
      </div>
    </div>
  );
}
