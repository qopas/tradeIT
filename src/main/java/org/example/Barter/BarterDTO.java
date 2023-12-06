package org.example.Barter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarterDTO {

    private Integer id;

    private BarterProductDTO offeredBy;

    private BarterProductDTO requestedFrom;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date initiatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date lastUpdated;

    private String message;

    public static BarterDTO fromEntity(Barter barter) {
        return BarterDTO.builder()
                .id(barter.getId())
                .offeredBy(BarterProductDTO.fromEntity(barter.getOfferedId()))
                .requestedFrom(BarterProductDTO.fromEntity(barter.getRequestedId()))
                .status(barter.getStatus())
                .initiatedAt(barter.getInitiated_at())
                .lastUpdated(barter.getLast_updated())
                .message(barter.getMessage())
                .build();
    }
}
