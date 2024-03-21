import React from 'react';
import { useState } from 'react';

// const display = {
//   data_set: [
//         {
//             'name': '1',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '2',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '3',
//             'price': 1500000,
//             'sale-price': 1500000,
//         },
//         {
//             'name': '4',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '5',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '6',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '7',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '8',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '9',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '10',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '11',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '12',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '13',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '14',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '15',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '16',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//         {
//             'name': '17',
//             'price': 1500000,
//             'sale-price': 1200000,
//         },
//     // Remaining product items...
//   ],
//   cur_page: 1,
//   per_page: 8,
//   total_page: Math.ceil(17 / 8), // Hardcoded as there are 17 items in the provided data
// };

// export function ProductList() {
//   return (
//     <div id="product-list">
//       {display.data_set
//         .slice(
//           (display.cur_page - 1) * display.per_page,
//           display.cur_page * display.per_page
//         )
//         .map((item, index) => (
//           <div key={`product-${index}`} className="col-4 col-sm-2 mb-4">
//             <div className="card rounded-0">
//             <img src={process.env.PUBLIC_URL + '/img/kienvachauchau1.jpg'} className="card-img-top" alt="..." />
//               <div className="card-body">
//                 {item['sale-price'] !== item.price && <div className="flash"></div>}
//                 <p className="card-text text-center">{item.name}</p>
//                 <div className="product-price border-top pt-3" style={{ minHeight: '58px' }}>
//                   <div className="float-start">
//                     <h6 className="card-title text-primary">
//                       {item['sale-price'].toLocaleString('de-US', 0)}đ
//                     </h6>
//                     {item['sale-price'] !== item.price && (
//                       <p className="card-text text-decoration-line-through" style={{ fontSize: '12px' }}>
//                         {item.price.toLocaleString('de-US', 0)}đ
//                       </p>
//                     )}
//                   </div>
//                   <a href="#" className="btn--primary float-end">
//                     Chi tiết
//                   </a>
//                 </div>
//               </div>
//             </div>
//           </div>
//         ))}
//     </div>
//   );
// };


export function ProductList() {
    const [curPage, setCurPage] = useState(1);
    const totalPage = 4; // Thay đổi số trang tùy theo nhu cầu của bạn

    const changePage = (newPage) => {
        setCurPage(newPage);
    };

    const display = {
        data_set: [
        {
            'name': '1',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '2',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '3',
            'price': 1500000,
            'sale-price': 1500000,
        },
        {
            'name': '4',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '5',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '6',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '7',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '8',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '9',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '10',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '11',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '12',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '13',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '14',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '15',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '16',
            'price': 1500000,
            'sale-price': 1200000,
        },
        {
            'name': '17',
            'price': 1500000,
            'sale-price': 1200000,
        }
        ], // Thêm dữ liệu sản phẩm ở đây
        cur_page: curPage, // Thay đổi tên biến từ display.cur_page sang curPage
        per_page: 12, // Thay đổi số lượng sản phẩm trên mỗi trang nếu cần
    };

    const renderProductList = () => {
        // Đặt logic render sản phẩm ở đây
        return (
            <div class = "row" id = "product-list">
                {display.data_set
                    .slice(
                        (display.cur_page - 1) * display.per_page,
                        display.cur_page * display.per_page
                    )
                    .map((item, index) => (
                        <div key={`product-${index}`} className="col-6 col-sm-3 mb-4">
                            <div className="card rounded-0">
                                <img src={process.env.PUBLIC_URL + '/img/kienvachauchau1.jpg'} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    {item['sale-price'] !== item.price && <div className="flash"></div>}
                                    <p className="card-text text-center">{item.name}</p>
                                    <div className="product-price border-top pt-3" style={{ minHeight: '58px' }}>
                                        <div className="float-start">
                                            <h6 className="card-title text-primary">
                                                {item['sale-price'].toLocaleString('de-US', 0)}đ
                                            </h6>
                                            {item['sale-price'] !== item.price && (
                                                <p className="card-text text-decoration-line-through" style={{ fontSize: '12px' }}>
                                                    {item.price.toLocaleString('de-US', 0)}đ
                                                </p>
                                            )}
                                        </div>
                                        <a href="#" className="btn--primary float-end">
                                            Chi tiết
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
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

