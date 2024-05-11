import React from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import 'font-awesome/css/font-awesome.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import '../itemproduct.css'
import { Header } from '../components/header'
import { Footer } from '../components/footer'
import { ProductList } from '../components/paging'
import { useState } from 'react'
import { useLocation } from 'react-router-dom'

function ProductDetail(){
    return (
        <div className="row bg-flex mg-auto">
            <div className="product-info row">
                <div className="image">
                    <img width="100%" height="auto" src="./img/product_item_GT.jpg" alt="Book" />
                </div>
                <div className="info">
                    <h1 className="inner-title">Giáo trình giải tích 2
                        <div className="inner-price-new">₫15000</div>
                        <div className="inner-price-old">₫20000</div>
                    </h1>
                    <form className="form-add-cart" action="/" method="">
                        <input class="form-quantity" type="number" name="quantity" value="1" min="1" max="10"/>
                        <button className="btn-add-cart" type="submit">
                            <div className="inner-text">
                                Thêm vào giỏ hàng
                                <br/><span display="block" class="icon">
                                    <img className="m-0" src="/shopping-cart.svg" height="25px" width="25px" alt="shopping cart"/>
                                </span>
                            </div>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}

function Description(){
    return(
        <div className="bg-flex column">
            <h2 className="inner-title">Mô tả sản phẩm</h2>
            <p className="inner-desc"><strong>Tình trạng:</strong> Cũ(90~95%).</p>
            <p className="inner-desc"><strong>Danh mục:</strong> Sách giáo khoa.</p>
        </div>

    )
}

export function ProductItemPage(){
    return (
        <div>
        	<Header/>
        	<div className="container">
        	    <ProductDetail/>
        	    <Description/>
        	</div>
        	<Footer/>
        </div>
    );
}