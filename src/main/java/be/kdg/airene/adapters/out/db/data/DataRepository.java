package be.kdg.airene.adapters.out.db.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DataRepository  extends JpaRepository<DataJPA, UUID> {


	@Query("""
			SELECT d
			FROM DataJPA d
			WHERE d.timestamp = (SELECT MAX(d.timestamp) FROM DataJPA d)
		""")
	List<DataJPA> findMostRecentData();


	@Query(value = """
		SELECT
          EXTRACT(HOUR FROM d.timestamp) as hour,
          nullif(avg(d.car), 0) as car,
          nullif(avg(d.heavy), 0) as heavy,
          nullif(avg(d.current_co), 0) as co,
          nullif(avg(d.current_no2), 0) as no2,
          nullif(avg(d.current_pm2_5), 0) as pm2_5,
          nullif(avg(d.current_pm10), 0) as pm10,
          nullif(avg(d.current_so2), 0) as so2,
          nullif(avg(d.currentO3), 0) as o3,
          nullif(avg(d.v85), 0) as v85,
          nullif(avg(d.p1), 0) as p1,
          nullif(avg(d.p2), 0) as p2,
          nullif(avg(d.no2_aqi), 0) as no2Aqi,
          nullif(avg(d.o3_aqi), 0) as o3Aqi,
          nullif(avg(d.so2_aqi), 0) as so2Aqi,
          nullif(avg(d.pm25_aqi), 0) as pm25Aqi,
          nullif(avg(d.pm10_aqi), 0) as pm10Aqi,
          nullif(avg(d.co_aqi), 0) as coAqi,
          nullif(avg(d.aqi), 0) as aqi
      FROM data d
      WHERE DATE (d.timestamp) = :date
        AND :latitude BETWEEN (d.latitude  - :radiusKm/69.0) AND (d.latitude  + :radiusKm/69.0)
        AND :longitude BETWEEN (d.longitude - :radiusKm/42.5) AND (d.longitude + :radiusKm/42.5)
        AND acos( cos( radians( :latitude)) *
                             cos(
                                      radians( d.latitude))
                                 * cos(
                                            radians( d.longitude) -
                                            radians( :longitude)) +
                             sin( radians( :latitude)) *
                             sin( radians( d.latitude))) * 6371 < :radiusKm
      GROUP BY EXTRACT(HOUR FROM d.timestamp)
      ORDER BY EXTRACT(HOUR FROM d.timestamp)
		""", nativeQuery = true)
	List<DataJPAInfo> getAverageValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);

	@Query(value = """
			SELECT
          EXTRACT(HOUR FROM d.timestamp) as hour,
          SUM(d.car) as car,
          SUM(d.heavy) as heavy,
          SUM(d.current_co) as co,
          SUM(d.current_no2) as no2,
          SUM(d.current_pm2_5) as pm2_5,
          SUM(d.current_pm10) as pm10,
          SUM(d.current_so2) as so2,
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
      FROM data d
      WHERE DATE (d.timestamp) = :date
        AND :latitude BETWEEN (d.latitude  - :radiusKm/69.0) AND (d.latitude  + :radiusKm/69.0)
        AND :longitude BETWEEN (d.longitude - :radiusKm/42.5) AND (d.longitude + :radiusKm/42.5)
        AND acos( cos( radians( :latitude)) *
                             cos(
                                      radians( d.latitude))
                                 * cos(
                                            radians( d.longitude) -
                                            radians( :longitude)) +
                             sin( radians( :latitude)) *
                             sin( radians( d.latitude))) * 6371 < :radiusKm
      GROUP BY EXTRACT(HOUR FROM d.timestamp)
      ORDER BY EXTRACT(HOUR FROM d.timestamp)
		""", nativeQuery = true)
	List<DataJPAInfo> getTotalValuesPerHourAscendingForDayInARadiusOfLocation(LocalDate date, double latitude, double longitude, double radiusKm);


	@Query("""
		SELECT d
		FROM DataJPA d
		JOIN AnomalyJPA a ON d.id = a.dataId
		WHERE DATE (d.timestamp) = :date
		AND :latitude BETWEEN (d.location.latitude  - :radiusKm/69.0) AND (d.location.latitude  + :radiusKm/69.0)
        AND :longitude BETWEEN (d.location.longitude - :radiusKm/42.5) AND (d.location.longitude + :radiusKm/42.5)
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

	@Query(
		"""
			SELECT d
			FROM DataJPA d
			JOIN AnomalyJPA a ON d.id = a.dataId
			WHERE d.timestamp = :timestamp
		"""
	)
	List<DataJPA> findMostRecentAnomalies(LocalDateTime timestamp);
}
