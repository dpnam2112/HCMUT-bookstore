import React from 'react';
import '../index.css';
import { Header } from '../components/header';
import { Footer } from '../components/footer';
import { ProductList } from '../components/paging';
import { useEffect, useState } from 'react';
import { useQuery } from '../utils/query.js';
import AddBookPage from './addBook.js';
import { Link } from 'react-router-dom';

function Breadcrumb() {
    return (
        <div className="section-breadcrumb">
            <div className="container-xl">
                <nav style={{ '--bs-breadcrumb-divider': '>' }} aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li className="breadcrumb-item active" aria-current="page">Tất cả sản phẩm</li>
                    </ol>
                </nav>
                <div className="title-page text-center">
                    <h3 className="text-primary fw-bolder">Tất cả sách</h3>
                </div>
            </div>
        </div>
    );
}

function OffcanvasMenu() {
    return (
      <div className="offcanvas-lg offcanvas-end overflow-auto p-4 p-lg-0" data-bs-scroll="true" tabIndex="-1" id="offcanvasResponsive" aria-labelledby="offcanvasResponsiveLabel">
        <div className="card w-100 border border-0 mb-5">
          <h5 className="card-header bg-white text-dark border border-0 ps-0">DANH MỤC SẢN PHẨM</h5>
          <ul className="list-group list-group-flush border">
            <li className="list-group-item border border-0 text-hover-primary"><a href="#" className="text-decoration-none text-dark text-hover-primary">Trang chủ</a></li> <li className="list-group-item border border-0 text-hover-primary"><a href="#" className="text-decoration-none text-dark text-hover-primary">Sản phẩm</a></li>
            <li className="list-group-item border border-0 text-hover-primary"><a href="#" className="text-decoration-none text-dark text-hover-primary">Liên hệ</a></li>
            <li className="list-group-item border border-0 text-hover-primary"><a href="#" className="text-decoration-none text-dark text-hover-primary">Giới thiệu</a></li>
            <li className="list-group-item border border-0 text-hover-primary"><a href="#" className="text-decoration-none text-dark text-hover-primary">Tin tức</a></li>
          </ul>
        </div>
      </div>
    );
  }

function PriceFilter() {
  return (
    <div className="card w-100 border border-0 mb-5">
      <h5 className="card-header bg-white text-dark border border-0 ps-0">GIÁ CẢ</h5>
      <ul className="list-group list-group-flush border">
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-0" />
	  <label htmlFor="filter-cost-0" className="form-check-label">
	    Giá dưới 100.000đ
	  </label>
	</li>
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-1" />
	  <label htmlFor="filter-cost-1" className="form-check-label">
	    100.000đ - 200.000đ
	  </label>
	</li>
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-2" />
	  <label htmlFor="filter-cost-2" className="form-check-label">
	    200.000đ - 300.000đ
	  </label>
	</li>
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-3" />
	  <label htmlFor="filter-cost-3" className="form-check-label">
	    300.000đ - 500.000đ
	  </label>
	</li>
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-4" />
	  <label htmlFor="filter-cost-4" className="form-check-label">
	    500.000đ - 1.000.000đ
	  </label>
	</li>
	<li className="list-group-item border border-0 text-hover-primary">
	  <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-5" />
	  <label htmlFor="filter-cost-5" className="form-check-label">
	    Giá trên 1.000.000đ
	  </label>
	</li>
      </ul>
    </div>
);
}
  function PublisherFilter() {
    return (
      <div className="card w-100 border border-0 mb-5">
        <h5 className="card-header bg-white text-dark border border-0 ps-0">NHÀ XUẤT BẢN</h5>
        <ul className="list-group list-group-flush border">
        <div className="scrollable-list">
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-0" />
            <label htmlFor="filter-cost-0" className="form-check-label">
            Adam Khoo
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-1" />
            <label htmlFor="filter-cost-1" className="form-check-label">
            Astrid Lindgren
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-2" />
            <label htmlFor="filter-cost-2" className="form-check-label">
            Barry Nalebuft
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-3" />
            <label htmlFor="filter-cost-3" className="form-check-label">
            Brian Tracy
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-4" />
            <label htmlFor="filter-cost-4" className="form-check-label">
            Cloud Pillow Studio
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-5" />
            <label htmlFor="filter-cost-5" className="form-check-label">
            Cửu Bả Đao
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-4" />
            <label htmlFor="filter-cost-4" className="form-check-label">
            Cloud Pillow Studio
            </label>
          </li>
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-4" />
            <label htmlFor="filter-cost-4" className="form-check-label">
            Cloud Pillow Studio
            </label>
          </li>
        </div>
        </ul>
      </div>
    );
  }

  function CategoryFilter() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
      fetch("http://localhost:8080/api/categories")
	.then(response => response.json())
	.then(data => setCategories(data))
    }, []);

    return (
      <div className="card w-100 border border-0 mb-5">
        <h5 className="card-header bg-white text-dark border border-0 ps-0">THỂ LOẠI</h5>
        <ul className="list-group list-group-flush border">
        <div className="scrollable-list">
          <li className="list-group-item border border-0 text-hover-primary">
            <input className="form-check-input me-2 border rounded-0" type="checkbox" value="" id="filter-cost-0" />
            <label htmlFor="filter-cost-0" className="form-check-label">
            Sách kinh tế
            </label>
          </li>
        </div>
        </ul>
      </div>
    );
  }

function Products() {
    let query = useQuery();
    let [products, setProducts] = useState([]);

    let searchParam = query.get("name");
    let categoriesParam = query.get("categories");
    let page = query.get("page");

    let per_page = 12;

    if (page == null) {
        page = 1;
    }

    let url = `http://localhost:8080/api/books?per_page=${per_page}&page=${page}`;

    if (categoriesParam != null) {
        url += `&categories=${categoriesParam}`;
    }

    if (searchParam != null) {
        url += `&name=${searchParam}`;
    }

    useEffect(() => {
        fetch(url)
            .then(response => response.json())
            .then(products => setProducts(products));
    }, [url]);

    return (
        <div className="container-xl">
            <div className="row">
                <div className="btn-aside d-lg-none" data-bs-toggle="offcanvas" data-bs-target="#offcanvasResponsive" aria-controls="offcanvasResponsive">
                    <i className="fa-solid fa-bars"></i>
                </div>
                {/* aside */}
                <aside className="col-0 col-lg-3">
                    <OffcanvasMenu />
                    <PriceFilter />
                </aside>
                <div className="col-12 col-lg-9">
                    <hr />
                    <div className="d-flex justify-content-between align-items-center mb-3">
                        <h3>Danh sách sản phẩm</h3>
                        <Link to="/addBook" className="btn btn-primary mb-3">Thêm sách mới</Link>
                    </div>
                    <ProductList products={products} />
                </div>
            </div>
        </div>
    );
}

export function ProductsPage() {
    return (
        <div className="vh-100">
            <Header />
            <Breadcrumb />
            <Products />
            <Footer />
        </div>
    );
}
