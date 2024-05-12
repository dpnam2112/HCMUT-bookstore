import '../itemproduct.css'
import { Header } from '../components/header'
import { Footer } from '../components/footer'
import { ProductList } from '../components/paging'
import { useLocation } from 'react-router-dom'
import React, { useEffect, useState } from 'react';
import { Modal } from 'bootstrap/dist/js/bootstrap.bundle.js';
import $ from 'jquery';

export function ResultModal({id, header, message }) {

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
	  <div className="modal-footer">
	    <a href="." className="btn btn-primary">OK</a>
	  </div>
	</div>
      </div>
    </div>
  );
}

export const BookDetail = () => {
  const [book, setBook] = useState(null);
  const [publisher, setPublisher] = useState(null);
  const [author, setAuthor] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [showMessage, setShowMessage] = useState(false);
  const [messageContent, setMessageContent] = useState({ success: null, message: null })
  const [modalContent, setModalContent] = useState({ header: null, message: null })

  useEffect(() => {
    fetch('http://localhost:8080/api/bookDetail/1')
      .then(response => response.json())
      .then(data => {
        setBook(data.book);
        setPublisher(data.publisher);
        setAuthor(data.author);
      })
      .catch(error => console.error(error));
  }, []);

  if (!book || !publisher || !author) {
    return <div>Loading...</div>;
  }

  const handleAddToCart = () => {
    let formData = new FormData();

    formData.append("bookId", book.id);
    formData.append("bookCount", quantity);

    fetch("http://localhost:8080/api/addBookToCart", {
      method: "POST",
      mode: "cors",
      credentials: "include",
      body: formData
    }).then(response => {
      setShowMessage(true);

      let modal = new Modal(document.getElementById('resultModal'), { focus: true });

      if (response.ok) {
	setModalContent({ header: "Thành công", message: "Bạn đã thêm hàng vào giỏ hàng thành công." })
      } else if (response.status == 403) {
	setModalContent({ header: "Thất bại", message: "Vui lòng đăng nhập để sử dụng dịch vụ." })
      } else {
	setModalContent({ header: "Thất bại", message: "Có lỗi xảy ra ở phía máy chủ." })
      }

      modal.show();
    })
  };

  const handleQuantityChange = event => {
    const value = parseInt(event.target.value, 10);
    setQuantity(value);
  };

  return (
    <>
      <Header/>
	<div className="container mt-5" style={{ height: '80vh' }}>
	  <div className="row align-items-center">
	    <div className="col-md-6 d-flex justify-content-center">
	      <div style={{ width: '50%' }}>
		<img
		  src={book.bookCover}
		  alt={book.title}
		  style={{ width: '100%', height: 'auto' }}
		/>
	      </div>
	    </div>
	    <div className="col-md-4">
	      <h1 className="font-weight-bold my-2">{book.title}</h1>
	      <h4 className="my-2">Tác giả: {author.authorName}</h4>
	      <h4 className="my-2">Nhà xuất bản: {publisher.publisherName}</h4>
	      <p className="font-weight-bold text-primary my-3">Giá bán: {book.price}đ</p>
	      <div className="row my-2">
		<div className="col-md-4">
		  <label htmlFor="quantity">Số lượng:</label>
		</div>
		<div className="col-md-3">
		  <input
		    type="number"
		    id="quantity"
		    className="form-control"
		    min="1"
		    value={quantity}
		    onChange={handleQuantityChange}
		  />
		</div>
	      </div>
	      <button
		className="btn btn-primary my-3"
		onClick={handleAddToCart}
	      >
		Thêm vào giỏ hàng
	      </button>
	    </div>
	  </div>
	</div>
	<ResultModal id={"resultModal"} header={modalContent.header} message={modalContent.message}/>
      <Footer/>
    </>
  );
};
