package com.example.caravan.repository;

import com.example.caravan.entity.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface AnomalyRepository extends JpaRepository<Anomaly, Integer> {

    interface AnomalyDashboardRow {
        Integer getAnomalyId();
        String getAnomalyType();
        String getDescription();
        String getEmployeeName();
        String getDepartmentName();
        String getPriorityLabel();
    }

    @Query(value = """
            SELECT
                a.anomaly_id AS anomalyId,
                a.type AS anomalyType,
                a.description AS description,
                e.full_name AS employeeName,
                d.department_name AS departmentName,
                CASE
                    WHEN a.type IN ('UNDERWORKING', 'MISSED_CHECKPOINT') THEN 'НЕМЕДЛЕННЫЙ'
                    WHEN a.type = 'OVERWORKING' THEN 'СЕРЕДИНА'
                    WHEN a.type IN ('LATE_ARRIVAL', 'EARLY_DEPARTURE') THEN 'ПРЕДУПРЕЖДЕНИЕ'
                END AS priorityLabel
            FROM anomaly a
            JOIN employee e ON a.employee_id = e.employee_id
            LEFT JOIN department d ON e.department_id = d.department_id
            LEFT JOIN location l ON d.location_id = l.location_id
            WHERE (a.is_excused = FALSE OR a.is_excused IS NULL)
              AND l.location_name = :location
            ORDER BY
                CASE
                    WHEN a.type IN ('UNDERWORKING', 'MISSED_CHECKPOINT') THEN 1
                    WHEN a.type = 'OVERWORKING' THEN 2
                    WHEN a.type IN ('LATE_ARRIVAL', 'EARLY_DEPARTURE') THEN 3
                    ELSE 4
                END ASC,
                a.anomaly_id DESC
            LIMIT 10
            """, nativeQuery = true)
    List<AnomalyDashboardRow> findTopActiveAnomalies(@Param("location") String location);

}
