package com.techzen.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DictionaryController {
    //khởi tạo từ điển với các cặp dữ liệu tiếng anh và bản dịch tếng việt

    private Map<String, String> dictionaryMap = Map.ofEntries(
            // hoặc có thể sài hashMap nếu dữ liệu thay thổi thay vì Map.ofEntries thì dữ liệu sẽ không thay đổi
            Map.entry("morning", "Chào buổi sáng"),
            Map.entry("what", "cái gì"),
            Map.entry("apple", "quả táo"),
            Map.entry("phone", "điện thoại"),
            Map.entry("book", "sách")
    );

    //viết API lấy dữ liệu để tra từ điển
    @GetMapping("/dictionary")
    public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String word) {
        //xử lý từ nhập vào : loại bỏ những khoảng trắng và chuyển từ hoa sang thường
        String translation = dictionaryMap.get(word.trim().toLowerCase());

        //nếu muốn tìm bản dịch trong từ điển, trả về kết quả với mã trạng thái 200 OK
        if (translation != null) {
            return ResponseEntity.status(HttpStatus.OK).body(translation);
        }
        // Trả về 404 nếu không tìm thấy từ
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy từ điển");
    }

}
