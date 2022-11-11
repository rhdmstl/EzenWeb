package Com.EzenWeb.Domain.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDto {
    private String btitle;
    private String bcontent;
}
