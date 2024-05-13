import React, { useState, useEffect } from 'react';
import { Header } from "../components/header.js";
import { Footer } from "../components/footer.js";
import { Modal } from 'bootstrap/dist/js/bootstrap.bundle.js';
import { Modal as ModalComponent } from '../components/modals.js';

const ConfirmModal = () => {
}

export const CartPage = () => {
  const [products, setProducts] = useState([]);
  const [changeQuantity, setChangeQuantity] = useState(false);
  const [quantityChanged, setQuantityChanged ] = useState(false);
  const [{ title, message }, setModalContent ] = useState({ title: null, message: null } );

  useEffect(() => {
    fetch("http://localhost:8080/api/cart", {
      credentials: 'include'
    })
      .then(response => response.json())
      .then(data => setProducts(data));
  }, []);

  const handleQuantityChange = (e, index) => {
    const newQuantity = e.target.value;

    const newProducts = products.map(({ book, count }, i) => {
      if (i == index) {
	count = newQuantity;
      } 

      return { book, count };
    });

    setProducts(newProducts);
    setQuantityChanged(true);
  }

  const saveCartChange = () => {
    const payload = {
      bookIds: products.map(({ book, count }) => book.id),
      bookCounts: products.map(({ book, count }) => count)
    };

    if (payload.bookIds.length == 0) {
      setModalContent({ title: "Thất bại :(", message: "Giỏ hàng trống." })
      let modal = new Modal(document.getElementById('confirmResult'), { focus: true });
      modal.show();
      return;
    }

    fetch("http://localhost:8080/api/updateCart", {
      method: "POST",
      credentials: 'include',
      mode: 'cors',
      headers: {
	"Content-Types": "application/json",
      },
      body: JSON.stringify(payload)
    }).then(response => {
      if (response.ok) {
	setModalContent({ title: "Thành công!", message: "Thay đổi của bạn đã được lưu." })

	const newProducts = products.filter(({ book, count }) => count != 0);
	setProducts(newProducts);
	setQuantityChanged(false);
      } else if (response.status == 403) {
	setModalContent({ title: "Thất bại :(", message: "Vui lòng đăng nhập để sử dụng dịch vụ." })
      } else {
	setModalContent({ title: "Thất bại :(", message: "Có lỗi xảy ra. Vui lòng thử lại sau." })
      }

      let modal = new Modal(document.getElementById('confirmResult'), { focus: true });
      modal.show();
    });
  }

  return (
    <>
    <Header/>
      <div className="container" style={{ minHeight: "80vh" }}>
	<h1 className="text-center my-3">Giỏ hàng</h1>
	{products.map(({ book, count }, index) => (
	  <div className="container d-flex flex-row justify-content-between border my-2 py-1" style={{ width: "65%" }}>
	    <div className="d-flex flex-row">
	      <img src={book.bookCover} alt={book.bookCover} style={{ maxHeight: "100px" }} className="img-fluid" />
	      <div className="d-flex flex-column justify-content-center mw-60">
		<h5>{book.title}</h5>
		<div className="my-2 d-inline-block">
		  <p className="d-inline-block">Số lượng</p>
		  <input type="number" min="0" max="20" value={count} onChange={e => handleQuantityChange(e, index)} className="mx-2" style={ { width: "70px" } } />
		</div>
	      </div>
	    </div>
	    <div className="d-flex flex-column justify-content-center">
	      <h3>{ book.price }đ</h3>
	    </div>
	  </div>
	))}

	<div className="d-flex justify-content-center my-3">
	  <button disabled={!quantityChanged} className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmModal">Lưu giỏ hàng</button>
	</div>

	<div className="modal fade" id="confirmModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabIndex="-1">
	  <div className="modal-dialog modal-dialog-centered">
	    <div className="modal-content">
	      <div className="modal-header">
		<h1 className="modal-title fs-5" id="exampleModalToggleLabel">Xác nhận</h1>
		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div className="modal-body">
		Bạn có muốn lưu thay đổi?
	      </div>
	      <div className="modal-footer">
		<button className="btn btn-primary" data-bs-dismiss="modal" onClick={ e => saveCartChange() }>Xác nhận</button>
	      </div>
	    </div>
	  </div>
	</div>

	<ModalComponent id={"confirmResult"} header={title} message={message}/>
    </div>
    <Footer/>
    </>
  );
};

export default CartPage;
