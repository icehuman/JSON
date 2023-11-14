package com.json.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 如果前端数据中json中的area_info中的center格式用类接收 则使用 如果还是用数组 则不需要用
public class Center {

    private Integer lng;

    private Integer lat;

    private Integer height;
}
