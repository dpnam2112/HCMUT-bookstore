import React, { useState, useEffect } from 'react';
import { Header } from "../components/header.js";
import { Footer } from "../components/footer.js";


export const CartPage = () => {
  const [products, setProducts] = useState([]);
  const [changeQuantity, setChangeQuantity] = useState(false);

  useEffect(() => {
    fetch("http://localhost:8080/api/cart", {
      credentials: 'include'
    })
      .then(response => response.json())
      .then(data => setProducts(data));
  }, []);

  const handleQuantityChange = (itemId, newQuantity) => {
    setProducts(prevItems =>
      prevItems.map(item =>
        item.id === itemId ? { ...item, quantity: newQuantity } : item
      )
    );
  };

  return (
    <>
    <Header/>
      <div className="container d-flex flex-column align-items-center">
	<h2>Giỏ hàng</h2>
	<div className="list-group" style={{ width: "45vw" }}>
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
		<div className="d-flex align-items-center">
		  <small className="fw-bold mx-2">Số lượng:</small>
		  <input type="number" value={count} onChange={ e => handleQuantityChange(book.id, e.target.value) }/>
		</div>
	      </div>
	      <h5 className="fw-bold">{book.price}đ</h5>
	    </div>
	  ))}
	</div>
	<button className="btn btn-primary my-2" disabled={!changeQuantity}>Lưu số lượng sản phẩm</button>
      </div>
    <Footer/>
    </>
  );
};

export default CartPage;
