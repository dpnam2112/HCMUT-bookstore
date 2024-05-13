import React from 'react';
import { useState } from 'react';

export function ProductList({ products }) {
    const [curPage, setCurPage] = useState(1);
    const [bookData, setBookData] = useState([]);
    const booksPerPage = 12;
    const totalPage = 4; // Thay đổi số trang tùy theo nhu cầu của bạn

    const changePage = (newPage) => {
        setCurPage(newPage);
    };

    const display = {
        data_set: [], // Thêm dữ liệu sản phẩm ở đây
        cur_page: curPage, // Thay đổi tên biến từ display.cur_page sang curPage
        per_page: 12, // Thay đổi số lượng sản phẩm trên mỗi trang nếu cần
    };

    display.data_set = bookData;

    const renderProductList = () => {
        // Đặt logic render sản phẩm ở đây
      return (
	<div className="row row-cols-1 row-cols-md-2 row-cols-lg-4" id="product-list">
	    {products
		.slice(
		    (display.cur_page - 1) * display.per_page,
		    display.cur_page * display.per_page
		)
		.map((item, index) => (
		    <a key={`product-${index}`} className="col mb-4" href={"/bookDetail?id=" + item.id}>
			<div className="card h-100">
			    <img src={item.bookCover} className="card-img-top" height="200px" alt="..." />
			    <div className="card-body d-flex flex-column">
				<h5 className="card-title text-center">{item.title}</h5>
				<div className="mt-auto">
				    <div className="product-price border-top pt-3">
					<div className="float-start">
					    <h6 className="card-title text-primary">
						{item.price.toLocaleString('de-US', 0)}đ
					    </h6>
					</div>
					<a href="#" className="btn btn-primary float-end">
					    Chi tiết
					</a>
				    </div>
				</div>
			    </div>
			</div>
		    </a>
		))}
	</div>
      );
    };

    const handlePrevClick = () => {
        if (curPage > 1) {
            changePage(curPage - 1);
        }
    };

    const handleNextClick = () => {
        if (curPage < totalPage) {
            changePage(curPage + 1);
        }
    };

    return (   
        <div>
            {renderProductList()}
            <nav aria-label="Page navigation example">
                <ul className="pagination justify-content-end">
                    <li className={`btn-prev ${curPage === 1 ? 'disabled' : ''}`}>
                        <a className="page-link text-primary rounded rounded-0" href="#" aria-label="Previous" onClick={handlePrevClick}>
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    {Array.from({ length: totalPage }, (_, index) => (
                        <li key={index} className={`page-item ${curPage === index + 1 ? 'active' : ''}`}>
                            <a className="page-link text-primary" href="#" onClick={() => changePage(index + 1)}>
                                {index + 1}
                            </a>
                        </li>
                    ))}
                    <li className={`btn-next ${curPage === totalPage ? 'disabled' : ''}`}>
                        <a className="page-link text-primary rounded rounded-0" href="#" aria-label="Next" onClick={handleNextClick}>
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

