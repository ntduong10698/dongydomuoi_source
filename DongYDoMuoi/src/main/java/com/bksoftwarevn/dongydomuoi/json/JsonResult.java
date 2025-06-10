package com.bksoftwarevn.dongydomuoi.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private boolean success;
    private Object data;
    private String message;

    public static ResponseEntity<JsonResult> success(Object data){
        return ResponseEntity.ok(new JsonResult(true, data, "OK"));
    }

    public static ResponseEntity<JsonResult> uploaded(Object data){
        return ResponseEntity.ok(new JsonResult(true, data, "uploaded"));
    }

    public static ResponseEntity<JsonResult> updated(Object data){
        return ResponseEntity.ok(new JsonResult(true, data, "updated"));
    }

    public static ResponseEntity<JsonResult> deleted(){
        return ResponseEntity.ok(new JsonResult(true, null, "deleted"));
    }

    public static ResponseEntity<JsonResult> badRequest(String mess){
        return ResponseEntity.ok(new JsonResult(false, null, mess));
    }

    public static ResponseEntity<JsonResult> error(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new JsonResult(false, null, ex.toString()));
    }
}
