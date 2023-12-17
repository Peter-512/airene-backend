package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.adapters.out.db.data.DataJPAInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InfoDataDTO {
	private List<DataJPAInfo> data;
}
