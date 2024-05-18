import { Header } from '../components/header';
import { Footer } from '../components/footer';
import React, { useState } from 'react';
import axios from 'axios';
import '../index.css';

function ModifyInfo() {
    const [action, setAction] = useState(null);
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setErrorMessage('');
        setSuccessMessage('');
        if (name === 'id') {
            setId(value);
        } else if (name === 'name') {
            setName(value);
        }
    };

    const handleCancel = () => {
        setAction(null);
        setErrorMessage('');
        setSuccessMessage('');
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage('');
        setSuccessMessage('');

        let url;
        let payload;

        if (action === 'author') {
            url = 'http://localhost:8080/api/new-author';
            payload = { id: parseInt(id), authorName: name };
        } else if (action === 'publisher') {
            url = 'http://localhost:8080/api/new-publisher';
            payload = { id: parseInt(id), publisherName: name };
        }

        console.log('Payload:', payload);
        console.log('URL:', url);

        try {
            const response = await axios.post(url, payload);
            console.log('Response from server:', response.data);
            setSuccessMessage(`${action === 'author' ? 'Thêm tác giả' : 'Thêm nhà xuất bản'} thành công`);
            setId('');
            setName('');
            setAction(null);
        } catch (error) {
            console.error(`Error adding new ${action}:`, error);
            if (error.response) {
                console.error('Server Error Data:', error.response.data);
                setErrorMessage(error.response.data.message || 'Đã xảy ra lỗi khi thêm mới');
            } else {
                setErrorMessage('Đã xảy ra lỗi khi thêm mới');
            }
        }
    };

    return (
        <>
            <Header />
            <div className="container mt-4">
                <h2 className="text-center">Thêm Thông Tin</h2>
                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                {successMessage && <div className="alert alert-success">{successMessage}</div>}
                {action === null ? (
                    <div className="d-flex justify-content-center mt-4">
                        <button
                            onClick={() => { setAction('author'); setErrorMessage(''); setSuccessMessage(''); }}
                            className="btn btn-primary btn-lg me-3"
                        >
                            Thêm Tác Giả
                        </button>
                        <button
                            onClick={() => { setAction('publisher'); setErrorMessage(''); setSuccessMessage(''); }}
                            className="btn btn-secondary btn-lg ms-3"
                        >
                            Thêm Nhà Xuất Bản
                        </button>
                    </div>
                ) : (
                    <form onSubmit={handleFormSubmit} className="form-custom mt-4">
                        <div className="row mb-3">
                            <div className="col-md-6">
                                <label htmlFor="id" className="form-label">ID</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="id"
                                    name="id"
                                    value={id}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>
                            <div className="col-md-6">
                                <label htmlFor="name" className="form-label">
                                    {action === 'author' ? 'Tên Tác Giả' : 'Tên Nhà Xuất Bản'}
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="name"
                                    name="name"
                                    value={name}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>
                        </div>
                        <div className="d-flex justify-content-center">
                            <button type="submit" className="btn btn-success me-3">Xác nhận</button>
                            <button type="button" className="btn btn-danger ms-3" onClick={handleCancel}>Hủy bỏ</button>
                        </div>
                    </form>
                )}
            </div>
            <Footer />
        </>
    );
}

export default ModifyInfo;
