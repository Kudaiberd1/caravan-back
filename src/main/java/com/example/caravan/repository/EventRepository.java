package com.example.caravan.repository;

import com.example.caravan.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    interface DepartmentHeatmapRow {
        Integer getDepartmentId();
        String getDepartmentName();
        Long getEmployeesCount();
        Long getTotalEmployees();
    }

    @Query(value = """
            WITH LatestEventsToday AS (
                SELECT
                    ev.employee_id,
                    d.door_direction,
                    z.area_function,
                    ROW_NUMBER() OVER(PARTITION BY ev.employee_id ORDER BY ev.event_time DESC) as rn
                FROM event ev
                JOIN door d ON ev.door_id = d.door_id
                JOIN zone z ON d.zone_id = z.zone_id
                JOIN location l ON z.location_id = l.location_id
                WHERE DATE(ev.event_time) = CURRENT_DATE
                  AND l.location_name = :location
            )
            SELECT COUNT(employee_id)
            FROM LatestEventsToday
            WHERE rn = 1
              AND NOT (door_direction = 'OUT' AND area_function = 'MAIN_ENTRANCE')
            """, nativeQuery = true)
    Long countEmployeesOnSiteToday(@Param("location") String location);


    @Query(value = """
            WITH LatestEventsToday AS (
                SELECT
                    ev.employee_id,
                    d.door_direction,
                    z.area_function,
                    ROW_NUMBER() OVER(PARTITION BY ev.employee_id ORDER BY ev.event_time DESC) as rn
                FROM event ev
                JOIN door d ON ev.door_id = d.door_id
                JOIN zone z ON d.zone_id = z.zone_id
                JOIN location l ON z.location_id = l.location_id
                WHERE DATE(ev.event_time) = CURRENT_DATE
                  AND l.location_name = :location
            ),
            AllEmployeesShouldBeOnSite AS (
                SELECT emp.employee_id,
                       emp.department_id
                FROM employee emp
                JOIN department dep ON emp.department_id = dep.department_id
                JOIN location l ON dep.location_id = l.location_id
                WHERE l.location_name = :location
            ),
            OnSiteEmployees AS (
                SELECT employee_id
                FROM LatestEventsToday
                WHERE rn = 1
                  AND NOT (door_direction = 'OUT' AND area_function = 'MAIN_ENTRANCE')
            )
            SELECT
                dep.department_id AS departmentId,
                dep.department_name AS departmentName,
                COUNT(ose.employee_id) AS employeesCount,
                COUNT(ae.employee_id) AS totalEmployees
            FROM department dep
            JOIN location l ON dep.location_id = l.location_id
            LEFT JOIN AllEmployeesShouldBeOnSite ae ON ae.department_id = dep.department_id
            LEFT JOIN OnSiteEmployees ose ON ose.employee_id = ae.employee_id
            WHERE l.location_name = :location
            GROUP BY dep.department_id, dep.department_name
            ORDER BY employeesCount DESC
            """, nativeQuery = true)
    List<DepartmentHeatmapRow> onSiteHeatmapByDepartmentToday(@Param("location") String location);
}
