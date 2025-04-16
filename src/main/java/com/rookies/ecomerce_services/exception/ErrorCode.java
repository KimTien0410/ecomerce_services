package com.rookies.ecomerce_services.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CATEGORY_NAME_NOT_BLANK(400," Tên danh mục không được để trống!", HttpStatus.BAD_REQUEST),
    INVALID_KEY(400,"Trường không hợp lệ!", HttpStatus.BAD_REQUEST),
    INVALID_TYPE(400,"Kiểu dữ liệu không hợp lệ!", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401,"Không có quyền truy cập!", HttpStatus.UNAUTHORIZED),
    CATEGORY_NOT_FOUND(404,"Không tìm thấy danh mục!", HttpStatus.NOT_FOUND),
    PRODUCT_NAME_NOT_BLANK(400,"Tên sản phẩm không được để trống!", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_NOT_NULL(400,"Giá sản phẩm không được để trống!", HttpStatus.BAD_REQUEST),
    STOCK_QUANTITY_NOT_NULL(400,"Số lượng sản phẩm không được để trống!", HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGES_NOT_VALID(400,"Hình ảnh sản phẩm không hợp lệ!", HttpStatus.BAD_REQUEST),
    UPLOAD_FILE_FAILED(500,"Tải tệp lên không thành công!", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_FOUND(404,"Không tìm thấy sản phẩm!", HttpStatus.NOT_FOUND),
    PRODUCT_PRICE_MIN(400,"Giá sản phẩm phải lớn hơn 0!", HttpStatus.BAD_REQUEST),
    STOCK_QUANTITY_MIN(400,"Số lượng sản phẩm phải lớn hơn 0!", HttpStatus.BAD_REQUEST),

    ROLE_NOT_FOUND(404,"Không tìm thấy Role!", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS(400,"Email đã tồn tại!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(404,"Không tìm thấy người dùng!", HttpStatus.NOT_FOUND),
    PASSWORD_INCORRECT(400,"Mật khẩu không chính xác!", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(404,"Không tìm thấy khách hàng!", HttpStatus.NOT_FOUND),

    RATING_NOT_FOUND(404,"Không tìm thấy đánh giá!", HttpStatus.NOT_FOUND),

    FEATURE_PRODUCT_NOT_FOUND(404,"Không tìm thấy sản phẩm nổi bật!", HttpStatus.NOT_FOUND),
    ADMIN_NOT_FOUND(404,"Không tìm thấy quản trị viên!", HttpStatus.NOT_FOUND),
    RECEIVER_NAME_NOT_BLANK(400,"Tên người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_PHONE_NOT_BLANK(400,"Số điện thoại người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_STREET_NOT_BLANK(400,"Địa chỉ người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_WARD_NOT_BLANK(400,"Phường xã người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_DISTRICT_NOT_BLANK(400,"Quận huyện người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_CITY_NOT_BLANK(400,"Tỉnh thành người nhận không được để trống!", HttpStatus.BAD_REQUEST),
    RECEIVER_ADDRESS_NOT_FOUND(404,"Không tìm thấy địa chỉ nhận hàng!", HttpStatus.NOT_FOUND),
    CUSTOMER_ID_NOT_NULL(400,"ID khách hàng không được để trống!", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
