package be.kdg.airene.adapters.out.db.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DataRepository  extends JpaRepository<DataJPA, UUID> {


	@Query("""
			SELECT d
			FROM DataJPA d
			WHERE d.timestamp = (max(d.timestamp))
		""")
	List<DataJPA> findMostRecentData();


	@Query("""
			SELECT
			EXTRACT(HOUR FROM d.timestamp) as hour,
			NULLIF(AVG(d.airQuality),0) as avgairQuality,
			NULLIF(AVG(d.car),0) as avgCar,
			NULLIF(AVG(d.heavy),0) as avgHeavy,
			NULLIF(AVG(d.currentCo),0) as avgCo,
			NULLIF(AVG(d.currentNo2),0) as avgNo2,
			NULLIF(AVG(d.currentPm2_5),0) as avgPm2_5,
			NULLIF(AVG(d.currentPm10),0) as avgPm10,
			NULLIF(AVG(d.currentSo2),0) as avgSo2,
			NULLIF(AVG(d.currentO3),0) as avgO3,
			NULLIF(AVG(d.v85),0) as avgV85,
			NULLIF(AVG(d.p1),0) as avgP1,
			NULLIF(AVG(d.p2),0) as avgP2,
			NULLIF(AVG(d.no2_aqi),0) as avgNo2Aqi,
			NULLIF(AVG(d.o3_aqi),0) as avgO3Aqi,
			NULLIF(AVG(d.so2_aqi),0) as avgso2Aqi,
			NULLIF(AVG(d.pm25_aqi),0) as avgpm25Aqi,
			NULLIF(AVG(d.pm10_aqi),0) as avgpm10Aqi,
			NULLIF(AVG(d.co_aqi),0) as avgCoAqi,
			NULLIF(AVG(d.aqi),0) as avgAqi
			FROM DataJPA d
			WHERE DATE (d.timestamp) = :date
			AND FUNCTION('ACOS', FUNCTION('COS', FUNCTION('RADIANS', :latitude)) *
			FUNCTION('COS',
			FUNCTION('RADIANS', d.location.latitude))
			* FUNCTION('COS',
			FUNCTION('RADIANS', d.location.longitude) -
			FUNCTION('RADIANS', :longitude)) +
			FUNCTION('SIN', FUNCTION('RADIANS', :latitude)) *
			FUNCTION('SIN', FUNCTION('RADIANS', d.location.latitude))) * 6371 < :radiusKm
			GROUP BY EXTRACT(HOUR FROM d.timestamp)
			ORDER BY EXTRACT(HOUR FROM d.timestamp) ASC
		""")
	List<DataJPAAverageInfo> getAverageValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);

	@Query("""
			SELECT
			EXTRACT(HOUR FROM d.timestamp) as hour,
			SUM(d.airQuality) as sumAirQuality,
			SUM(d.car) as sumCar,
			SUM(d.heavy) as sumHeavy,
			SUM(d.currentCo) as sumCo,
			SUM(d.currentNo2) as sumNo2,
			SUM(d.currentPm2_5) as sumPm2_5,
			SUM(d.currentPm10) as sumPm10,
			SUM(d.currentSo2) as sumSo2,
			SUM(d.currentO3) as sumO3,
			SUM(d.v85) as sumV85,
			SUM(d.p1) as sumP1,
			SUM(d.p2) as sumP2,
			SUM(d.no2_aqi) as sumNo2Aqi,
			SUM(d.o3_aqi) as sumO3Aqi,
			SUM(d.so2_aqi) as sumSo2Aqi,
			SUM(d.pm25_aqi) as sumPm25Aqi,
			SUM(d.pm10_aqi) as sumPm10Aqi,
			SUM(d.co_aqi) as sumCoAqi,
			SUM(d.aqi) as sumAqi
			FROM DataJPA d
			WHERE DATE (d.timestamp) = :date
			AND FUNCTION('ACOS', FUNCTION('COS', FUNCTION('RADIANS', :latitude)) * 
			FUNCTION('COS', 
			FUNCTION('RADIANS', d.location.latitude)) 
			* FUNCTION('COS', 
			FUNCTION('RADIANS', d.location.longitude) - 
			FUNCTION('RADIANS', :longitude)) + 
			FUNCTION('SIN', FUNCTION('RADIANS', :latitude)) * 
			FUNCTION('SIN', FUNCTION('RADIANS', d.location.latitude))) * 6371 < :radiusKm
			GROUP BY EXTRACT(HOUR FROM d.timestamp)
			ORDER BY EXTRACT(HOUR FROM d.timestamp) ASC
		""")
	List<DataJPASumInfo> getTotalValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);


}
