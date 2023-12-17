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
			WHERE d.timestamp = (SELECT MAX(d.timestamp) FROM DataJPA d)
		""")
	List<DataJPA> findMostRecentData();


	@Query("""
			SELECT
			EXTRACT(HOUR FROM d.timestamp) as hour,
			NULLIF(AVG(d.car),0) as car,
			NULLIF(AVG(d.heavy),0) as heavy,
			NULLIF(AVG(d.currentCo),0) as co,
			NULLIF(AVG(d.currentNo2),0) as no2,
			NULLIF(AVG(d.currentPm2_5),0) as pm2_5,
			NULLIF(AVG(d.currentPm10),0) as pm10,
			NULLIF(AVG(d.currentSo2),0) as so2,
			NULLIF(AVG(d.currentO3),0) as o3,
			NULLIF(AVG(d.v85),0) as v85,
			NULLIF(AVG(d.p1),0) as p1,
			NULLIF(AVG(d.p2),0) as p2,
			NULLIF(AVG(d.no2_aqi),0) as no2Aqi,
			NULLIF(AVG(d.o3_aqi),0) as o3Aqi,
			NULLIF(AVG(d.so2_aqi),0) as so2Aqi,
			NULLIF(AVG(d.pm25_aqi),0) as pm25Aqi,
			NULLIF(AVG(d.pm10_aqi),0) as pm10Aqi,
			NULLIF(AVG(d.co_aqi),0) as coAqi,
			NULLIF(AVG(d.aqi),0) as aqi
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
	List<DataJPAInfo> getAverageValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);

	@Query("""
			SELECT
			EXTRACT(HOUR FROM d.timestamp) as hour,
			SUM(d.car) as car,
			SUM(d.heavy) as heavy,
			SUM(d.currentCo) as co,
			SUM(d.currentNo2) as no2,
			SUM(d.currentPm2_5) as pm2_5,
			SUM(d.currentPm10) as pm10,
			SUM(d.currentSo2) as so2,
			SUM(d.currentO3) as o3,
			SUM(d.v85) as v85,
			SUM(d.p1) as p1,
			SUM(d.p2) as p2,
			SUM(d.no2_aqi) as no2Aqi,
			SUM(d.o3_aqi) as o3Aqi,
			SUM(d.so2_aqi) as so2Aqi,
			SUM(d.pm25_aqi) as pm25Aqi,
			SUM(d.pm10_aqi) as pm10Aqi,
			SUM(d.co_aqi) as coAqi,
			SUM(d.aqi) as aqi
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
	List<DataJPAInfo> getTotalValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);


	@Query("""
		SELECT d
		FROM DataJPA d
		JOIN AnomalyJPA a ON d.id = a.dataId
		WHERE DATE (d.timestamp) = :date
		AND FUNCTION('ACOS', FUNCTION('COS', FUNCTION('RADIANS', :latitude)) * 
			FUNCTION('COS', 
			FUNCTION('RADIANS', d.location.latitude)) 
			* FUNCTION('COS', 
			FUNCTION('RADIANS', d.location.longitude) - 
			FUNCTION('RADIANS', :longitude)) + 
			FUNCTION('SIN', FUNCTION('RADIANS', :latitude)) * 
			FUNCTION('SIN', FUNCTION('RADIANS', d.location.latitude))) * 6371 < :radiusKm
	""")
	List<DataJPA> getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKm(LocalDate date, double latitude, double longitude, double radiusKm);

}
