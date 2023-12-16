package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.adapters.out.db.data.DataJPASumInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TotalDataDTO {
	private List<DataJPASumInfo> total;
}
