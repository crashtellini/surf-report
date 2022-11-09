package com.sandsbeach.surfreport.adapter.surfline.dto.tide;




import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor

public class SurflineTideListDto {
    private List<SurflineTidesDto> tides;
}
