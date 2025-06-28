package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private String buildJoin(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder joinClause = new StringBuilder();

        if (buildingSearchRequest.getRentAreaFrom() != null || buildingSearchRequest.getRentAreaTo() != null) {
            joinClause.append(" join rentarea g on b.id = g.buildingid");
        }
        if (buildingSearchRequest.getStaffId() != null) {
            joinClause.append(" join assignmentbuilding c on b.id = c.buildingid ");
        }

        return joinClause.toString();
    }

    private String buildWhere(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder whereClause = new StringBuilder(" WHERE 1=1 ");
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();

            for (Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                Object value = item.get(buildingSearchRequest);


                if (value != null && !key.equals("staffId") && !key.equals("typeCode")
                        && !key.startsWith("rentArea") && !key.startsWith("rentPrice")
                        && !key.equals("street") && !key.equals("ward")) {


                    if (StringUtils.isNumber(value.toString())) {
                        whereClause.append(" AND b.").append(key).append(" = ").append(value);
                    } else {
                        if(value!=""){
                            whereClause.append(" AND b.").append(key).append(" LIKE '%").append(value).append("%'");
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (buildingSearchRequest.getRentAreaFrom() != null) {
            whereClause.append(" AND g.value >=" + buildingSearchRequest.getRentAreaFrom());
        }
        if (buildingSearchRequest.getRentAreaTo() != null) {
            whereClause.append(" AND g.value <=" + buildingSearchRequest.getRentAreaTo());
        }
        if (buildingSearchRequest.getWard() != null && !buildingSearchRequest.getWard().isEmpty()) {
            whereClause.append(" AND b.ward LIKE '%" + buildingSearchRequest.getWard() + "%'");
        }
        if (buildingSearchRequest.getStreet() != null && !buildingSearchRequest.getStreet().isEmpty()) {
            whereClause.append(" AND b.street LIKE '%" + buildingSearchRequest.getStreet() + "%'");
        }
        if (buildingSearchRequest.getRentPriceFrom() != null) {
            whereClause.append(" AND b.rentprice >= " + buildingSearchRequest.getRentPriceFrom());
        }
        if (buildingSearchRequest.getRentPriceTo() != null) {
            whereClause.append(" AND b.rentprice <= " + buildingSearchRequest.getRentPriceTo());
        }

        if (buildingSearchRequest.getStaffId() != null) {
            whereClause.append(" AND c.staffid = " + buildingSearchRequest.getStaffId());
        }
        //loi
        List<String> typeCodes = buildingSearchRequest.getTypeCode();

        if (typeCodes != null && !typeCodes.isEmpty()) {
            whereClause.append(" AND (");
            whereClause.append(typeCodes.stream()
                    .map(i -> "b.type LIKE '%" + i + "%'")
                    .collect(Collectors.joining(" OR ")));

            whereClause.append(")");
        }
        return whereClause.toString();
    }



    @Override
    public List<BuildingEntity> searchBuildings(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
        sql.append(buildJoin(buildingSearchRequest));
        sql.append(buildWhere(buildingSearchRequest));
        sql.append(" GROUP BY b.id ");
        sql.append("ORDER BY b.createddate DESC ");
        System.out.print(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        List<BuildingEntity> T = query.getResultList();
        return T;
    }








}