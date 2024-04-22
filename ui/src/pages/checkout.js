import React from 'react'
import { useState, useEffect } from 'react'
import { Header } from '../components/header'
import { Footer } from '../components/footer'


const UserProducts = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/cart", {
      credentials: 'include'
    })
      .then(response => response.json())
      .then(data => setProducts(data));
  }, []);

  return (
    <div className="container">
      <h2>Giỏ hàng</h2>
      <div className="list-group">
        {products.map(({ book, count }, index) => (
          <div key={index} className="list-group-item list-group-item-action d-flex align-items-center">
            <img
              src={book.bookCover}
              alt={book.title}
              className="rounded me-3"
              style={{ maxHeight: '100px', maxWidth: '100px', objectFit: 'cover' }}
            />
            <div className="flex-grow-1">
              <h5 className="mb-1 fw-bold">{book.title}</h5>
              <div className="d-flex justify-content-between align-items-center">
                <small className="fw-bold">Số lượng: {count}</small>
              </div>
            </div>
	    <h5 className="fw-bold">{book.price}đ</h5>
          </div>
        ))}
      </div>
    </div>
  );
};

const CheckoutForm = () => {


  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    phoneNumber: '',
    address: '',
    paymentMethod: 'cash'
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    console.log(formData);
    fetch("http://localhost:8080/api/comfirm-order", {
      method: "POST",
      mode: "cors",
      credentials: "include",
      headers: {
	"Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    }).then((response) => {
      if (response.status == 200) {
	console.log("the order is confirmed.");
      } else {
	console.log("the order is not confirmed.");
      }
    });
  };

  return (
      <div className="row justify-content-center">
        <div className="col-md-6">
          <h2 className="mb-4">Thông tin giao hàng</h2>
          <form>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Họ và tên</label>
              <input type="text" className="form-control" id="fullName" name="fullName" placeholder="Nhập họ và tên" onChange={handleChange} />
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="form-label">Địa chỉ email</label>
              <input type="email" className="form-control" id="email" name="email" placeholder="Nhập địa chỉ email" onChange={handleChange} />
            </div>

            <div className="mb-3">
              <label htmlFor="address" className="form-label">Địa chỉ nhà</label>
              <input type="text" className="form-control" id="address" name="address" placeholder="Nhập địa chỉ nhà" onChange={handleChange} />
            </div>

            <div className="mb-3">
              <label htmlFor="phoneNumber" className="form-label">Số điện thoại</label>
              <input type="text" className="form-control" id="phoneNumber" name="phoneNumber" placeholder="Số điện thoại" onChange={handleChange} />
            </div>

	    <div className="mb-3">
	      
	      <h4>Phương thức thanh toán</h4>
    
	      <div className="form-check mb-3">
		<input className="form-check-input" type="radio" name="paymentMethod" id="cash" value="cash" defaultChecked/>
		<label className="form-check-label" htmlFor="cash">
		  Thanh toán qua tiền mặt
		</label>
	      </div>

	      <div className="form-check mb-3">
		<input className="form-check-input" type="radio" name="paymentMethod" id="momo" value="momo"/>
		<label className="form-check-label" htmlFor="momo">
		  Thanh toán qua MoMo
		</label>
	      </div>
	    </div>

            <button type="button" className="btn btn-primary" onClick={handleSubmit}>Submit</button>
          </form>
        </div>
      </div>
  );
};

export const CheckoutPage = () => {
  return (
    <div className="vh-100">
    <Header/>
      <div className="container">
	<h2 className="text-center mb-4">Thanh toán</h2>
	<div className="row">
	  <div className="col-md-6">
	    <UserProducts/>
	  </div>
	  <div className="col-md-6">
	    <CheckoutForm />
	  </div>
	</div>
      </div>
    <Footer/>
    </div>
  );
};

