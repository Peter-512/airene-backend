package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.adapters.out.db.data.DataJPAAverageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AverageDataDTO {
	private List<DataJPAAverageInfo> average;
}
