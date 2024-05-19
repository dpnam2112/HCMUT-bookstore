import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Header } from '../components/header';
import { Footer } from '../components/footer';
import { Link } from 'react-router-dom';
import '../index.css'

function AddBook() {
    const [title, setTitle] = useState('');
    const [bookCover, setBookCover] = useState('');
    const [price, setPrice] = useState('');
    const [remainingQuantity, setRemainingQuantity] = useState('');
    const [authorId, setAuthorId] = useState('');
    const [publisherId, setPublisherId] = useState('');
    const [id, setId] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [authors, setAuthors] = useState([]);
    const [publishers, setPublishers] = useState([]);

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            const newBook = {
                id: -1,
                title,
                bookCover,
                price: parseFloat(price),
                remainingQuantity: parseInt(remainingQuantity),
                authorId: parseInt(authorId),
                publisherId: parseInt(publisherId)
            };
            console.log('New Book Data:', newBook);

            const response = await axios.post('http://localhost:8080/api/add-book', newBook);
            console.log('Response from server:', response.data);

            alert('Thêm sách thành công');
            window.location.href = '/products';
        } catch (error) {
            console.error('Error adding new book:', error);
            if (error.response) {
                console.error('Server Error Data:', error.response.data);
                setErrorMessage(error.response.data.message);
            } else {
                setErrorMessage('Đã xảy ra lỗi khi thêm sách');
            }
        }
    };
  
    useEffect(() => {
      fetch("http://localhost:8080/api/getAuthors")
	.then(response => response.json())
	.then(authors => setAuthors(authors));

      fetch("http://localhost:8080/api/getPublishers")
	.then(response => response.json())
	.then(publishers => setPublishers(publishers));
    }, [])

    return (
        <>
            <Header />
            <div className="container mt-4">
                <h2 className="text-center">Thêm Sách Mới</h2>
                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                <form onSubmit={handleFormSubmit} className="form-custom">
                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="title" className="form-label">Tiêu Đề Sách</label>
                            <input type="text" className="form-control" id="title" value={title} onChange={(e) => setTitle(e.target.value)} required />
                        </div>
                    </div>

                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="bookCover" className="form-label">Đường Dẫn Ảnh Bìa Sách</label>
                            <input type="text" className="form-control" id="bookCover" value={bookCover} onChange={(e) => setBookCover(e.target.value)} required />
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="price" className="form-label">Giá Tiền (VNĐ)</label>
                            <input type="number" className="form-control" id="price" value={price} onChange={(e) => setPrice(e.target.value)} required />
                        </div>
                    </div>

                    <div className="row mb-3">

                        <div className="col-md-6">
                            <label htmlFor="remainingQuantity" className="form-label">Số Lượng Còn Lại</label>
                            <input type="number" className="form-control" id="remainingQuantity" value={remainingQuantity} onChange={(e) => setRemainingQuantity(e.target.value)} required />
                        </div>

                        <div className="col-md-6">
                            <label htmlFor="authorId" className="form-label">Tác Giả</label>
			    <select className="form-select" id="authorId" onChange={ (e) => { setAuthorId(e.target.value) } }>
				{ authors.map(({ id, authorName }) => {
				    return (<option value={ id }>{ authorName }</option>)
				}) }
			    </select>
                        </div>

                    </div>

                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="publisherId" className="form-label">Nhà xuất bản</label>
			    <select className="form-select" id="publisherId" onChange={ (e) => { setPublisherId(e.target.value) } }>
				{ publishers.map(({ id, publisherName }) => {
				    return (<option value={ id }>{ publisherName  }</option>)
				}) }
			    </select>
			</div>
		    </div>
                    <div className="d-flex justify-content-center">
                        <button type="submit" className="btn btn-primary me-3">Thêm Sách</button>
                        <Link to="/products" className="btn btn-secondary">Hủy</Link>
                    </div>
                </form>
            </div>
            <Footer />
        </>
    );
}

export default AddBook;
